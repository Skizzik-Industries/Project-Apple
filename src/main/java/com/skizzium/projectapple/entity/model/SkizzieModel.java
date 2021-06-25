package com.skizzium.projectapple.entity.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.Skizzie;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer lowerBody;
	private final ModelRenderer upperBody;
	private final ModelRenderer head;

	public SkizzieModel() {
		this.texWidth = 64;
		this.texHeight = 64;

		lowerBody = new ModelRenderer(this);
		lowerBody.setPos(2.0F, 17F, -0.5F);
		setRotationAngle(lowerBody, 0.5F, 0.0F, 0.0F);
		lowerBody.texOffs(26, 16).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, true);

		upperBody = new ModelRenderer(this);
		upperBody.setPos(2.0F, 7F, -0.5F);
		upperBody.texOffs(23, 25).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, 0.0F, true);
		upperBody.texOffs(0, 16).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);
		upperBody.texOffs(0, 20).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);
		upperBody.texOffs(0, 24).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 3.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
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