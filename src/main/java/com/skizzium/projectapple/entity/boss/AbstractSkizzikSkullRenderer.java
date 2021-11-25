package com.skizzium.projectapple.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.client.model.PA_SkullModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;

public abstract class AbstractSkizzikSkullRenderer<T extends AbstractHurtingProjectile> extends EntityRenderer<T> {
    private final PA_SkullModel model;
    
    public AbstractSkizzikSkullRenderer(EntityRendererProvider.Context renderer) {
        super(renderer);
        model = new PA_SkullModel(renderer.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_LAYER));
    }

    @Override
    protected int getBlockLightLevel(T skull, BlockPos pos) {
        return 15;
    }

    @Override
    public void render(T skull, float yaw, float pitch, PoseStack matrix, MultiBufferSource renderer, int light) {
        matrix.pushPose();
        matrix.scale(-1.0F, -1.0F, 1.0F);

        float yRot = Mth.rotlerp(skull.yRotO, skull.getYRot(), pitch);
        float xRot = Mth.lerp(pitch, skull.xRotO, skull.getXRot());

        VertexConsumer vertex = renderer.getBuffer(this.model.renderType(this.getTextureLocation(skull)));
        this.model.setupAnim(0.0F, yRot, xRot);
        this.model.renderToBuffer(matrix, vertex, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        matrix.popPose();
        super.render(skull, yaw, pitch, matrix, renderer, light);
    }
}