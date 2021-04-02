// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelprismarine_foe extends EntityModel<Entity> {
	private final ModelRenderer prismarine_foe;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightItem;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public Modelprismarine_foe() {
		textureWidth = 64;
		textureHeight = 64;

		prismarine_foe = new ModelRenderer(this);
		prismarine_foe.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		prismarine_foe.addChild(head);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.01F, false);
		head.setTextureOffset(2, 0).addBox(-3.0F, -8.5F, -3.5F, 6.0F, 1.0F, 7.0F, 0.01F, false);
		head.setTextureOffset(32, 14).addBox(-3.0F, -8.5F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(23, 37).addBox(-4.0F, 0.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(24, 35).addBox(3.0F, 0.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(25, 38).addBox(-2.0F, 0.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(27, 37).addBox(-3.0F, 3.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(26, 37).addBox(1.0F, 0.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(31, 38).addBox(2.0F, 1.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(22, 35).addBox(-4.0F, 0.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(29, 40).addBox(-2.0F, 0.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(23, 36).addBox(1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(26, 36).addBox(3.0F, 0.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(27, 39).addBox(-3.0F, 1.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		prismarine_foe.addChild(body);
		body.setTextureOffset(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-5.0F, -22.0F, 0.0F);
		prismarine_foe.addChild(rightArm);
		rightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		rightArm.setTextureOffset(21, 33).addBox(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.01F, false);
		rightArm.setTextureOffset(21, 41).addBox(-2.0F, -1.5F, -3.0F, 1.0F, 1.0F, 6.0F, 0.01F, false);
		rightArm.setTextureOffset(20, 35).addBox(-2.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);
		rightArm.setTextureOffset(20, 33).addBox(-1.0F, -2.5F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		rightArm.setTextureOffset(20, 35).addBox(-3.5F, 1.0F, -3.0F, 2.0F, 1.0F, 6.0F, 0.001F, false);
		rightArm.setTextureOffset(21, 33).addBox(-4.0F, 1.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.01F, false);
		rightArm.setTextureOffset(49, 0).addBox(-2.5F, -2.5F, -2.5F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		rightArm.setTextureOffset(48, 0).addBox(-3.5F, -2.5F, -2.5F, 2.0F, 4.0F, 5.0F, -0.01F, false);

		rightItem = new ModelRenderer(this);
		rightItem.setRotationPoint(-1.5F, 7.75F, 1.0F);
		rightArm.addChild(rightItem);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(5.0F, -22.0F, 0.0F);
		prismarine_foe.addChild(leftArm);
		leftArm.setTextureOffset(21, 33).addBox(0.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.01F, false);
		leftArm.setTextureOffset(21, 36).addBox(0.0F, -2.5F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		leftArm.setTextureOffset(21, 41).addBox(1.0F, -1.5F, -3.0F, 1.0F, 1.0F, 6.0F, 0.01F, false);
		leftArm.setTextureOffset(20, 35).addBox(1.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);
		leftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		leftArm.setTextureOffset(49, 0).addBox(0.5F, -2.5F, -2.5F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		leftArm.setTextureOffset(48, 0).addBox(1.5F, -2.5F, -2.5F, 2.0F, 4.0F, 5.0F, -0.01F, false);
		leftArm.setTextureOffset(21, 33).addBox(3.0F, 1.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.01F, false);
		leftArm.setTextureOffset(20, 35).addBox(1.5F, 1.0F, -3.0F, 2.0F, 1.0F, 6.0F, 0.001F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-1.9F, -12.0F, 0.0F);
		prismarine_foe.addChild(rightLeg);
		rightLeg.setTextureOffset(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		rightLeg.setTextureOffset(0, 32).addBox(-2.2F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.25F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(1.9F, -12.0F, 0.0F);
		prismarine_foe.addChild(leftLeg);
		leftLeg.setTextureOffset(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		leftLeg.setTextureOffset(0, 32).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.25F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		prismarine_foe.render(matrixStack, buffer, packedLight, packedOverlay);
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