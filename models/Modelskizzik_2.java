// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelskizzik_2 extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer body2;
	private final ModelRenderer head1;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;

	public Modelskizzik_2() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 4.9F, -8.5F);
		body.setTextureOffset(73, 100).addBox(-3.0F, -11.0F, 6.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(84, 46).addBox(-11.0F, -9.5F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 86).addBox(8.0F, -8.5F, -11.5F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(80, 24).addBox(-10.0F, -8.5F, -11.5F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(60, 44).addBox(-7.0F, -8.5F, -11.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 42).addBox(5.0F, -8.5F, -11.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 37).addBox(5.0F, 2.5F, -11.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 32).addBox(-7.0F, 2.5F, -11.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(64, 78).addBox(-10.0F, 2.5F, -11.5F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(90, 76).addBox(8.0F, -3.5F, -11.5F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body.setTextureOffset(88, 0).addBox(-10.0F, -3.5F, -11.5F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body.setTextureOffset(76, 54).addBox(8.0F, 2.5F, -11.5F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(0, 10).addBox(-7.0F, -3.5F, -11.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 4).addBox(5.0F, -3.5F, -11.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(48, 8).addBox(-11.0F, -4.0F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(48, 0).addBox(-11.0F, 1.5F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 32).addBox(-7.0F, -10.0F, -9.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		body.setTextureOffset(29, 98).addBox(-2.0F, 5.0F, -11.0F, 5.0F, 1.0F, 17.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-2.0F, 3.0F, -11.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(1.0F, 15.9F, 4.5F);
		setRotationAngle(body2, 0.6981F, 0.0F, 0.0F);
		body2.setTextureOffset(101, 101).addBox(-4.0F, -7.0026F, -3.3586F, 7.0F, 13.0F, 7.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(1.0F, -16.0F, 0.0F);
		head1.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(19.0F, -9.0F, 0.0F);
		head2.setTextureOffset(0, 62).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(-18.0F, -11.0F, 1.0F);
		head3.setTextureOffset(36, 74).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		head4 = new ModelRenderer(this);
		head4.setRotationPoint(-13.0F, -34.0F, 1.0F);
		head4.setTextureOffset(52, 20).addBox(3.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		body2.render(matrixStack, buffer, packedLight, packedOverlay);
		head1.render(matrixStack, buffer, packedLight, packedOverlay);
		head2.render(matrixStack, buffer, packedLight, packedOverlay);
		head3.render(matrixStack, buffer, packedLight, packedOverlay);
		head4.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head1.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}