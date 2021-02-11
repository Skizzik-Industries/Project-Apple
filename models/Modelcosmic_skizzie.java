// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelcosmic_skizzie extends EntityModel<Entity> {
	private final ModelRenderer cosmic_skizzie;
	private final ModelRenderer body;
	private final ModelRenderer body2;
	private final ModelRenderer head1;

	public Modelcosmic_skizzie() {
		textureWidth = 128;
		textureHeight = 128;

		cosmic_skizzie = new ModelRenderer(this);
		cosmic_skizzie.setRotationPoint(0.0F, 17.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(-0.5F, -6.0F, 1.0F);
		cosmic_skizzie.addChild(body);
		setRotationAngle(body, 0.4363F, 0.0F, 0.0F);
		body.setTextureOffset(46, 19).addBox(-2.5F, 1.2901F, -2.4515F, 5.0F, 8.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(32, 48).addBox(-1.5F, 0.2901F, -1.4515F, 3.0F, 11.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-0.5F, 10.2901F, -0.4515F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(-0.5F, -17.1F, 1.0F);
		cosmic_skizzie.addChild(body2);
		body2.setTextureOffset(0, 44).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 14.0F, 5.0F, 0.0F, false);
		body2.setTextureOffset(20, 44).addBox(-1.5F, -9.0F, -1.5F, 3.0F, 18.0F, 3.0F, 0.0F, false);
		body2.setTextureOffset(44, 48).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 20.0F, 1.0F, 0.0F, false);
		body2.setTextureOffset(38, 40).addBox(-8.5F, -6.5F, -2.0F, 17.0F, 4.0F, 4.0F, 0.0F, false);
		body2.setTextureOffset(36, 0).addBox(-8.5F, -2.0F, -2.0F, 17.0F, 4.0F, 4.0F, 0.0F, false);
		body2.setTextureOffset(0, 36).addBox(-8.5F, 2.5F, -2.0F, 17.0F, 4.0F, 4.0F, 0.0F, false);
		body2.setTextureOffset(0, 32).addBox(-10.5F, 3.5F, -1.0F, 21.0F, 2.0F, 2.0F, 0.0F, false);
		body2.setTextureOffset(0, 28).addBox(-10.5F, -1.0F, -1.0F, 21.0F, 2.0F, 2.0F, 0.0F, false);
		body2.setTextureOffset(0, 24).addBox(-10.5F, -5.5F, -1.0F, 21.0F, 2.0F, 2.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(-0.5F, -34.0F, 0.0F);
		cosmic_skizzie.addChild(head1);
		head1.setTextureOffset(0, 0).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head1.setTextureOffset(48, 48).addBox(-5.0F, -8.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		head1.setTextureOffset(48, 56).addBox(-5.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		head1.setTextureOffset(40, 32).addBox(3.0F, -8.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		head1.setTextureOffset(48, 8).addBox(4.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		cosmic_skizzie.render(matrixStack, buffer, packedLight, packedOverlay);
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