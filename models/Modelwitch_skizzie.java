// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelwitch_skizzie extends EntityModel<Entity> {
	private final ModelRenderer body1;
	private final ModelRenderer body2;
	private final ModelRenderer head1;

	public Modelwitch_skizzie() {
		textureWidth = 64;
		textureHeight = 64;

		body1 = new ModelRenderer(this);
		body1.setRotationPoint(2.0F, 6.9F, -0.5F);
		body1.setTextureOffset(0, 28).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, 0.0F, false);
		body1.setTextureOffset(0, 24).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);
		body1.setTextureOffset(0, 20).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);
		body1.setTextureOffset(0, 16).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(2.0F, 16.9F, -0.5F);
		setRotationAngle(body2, 0.6109F, 0.0F, 0.0F);
		body2.setTextureOffset(12, 29).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(0.0F, 3.0F, 0.0F);
		head1.setTextureOffset(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head1.setTextureOffset(30, 19).addBox(-1.5F, -9.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		head1.setTextureOffset(24, 0).addBox(-1.5F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		head1.setTextureOffset(24, 16).addBox(-1.5F, -12.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head1.setTextureOffset(0, 0).addBox(-1.5F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(32, 8).addBox(-2.5F, -9.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(24, 29).addBox(-3.5F, -7.0F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(26, 16).addBox(-4.5F, -5.0F, -1.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(30, 25).addBox(-3.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(32, 12).addBox(0.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(0, 4).addBox(0.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(24, 0).addBox(-2.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head1.setTextureOffset(24, 3).addBox(0.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head1.setTextureOffset(9, 28).addBox(0.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head1.setTextureOffset(30, 19).addBox(-2.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head1.setTextureOffset(24, 33).addBox(-3.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head1.setTextureOffset(18, 20).addBox(-1.5F, -5.0F, -4.0F, 2.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body1.render(matrixStack, buffer, packedLight, packedOverlay);
		body2.render(matrixStack, buffer, packedLight, packedOverlay);
		head1.render(matrixStack, buffer, packedLight, packedOverlay);
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