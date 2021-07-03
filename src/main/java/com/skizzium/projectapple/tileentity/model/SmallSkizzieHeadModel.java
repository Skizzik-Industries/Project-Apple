package com.skizzium.projectapple.tileentity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SmallSkizzieHeadModel extends GenericHeadModel {
    protected final ModelRenderer head;

    public SmallSkizzieHeadModel(int texOffsWidth, int texOffsHeight, int texWidth, int texHeight) {
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.head = new ModelRenderer(this, texOffsWidth, texOffsHeight);
        this.head.addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F);
        this.head.setPos(0.0F, 0.0F, 0.0F);
    }

    public void setupAnim(float f1, float f2, float f3) {
        this.head.yRot = f2 * ((float)Math.PI / 180F);
        this.head.xRot = f3 * ((float)Math.PI / 180F);
    }

    public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
    }
}
