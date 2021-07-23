package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlyWitchSkizzieGlowLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public FriendlyWitchSkizzieGlowLayer(RenderLayerParent<T, M> entity) {
        super(entity);
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        VertexConsumer builder = buffer.getBuffer(RenderType.eyes(new ResourceLocation("skizzik:textures/entity/friendly_witch_skizzie/friendly_witch_skizzie_glow.png")));
        this.getParentModel().renderToBuffer(matrix, builder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
