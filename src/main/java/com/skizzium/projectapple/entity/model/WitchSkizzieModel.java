package com.skizzium.projectapple.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer lowerBody;
	private final ModelRenderer upperBody;
	private final ModelRenderer head;

	public WitchSkizzieModel() {
		this.texWidth = 64;
		this.texHeight = 64;

		lowerBody = new ModelRenderer(this);
		lowerBody.setPos(2.0F, 6.9F, -0.5F);
		lowerBody.texOffs(0, 28).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, 0.0F, false);
		lowerBody.texOffs(0, 24).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);
		lowerBody.texOffs(0, 20).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);
		lowerBody.texOffs(0, 16).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);

		upperBody = new ModelRenderer(this);
		upperBody.setPos(2.0F, 16.9F, -0.5F);
		setRotationAngle(upperBody, 0.6109F, 0.0F, 0.0F);
		upperBody.texOffs(12, 29).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 3.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.texOffs(30, 19).addBox(-1.5F, -9.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		head.texOffs(24, 0).addBox(-1.5F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		head.texOffs(24, 16).addBox(-1.5F, -12.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-1.5F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(32, 8).addBox(-2.5F, -9.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(24, 29).addBox(-3.5F, -7.0F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(26, 16).addBox(-4.5F, -5.0F, -1.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);
		head.texOffs(30, 25).addBox(-3.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.texOffs(32, 12).addBox(0.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 4).addBox(0.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.texOffs(24, 0).addBox(-2.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(24, 3).addBox(0.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(9, 28).addBox(0.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(30, 19).addBox(-2.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(24, 33).addBox(-3.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.texOffs(18, 20).addBox(-1.5F, -5.0F, -4.0F, 2.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		this.head.yRot = f3 / (180F / (float) Math.PI);
		this.head.xRot = f4 / (180F / (float) Math.PI);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha){
		lowerBody.render(matrix, buffer, light, overlay);
		upperBody.render(matrix, buffer, light, overlay);
		head.render(matrix, buffer, light, overlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}