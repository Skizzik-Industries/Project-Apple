// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelprismarine_colossus extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body;

	public Modelprismarine_colossus() {
		textureWidth = 512;
		textureHeight = 256;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -120.0F, 0.0F);
		head.setTextureOffset(0, 168).addBox(-24.0F, -46.0F, -16.0F, 48.0F, 48.0F, 40.0F, 0.0F, false);
		head.setTextureOffset(0, 30).addBox(-20.0F, -48.0F, -22.0F, 40.0F, 2.0F, 40.0F, 0.0F, false);
		head.setTextureOffset(144, 116).addBox(-22.0F, -33.5F, -24.0F, 8.0F, 44.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(464, 184).addBox(-14.0F, -9.5F, -28.0F, 8.0F, 26.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(464, 184).addBox(6.0F, -9.5F, -28.0F, 8.0F, 26.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(144, 116).addBox(14.0F, -33.5F, -24.0F, 8.0F, 44.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(144, 80).addBox(6.0F, -29.5F, -24.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(464, 218).addBox(-6.0F, -29.5F, -24.0F, 12.0F, 30.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(308, 51).addBox(-6.0F, -17.5F, -28.0F, 12.0F, 7.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(272, 16).addBox(-14.0F, -17.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(272, 16).addBox(6.0F, -17.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(120, 1).addBox(-26.0F, -17.5F, -28.0F, 12.0F, 4.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(120, 1).addBox(14.0F, -17.5F, -28.0F, 12.0F, 4.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(272, 16).addBox(-26.0F, -25.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 14).addBox(-26.0F, -33.5F, -29.0F, 52.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 6).addBox(-24.0F, -32.5F, -30.0F, 48.0F, 7.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(272, 16).addBox(18.0F, -25.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(272, 32).addBox(-5.0F, -25.5F, -28.0F, 10.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(336, 92).addBox(-5.0F, -32.5F, -31.0F, 10.0F, 11.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -32.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -28.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -24.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -20.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -32.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -32.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -28.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -28.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -24.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -24.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -20.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -20.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(464, 168).addBox(-18.0F, -25.5F, -25.0F, 13.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(464, 168).addBox(5.0F, -25.5F, -25.0F, 13.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(144, 80).addBox(-14.0F, -29.5F, -24.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-4.0F, -112.0F, 0.0F);
		rightArm.setTextureOffset(176, 160).addBox(-44.0F, -8.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		rightArm.setTextureOffset(362, 118).addBox(-45.0F, 32.0F, -13.0F, 26.0F, 16.0F, 26.0F, 0.0F, false);
		rightArm.setTextureOffset(176, 10).addBox(-46.0F, -9.0F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(4.0F, -112.0F, 0.0F);
		leftArm.setTextureOffset(176, 64).addBox(20.0F, -8.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		leftArm.setTextureOffset(362, 76).addBox(19.0F, 32.0F, -13.0F, 26.0F, 16.0F, 26.0F, 0.0F, false);
		leftArm.setTextureOffset(272, 106).addBox(28.0F, -9.0F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(0.0F, -48.0F, 0.0F);
		rightLeg.setTextureOffset(272, 160).addBox(-24.0F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		rightLeg.setTextureOffset(272, 86).addBox(-24.0F, 60.0F, -20.0F, 24.0F, 12.0F, 8.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(0.0F, -48.0F, 0.0F);
		leftLeg.setTextureOffset(368, 160).addBox(0.0F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		leftLeg.setTextureOffset(272, 66).addBox(0.0F, 60.0F, -20.0F, 24.0F, 12.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(-24.0F, -119.5F, 2.0F);
		body.setTextureOffset(0, 72).addBox(0.0F, -0.5F, -14.0F, 48.0F, 72.0F, 24.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.rightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}