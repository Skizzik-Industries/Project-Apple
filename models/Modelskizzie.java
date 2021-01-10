// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelskizzie extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer body2;
	private final ModelRenderer head1;

	public Modelskizzie() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(2.0F, 16.9F, -0.5F);
		setRotationAngle(body, 0.4363F, 0.0F, 0.0F);
		body.setTextureOffset(26, 16).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, true);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(2.0F, 6.9F, -0.5F);
		body2.setTextureOffset(23, 25).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, 0.0F, true);
		body2.setTextureOffset(0, 16).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);
		body2.setTextureOffset(0, 20).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);
		body2.setTextureOffset(0, 24).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(0.0F, 3.0F, 0.0F);
		head1.setTextureOffset(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
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