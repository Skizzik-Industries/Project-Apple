// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelprismarine_colossus extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightItem;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public Modelprismarine_colossus() {
		textureWidth = 384;
		textureHeight = 384;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(96, 96).addBox(-24.0F, -144.0F, -12.0F, 48.0F, 72.0F, 24.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(-0.5238F, -118.2262F, 0.5238F);
		head.setTextureOffset(0, 0).addBox(-25.4762F, -49.7738F, -14.5238F, 51.0F, 48.0F, 38.0F, 0.01F, false);
		head.setTextureOffset(140, 0).addBox(-25.4762F, -49.7738F, -27.5238F, 51.0F, 15.0F, 13.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(-23.4762F, -34.7738F, -22.5238F, 8.0F, 44.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(-15.4762F, -34.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(0.0238F, -26.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(-7.9762F, -26.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(7.5238F, -34.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(210, 57).addBox(-5.9762F, -34.7738F, -28.5238F, 12.0F, 13.0F, 12.0F, 0.01F, false);
		head.setTextureOffset(210, 82).addBox(-22.9762F, -33.7738F, -28.5238F, 17.0F, 7.0F, 1.0F, 0.01F, false);
		head.setTextureOffset(216, 90).addBox(6.0238F, -33.7738F, -28.5238F, 17.0F, 7.0F, 1.0F, 0.01F, false);
		head.setTextureOffset(210, 44).addBox(-24.4762F, -34.7738F, -27.5238F, 49.0F, 8.0F, 5.0F, 0.01F, false);
		head.setTextureOffset(258, 57).addBox(-24.4762F, -26.7738F, -27.5238F, 6.0F, 8.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(317, 112).addBox(-18.4762F, -26.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(317, 112).addBox(5.5238F, -26.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(316, 111).addBox(-18.4762F, -19.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(316, 111).addBox(5.5238F, -19.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(286, 57).addBox(-17.4762F, -25.7738F, -24.5238F, 11.0F, 6.0F, 0.0F, 0.01F, false);
		head.setTextureOffset(286, 57).addBox(6.5238F, -25.7738F, -24.5238F, 11.0F, 6.0F, 0.0F, 0.01F, false);
		head.setTextureOffset(328, 105).addBox(-18.4762F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(328, 105).addBox(5.5238F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(329, 98).addBox(-6.4762F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(329, 98).addBox(17.5238F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
		head.setTextureOffset(268, 0).addBox(-24.4762F, -18.7738F, -27.5238F, 49.0F, 4.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(350, 12).addBox(-13.4762F, -10.7738F, -27.5238F, 9.0F, 28.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(323, 107).addBox(-4.4762F, -10.7738F, -22.5238F, 9.0F, 4.0F, 1.0F, 0.02F, false);
		head.setTextureOffset(323, 107).addBox(-4.4762F, -6.7738F, -22.5238F, 1.0F, 8.0F, 1.0F, 0.02F, false);
		head.setTextureOffset(323, 107).addBox(3.5238F, -6.7738F, -22.5238F, 1.0F, 8.0F, 1.0F, 0.02F, false);
		head.setTextureOffset(323, 101).addBox(-15.4762F, -6.7738F, -22.5238F, 7.0F, 1.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(322, 95).addBox(7.5238F, -6.7738F, -22.5238F, 7.0F, 1.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(327, 95).addBox(-15.4762F, -6.7738F, -22.5238F, 1.0F, 16.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(322, 95).addBox(14.5238F, -6.7738F, -22.5238F, 1.0F, 16.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(350, 48).addBox(4.5238F, -10.7738F, -27.5238F, 9.0F, 28.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(268, 12).addBox(-13.4762F, -14.7738F, -27.5238F, 27.0F, 4.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(258, 57).addBox(18.5238F, -26.7738F, -27.5238F, 6.0F, 8.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(258, 73).addBox(-5.4762F, -26.7738F, -27.5238F, 11.0F, 8.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(15.5238F, -34.7738F, -22.5238F, 8.0F, 44.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(15.5238F, -34.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(-3.9762F, -34.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(-23.4762F, -34.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(-13.4762F, -26.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 44).addBox(5.5238F, -26.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(178, 28).addBox(-23.4762F, -49.7738F, -28.5238F, 47.0F, 15.0F, 1.0F, 0.01F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-25.5F, -110.0F, 0.0F);
		rightArm.setTextureOffset(240, 96).addBox(-22.5F, -10.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		rightArm.setTextureOffset(288, 192).addBox(-24.0F, -11.5F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);

		rightItem = new ModelRenderer(this);
		rightItem.setRotationPoint(19.0F, 119.75F, 1.0F);
		rightArm.addChild(rightItem);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(26.25F, -110.0F, 0.0F);
		leftArm.setTextureOffset(192, 288).addBox(-2.25F, -10.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		leftArm.setTextureOffset(288, 285).addBox(5.25F, -11.5F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-12.15F, -48.0F, 0.0F);
		rightLeg.setTextureOffset(0, 96).addBox(-11.9F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		rightLeg.setTextureOffset(0, 228).addBox(-12.1F, 60.0F, -12.0F, 24.0F, 12.0F, 24.0F, 1.01F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(12.15F, -48.0F, 0.0F);
		leftLeg.setTextureOffset(96, 288).addBox(-12.1F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
		leftLeg.setTextureOffset(0, 192).addBox(-11.9F, 60.0F, -12.0F, 24.0F, 12.0F, 24.0F, 1.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
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