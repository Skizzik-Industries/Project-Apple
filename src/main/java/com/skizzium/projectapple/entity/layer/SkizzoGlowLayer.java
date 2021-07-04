package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
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
public class SkizzoGlowLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    public SkizzoGlowLayer(IEntityRenderer<T, M> entity) {
        super(entity);
    }

    @Override
    public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        IVertexBuilder builder = buffer.getBuffer(RenderType.eyes(new ResourceLocation("skizzik:textures/entity/skizzik/skizzo_glow.png")));
        this.getParentModel().renderToBuffer(matrix, builder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
