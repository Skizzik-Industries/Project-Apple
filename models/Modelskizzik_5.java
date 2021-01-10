// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelskizzik_5 extends EntityModel<Entity> {
	private final ModelRenderer last_stage;
	private final ModelRenderer body;
	private final ModelRenderer block_holders;
	private final ModelRenderer block_holders2;
	private final ModelRenderer block_holders3;
	private final ModelRenderer body2;
	private final ModelRenderer head1;

	public Modelskizzik_5() {
		textureWidth = 256;
		textureHeight = 256;

		last_stage = new ModelRenderer(this);
		last_stage.setRotationPoint(0.0F, 4.9F, 12.5F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		last_stage.addChild(body);
		body.setTextureOffset(73, 100).addBox(-3.0F, -11.0F, 6.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(84, 46).addBox(-11.0F, -9.5F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(48, 8).addBox(-11.0F, -4.0F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(48, 0).addBox(-11.0F, 1.5F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 32).addBox(-7.0F, -10.0F, -19.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);

		block_holders = new ModelRenderer(this);
		block_holders.setRotationPoint(7.0F, 8.1F, 0.5F);
		last_stage.addChild(block_holders);
		setRotationAngle(block_holders, 0.0F, -0.4363F, 0.0F);
		block_holders.setTextureOffset(0, 86).addBox(4.0F, -16.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders.setTextureOffset(0, 42).addBox(1.0F, -16.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders.setTextureOffset(0, 37).addBox(1.0F, -5.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders.setTextureOffset(90, 76).addBox(4.0F, -11.6F, -12.0F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		block_holders.setTextureOffset(76, 54).addBox(4.0F, -5.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders.setTextureOffset(0, 4).addBox(1.0F, -11.6F, -12.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		block_holders2 = new ModelRenderer(this);
		block_holders2.setRotationPoint(1.0F, 8.1F, -3.5F);
		last_stage.addChild(block_holders2);
		setRotationAngle(block_holders2, 0.0F, 0.4363F, 0.0F);
		block_holders2.setTextureOffset(80, 24).addBox(-14.0F, -16.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders2.setTextureOffset(60, 44).addBox(-11.0F, -16.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders2.setTextureOffset(0, 32).addBox(-11.0F, -5.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders2.setTextureOffset(64, 78).addBox(-14.0F, -5.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders2.setTextureOffset(88, 0).addBox(-14.0F, -11.6F, -12.0F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		block_holders2.setTextureOffset(0, 10).addBox(-11.0F, -11.6F, -12.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		block_holders3 = new ModelRenderer(this);
		block_holders3.setRotationPoint(-14.0F, 14.1F, 3.5F);
		last_stage.addChild(block_holders3);
		setRotationAngle(block_holders3, 0.3491F, 0.0F, 0.0F);
		block_holders3.setTextureOffset(29, 98).addBox(12.0F, -7.1F, -11.5F, 5.0F, 1.0F, 17.0F, 0.0F, false);
		block_holders3.setTextureOffset(0, 0).addBox(12.0F, -9.1F, -11.5F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(1.0F, 11.0F, 13.0F);
		last_stage.addChild(body2);
		setRotationAngle(body2, 0.6981F, 0.0F, 0.0F);
		body2.setTextureOffset(101, 101).addBox(-4.0F, -7.0026F, -3.3586F, 7.0F, 13.0F, 7.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(1.0F, -20.9F, 8.5F);
		last_stage.addChild(head1);
		head1.setTextureOffset(0, 0).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
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