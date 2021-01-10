// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelrainbow extends EntityModel<Entity> {
	private final ModelRenderer rainbow;

	public Modelrainbow() {
		textureWidth = 32;
		textureHeight = 32;

		rainbow = new ModelRenderer(this);
		rainbow.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(rainbow, 0.0F, 0.0F, -1.5708F);
		rainbow.setTextureOffset(0, 0).addBox(4.0F, -1.0F, -2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
		rainbow.setTextureOffset(7, 8).addBox(0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		rainbow.setTextureOffset(0, 6).addBox(0.0F, -1.0F, -7.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		rainbow.setTextureOffset(11, 3).addBox(2.0F, -1.0F, -6.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		rainbow.setTextureOffset(0, 11).addBox(2.0F, -1.0F, 3.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		rainbow.setTextureOffset(6, 16).addBox(4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		rainbow.setTextureOffset(14, 8).addBox(4.0F, -1.0F, -3.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		rainbow.setTextureOffset(8, 13).addBox(3.0F, -1.0F, 3.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		rainbow.setTextureOffset(10, 0).addBox(3.0F, -1.0F, -4.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		rainbow.setTextureOffset(0, 16).addBox(3.0F, -1.0F, -6.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		rainbow.setTextureOffset(14, 14).addBox(3.0F, -1.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		rainbow.setTextureOffset(16, 3).addBox(4.0F, -1.0F, 4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		rainbow.setTextureOffset(0, 0).addBox(4.0F, -1.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		rainbow.render(matrixStack, buffer, packedLight, packedOverlay);
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