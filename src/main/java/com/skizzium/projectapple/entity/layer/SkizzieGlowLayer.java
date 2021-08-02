package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.CorruptedSkizzie;
import com.skizzium.projectapple.entity.FriendlySkizzie;
import com.skizzium.projectapple.entity.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieGlowLayer<T extends LivingEntity> extends RenderLayer<T, SkizzieModel<T>> {
    private static final ResourceLocation FRIENDLY_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_skizzie_glow.png");
    private static final ResourceLocation FRIENDLY_WITCH_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_witch_skizzie_glow.png");
    private static final ResourceLocation SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie_glow.png");
    private static final ResourceLocation KABOOM_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie_glow.png");
    private static final ResourceLocation CORRUPTED_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/corrupted_skizzie_glow.png");

    public SkizzieGlowLayer(RenderLayerParent<T, SkizzieModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        ResourceLocation texture = entity instanceof FriendlyWitchSkizzie ? FRIENDLY_WITCH_SKIZZIE_GLOW :
                                    entity instanceof FriendlySkizzie ? FRIENDLY_SKIZZIE_GLOW :
                                        entity instanceof KaboomSkizzie ? KABOOM_SKIZZIE_GLOW :
                                            entity instanceof CorruptedSkizzie ? CORRUPTED_SKIZZIE_GLOW :
                                                SKIZZIE_GLOW;

        VertexConsumer vertex = buffer.getBuffer(RenderType.eyes(texture));
        this.getParentModel().renderToBuffer(pose, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
