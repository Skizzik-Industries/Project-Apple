package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikGlowLayer<T extends Skizzik, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    public SkizzikGlowLayer(IEntityRenderer<T, M> entity) {
        super(entity);
    }

    @Override
    public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
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

        IVertexBuilder builder = buffer.getBuffer(RenderType.eyes(texture));
        this.getParentModel().renderToBuffer(matrix, builder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
