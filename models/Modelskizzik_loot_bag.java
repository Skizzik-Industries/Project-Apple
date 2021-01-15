// Made with Blockbench 3.6.3
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelskizzik_loot_bag extends EntityModel<Entity> {
	private final ModelRenderer key;
	private final ModelRenderer body;
	private final ModelRenderer top;
	private final ModelRenderer back;

	public Modelskizzik_loot_bag() {
		textureWidth = 64;
		textureHeight = 64;

		key = new ModelRenderer(this);
		key.setRotationPoint(0.0F, 16.0F, 0.0F);
		key.setTextureOffset(30, 0).addBox(-1.0F, -6.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(-8.0F, 16.0F, 8.0F);
		body.setTextureOffset(0, 0).addBox(3.0F, -4.0F, -13.0F, 10.0F, 12.0F, 10.0F, 0.0F, false);

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 16.0F, 0.0F);
		top.setTextureOffset(0, 22).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);

		back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, 16.0F, 0.0F);
		back.setTextureOffset(12, 35).addBox(2.0F, 6.0F, 5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(6, 35).addBox(2.0F, -6.0F, 5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(30, 28).addBox(2.0F, 5.0F, 6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(30, 25).addBox(2.0F, -6.0F, 6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(0, 22).addBox(2.0F, -4.0F, 7.0F, 2.0F, 9.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(0, 35).addBox(-4.0F, 6.0F, 5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(30, 8).addBox(-4.0F, -6.0F, 5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(30, 22).addBox(-4.0F, 5.0F, 6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(30, 5).addBox(-4.0F, -6.0F, 6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		back.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, 7.0F, 2.0F, 9.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		key.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		top.render(matrixStack, buffer, packedLight, packedOverlay);
		back.render(matrixStack, buffer, packedLight, packedOverlay);
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