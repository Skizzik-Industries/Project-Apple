// Made with Blockbench 3.6.3
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelbeam extends EntityModel<Entity> {
	private final ModelRenderer beam;

	public Modelbeam() {
		textureWidth = 64;
		textureHeight = 64;

		beam = new ModelRenderer(this);
		beam.setRotationPoint(0.0F, 24.0F, 0.0F);
		beam.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -8.0F, 4.0F, 4.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		beam.render(matrixStack, buffer, packedLight, packedOverlay);
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