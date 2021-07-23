package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikGlowLayer<T extends Skizzik, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public SkizzikGlowLayer(RenderLayerParent<T, M> entity) {
        super(entity);
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        int stage = entity.getStage();
        ResourceLocation texture = null;
        if (stage == 0) {
            texture = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_sleeping_glow.png");
        }
        else if (stage >= 1 && stage <= 4) {
            texture = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_glow.png");
        }
        else if (stage == 5) {
            texture = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_stage-5_glow.png");
        }
        else {
            texture = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_empty_glow.png");
        }

        VertexConsumer builder = buffer.getBuffer(RenderType.eyes(texture));
        this.getParentModel().renderToBuffer(matrix, builder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
