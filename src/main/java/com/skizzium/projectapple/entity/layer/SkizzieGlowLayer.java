package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieGlowLayer<T extends LivingEntity, M extends SkizzieModel<T>> extends LayerRenderer<T, M> {
    private static final ResourceLocation FRIENDLY_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_skizzie_glow.png");
    private static final ResourceLocation FRIENDLY_WITCH_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_witch_skizzie_glow.png");
    private static final ResourceLocation SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie_glow.png");
    private static final ResourceLocation KABOOM_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie_glow.png");
    private static final ResourceLocation CORRUPTED_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/corrupted_skizzie_glow.png");

    public SkizzieGlowLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack pose, IRenderTypeBuffer buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        ResourceLocation texture = entity instanceof FriendlyWitchSkizzie ? FRIENDLY_WITCH_SKIZZIE_GLOW :
                                    entity instanceof FriendlySkizzie ? FRIENDLY_SKIZZIE_GLOW :
                                        entity instanceof KaboomSkizzie ? KABOOM_SKIZZIE_GLOW :
                                            entity instanceof CorruptedSkizzie ? CORRUPTED_SKIZZIE_GLOW :
                                                SKIZZIE_GLOW;

        IVertexBuilder vertex = buffer.getBuffer(RenderType.eyes(texture));
        this.getParentModel().renderToBuffer(pose, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
