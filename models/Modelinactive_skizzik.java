// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelinactive_skizzik extends EntityModel<Entity> {
	private final ModelRenderer Skizzik;
	private final ModelRenderer body;
	private final ModelRenderer head1;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;

	public Modelinactive_skizzik() {
		textureWidth = 256;
		textureHeight = 256;

		Skizzik = new ModelRenderer(this);
		Skizzik.setRotationPoint(0.0F, 24.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.5F, -7.3895F, -10.9211F);
		Skizzik.addChild(body);
		body.setTextureOffset(73, 100).addBox(-3.5F, -9.7105F, 8.4211F, 7.0F, 19.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(84, 46).addBox(-11.5F, -8.2105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 86).addBox(7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(80, 24).addBox(-10.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(60, 44).addBox(-7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 42).addBox(4.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 37).addBox(4.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 32).addBox(-7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(64, 78).addBox(-10.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(90, 76).addBox(7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body.setTextureOffset(88, 0).addBox(-10.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body.setTextureOffset(76, 54).addBox(7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(0, 10).addBox(-7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 4).addBox(4.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(48, 8).addBox(-11.5F, -2.7105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(48, 0).addBox(-11.5F, 2.7895F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 32).addBox(-7.5F, -8.7105F, -6.5789F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		body.setTextureOffset(29, 98).addBox(-2.5F, 6.2895F, -8.5789F, 5.0F, 1.0F, 17.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-2.5F, 4.2895F, -8.5789F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(1.0F, -40.0F, 0.0F);
		Skizzik.addChild(head1);
		head1.setTextureOffset(0, 0).addBox(-9.0F, 7.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(19.0F, -33.0F, 0.0F);
		Skizzik.addChild(head2);
		head2.setTextureOffset(0, 62).addBox(-6.0F, 21.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(-18.0F, -35.0F, 1.0F);
		Skizzik.addChild(head3);
		head3.setTextureOffset(36, 74).addBox(-6.0F, 23.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		head4 = new ModelRenderer(this);
		head4.setRotationPoint(-17.0F, -19.0F, 1.0F);
		Skizzik.addChild(head4);
		head4.setTextureOffset(52, 20).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		head5 = new ModelRenderer(this);
		head5.setRotationPoint(17.0F, -55.0F, 0.0F);
		Skizzik.addChild(head5);
		head5.setTextureOffset(48, 50).addBox(-5.0F, 31.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Skizzik.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}