// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelskizzik_finish_him extends EntityModel<Entity> {
	private final ModelRenderer last_stage;
	private final ModelRenderer body;
	private final ModelRenderer body2;
	private final ModelRenderer head1;

	public Modelskizzik_finish_him() {
		textureWidth = 256;
		textureHeight = 256;

		last_stage = new ModelRenderer(this);
		last_stage.setRotationPoint(0.0F, 5.9F, -9.5F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		last_stage.addChild(body);
		body.setTextureOffset(73, 100).addBox(-3.0F, -11.0F, 6.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(1.0F, 11.0F, 13.0F);
		last_stage.addChild(body2);
		setRotationAngle(body2, 0.6981F, 0.0F, 0.0F);
		body2.setTextureOffset(101, 101).addBox(-4.0F, -7.0026F, -3.3586F, 7.0F, 13.0F, 7.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(1.0F, -20.9F, 8.5F);
		last_stage.addChild(head1);
		head1.setTextureOffset(0, 0).addBox(-8.5F, -6.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		last_stage.render(matrixStack, buffer, packedLight, packedOverlay);
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