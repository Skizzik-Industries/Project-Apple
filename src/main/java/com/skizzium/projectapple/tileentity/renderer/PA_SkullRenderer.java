package com.skizzium.projectapple.tileentity.renderer;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.block.PA_SkullBlock;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class PA_SkullRenderer extends TileEntityRenderer<PA_Skull> {
    private static final Map<SkullBlock.ISkullType, GenericHeadModel> MODEL_BY_TYPE = Util.make(Maps.newHashMap(), (model) -> {
        GenericHeadModel genericHead = new GenericHeadModel(0, 0, 32, 32);
        model.put(PA_SkullBlock.CustomTypes.SKIZZIK, genericHead);
    });
    private static final Map<SkullBlock.ISkullType, ResourceLocation> SKIN_BY_TYPE = Util.make(Maps.newHashMap(), (skin) -> {
        skin.put(PA_SkullBlock.CustomTypes.SKIZZIK, new ResourceLocation("skizzik:textures/block/skizzik_head.png"));
    });

    public PA_SkullRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    public void render(PA_Skull skull, float f, MatrixStack matrix, IRenderTypeBuffer render, int i, int i1) {
        float f1 = skull.getMouthAnimation(f);
        BlockState state = skull.getBlockState();
        boolean flag = state.getBlock() instanceof WallSkullBlock;
        Direction direction = flag ? state.getValue(WallSkullBlock.FACING) : null;
        float f2 = 22.5F * (float)(flag ? (2 + direction.get2DDataValue()) * 4 : state.getValue(SkullBlock.ROTATION));

        renderSkull(direction, f2, ((AbstractSkullBlock)state.getBlock()).getType(), skull.getOwnerProfile(), f1, matrix, render, i);
    }

    public static void renderSkull(@Nullable Direction direction, float f, SkullBlock.ISkullType skull, @Nullable GameProfile game, float f1, MatrixStack matrix, IRenderTypeBuffer renderer, int i) {
        GenericHeadModel model = MODEL_BY_TYPE.get(skull);
        matrix.pushPose();

        if (direction == null) {
            matrix.translate(0.5D, 0.0D, 0.5D);
        }
        else {
            float f2 = 0.25F;
            matrix.translate(0.5F - (float)direction.getStepX() * 0.25F, 0.25D, 0.5F - (float)direction.getStepZ() * 0.25F);
        }

        matrix.scale(-1.0F, -1.0F, 1.0F);
        IVertexBuilder builder = renderer.getBuffer(getRenderType(skull, game));

        model.setupAnim(f1, f, 0.0F);
        model.renderToBuffer(matrix, builder, i, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

        matrix.popPose();
    }

    private static RenderType getRenderType(SkullBlock.ISkullType skull, @Nullable GameProfile game) {
        ResourceLocation location = SKIN_BY_TYPE.get(skull);

        if (skull == SkullBlock.Types.PLAYER && game != null) {
            Minecraft minecraft = Minecraft.getInstance();
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().getInsecureSkinInformation(game);

            return map.containsKey(MinecraftProfileTexture.Type.SKIN) ? RenderType.entityTranslucent(minecraft.getSkinManager().registerTexture(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN)) : RenderType.entityCutoutNoCull(DefaultPlayerSkin.getDefaultSkin(PlayerEntity.createPlayerUUID(game)));
        }
        else {
            return RenderType.entityCutoutNoCullZOffset(location);
        }
    }
}
