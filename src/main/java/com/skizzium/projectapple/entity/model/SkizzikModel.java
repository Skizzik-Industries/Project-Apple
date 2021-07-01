package com.skizzium.projectapple.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikModel<T extends Skizzik> extends EntityModel<T> {
	private final ModelRenderer lowerBody;
	private final ModelRenderer upperBody;
	private final ModelRenderer mainHead;
	private final ModelRenderer lowerLeftHead;
	private final ModelRenderer lowerRightHead;
	private final ModelRenderer upperLeftHead;
	private final ModelRenderer upperRightHead;

	public SkizzikModel() {
		texWidth = 256;
		texHeight = 256;

		lowerBody = new ModelRenderer(this);
		lowerBody.setPos(1.0F, 15.9F, 4.5F);
		lowerBody.texOffs(101, 101).addBox(-4.0F, -7.0026F, -3.3586F, 7.0F, 13.0F, 7.0F, 0.0F, false);
		setRotationAngle(lowerBody, 0.6981F, 0.0F, 0.0F);

		upperBody = new ModelRenderer(this);
		upperBody.setPos(0.5F, 3.6105F, -10.9211F);
		upperBody.texOffs(73, 100).addBox(-3.5F, -9.7105F, 8.4211F, 7.0F, 19.0F, 7.0F, 0.0F, false);
		upperBody.texOffs(84, 46).addBox(-11.5F, -8.2105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		upperBody.texOffs(0, 86).addBox(7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		upperBody.texOffs(80, 24).addBox(-10.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		upperBody.texOffs(60, 44).addBox(-7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		upperBody.texOffs(0, 42).addBox(4.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		upperBody.texOffs(0, 37).addBox(4.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		upperBody.texOffs(0, 32).addBox(-7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		upperBody.texOffs(64, 78).addBox(-10.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		upperBody.texOffs(90, 76).addBox(7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		upperBody.texOffs(88, 0).addBox(-10.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		upperBody.texOffs(76, 54).addBox(7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		upperBody.texOffs(0, 10).addBox(-7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		upperBody.texOffs(0, 4).addBox(4.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		upperBody.texOffs(48, 8).addBox(-11.5F, -2.7105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		upperBody.texOffs(48, 0).addBox(-11.5F, 2.7895F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		upperBody.texOffs(0, 32).addBox(-7.5F, -8.7105F, -6.5789F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		upperBody.texOffs(29, 98).addBox(-2.5F, 6.2895F, -8.5789F, 5.0F, 1.0F, 17.0F, 0.0F, false);
		upperBody.texOffs(0, 0).addBox(-2.5F, 4.2895F, -8.5789F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		mainHead = new ModelRenderer(this);
		mainHead.setPos(1.0F, -16.0F, 0.0F);
		mainHead.texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		lowerLeftHead = new ModelRenderer(this);
		lowerLeftHead.setPos(19.0F, -9.0F, 0.0F);
		lowerLeftHead.texOffs(0, 62).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		lowerRightHead = new ModelRenderer(this);
		lowerRightHead.setPos(-18.0F, -11.0F, 1.0F);
		lowerRightHead.texOffs(36, 74).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		upperLeftHead = new ModelRenderer(this);
		upperLeftHead.setPos(17.0F, -31.0F, 0.0F);
		upperLeftHead.texOffs(48, 50).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		upperRightHead = new ModelRenderer(this);
		upperRightHead.setPos(-13.0F, -34.0F, 1.0F);
		upperRightHead.texOffs(52, 20).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		this.mainHead.yRot = f3 * ((float)Math.PI / 180F);
		this.mainHead.xRot = f4 * ((float)Math.PI / 180F);
	}

	/* @Override
	public void prepareMobModel(T entity, float f, float f1, float f2) {
		this.mainHead.yRot = (entity.getHeadYRot(0) - entity.yBodyRot) * ((float)Math.PI / 180F);
		this.mainHead.xRot = entity.getHeadXRot(0) * ((float)Math.PI / 180F);

		this.lowerLeftHead.yRot = (entity.getHeadYRot(1) - entity.yBodyRot) * ((float)Math.PI / 180F);
		this.lowerLeftHead.xRot = entity.getHeadXRot(1) * ((float)Math.PI / 180F);

		this.lowerRightHead.yRot = (entity.getHeadYRot(2) - entity.yBodyRot) * ((float)Math.PI / 180F);
		this.lowerRightHead.xRot = entity.getHeadXRot(2) * ((float)Math.PI / 180F);

		this.upperLeftHead.yRot = (entity.getHeadYRot(3) - entity.yBodyRot) * ((float)Math.PI / 180F);
		this.upperLeftHead.xRot = entity.getHeadXRot(3) * ((float)Math.PI / 180F);

		this.upperRightHead.yRot = (entity.getHeadYRot(4) - entity.yBodyRot) * ((float)Math.PI / 180F);
		this.upperRightHead.xRot = entity.getHeadXRot(4) * ((float)Math.PI / 180F);
	} */

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		lowerBody.render(matrix, buffer, light, overlay);
		upperBody.render(matrix, buffer, light, overlay);
		mainHead.render(matrix, buffer, light, overlay);
		lowerLeftHead.render(matrix, buffer, light, overlay);
		lowerRightHead.render(matrix, buffer, light, overlay);
		upperLeftHead.render(matrix, buffer, light, overlay);
		upperRightHead.render(matrix, buffer, light, overlay);
	}
}