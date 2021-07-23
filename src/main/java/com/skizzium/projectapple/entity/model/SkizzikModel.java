package com.skizzium.projectapple.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class SkizzikModel<T extends Skizzik> extends ListModel<T> {
	private final ModelPart[] body;
	private final ModelPart[] heads;
	private final ImmutableList<ModelPart> parts;

	public SkizzikModel() {
		texWidth = 256;
		texHeight = 256;

		body = new ModelPart[2];
		heads = new ModelPart[5];

		body[0] = new ModelPart(this, 0, 16);
		body[0].setPos(1.0F, 15.9F, 4.5F);
		body[0].texOffs(101, 101).addBox(-4.0F, -7.0026F, -3.3586F, 7.0F, 13.0F, 7.0F, 0.0F, false);
		setRotationAngle(body[0], 0.6981F, 0.0F, 0.0F);

		body[1] = (new ModelPart(this)).setTexSize(this.texWidth, this.texHeight);
		body[1].setPos(0.5F, 3.6105F, -10.9211F);
		body[1].texOffs(73, 100).addBox(-3.5F, -9.7105F, 8.4211F, 7.0F, 19.0F, 7.0F, 0.0F, false);
		body[1].texOffs(84, 46).addBox(-11.5F, -8.2105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[1].texOffs(0, 86).addBox(7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[1].texOffs(80, 24).addBox(-10.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[1].texOffs(60, 44).addBox(-7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[1].texOffs(0, 42).addBox(4.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[1].texOffs(0, 37).addBox(4.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[1].texOffs(0, 32).addBox(-7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[1].texOffs(64, 78).addBox(-10.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[1].texOffs(90, 76).addBox(7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body[1].texOffs(88, 0).addBox(-10.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body[1].texOffs(76, 54).addBox(7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[1].texOffs(0, 10).addBox(-7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body[1].texOffs(0, 4).addBox(4.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body[1].texOffs(48, 8).addBox(-11.5F, -2.7105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[1].texOffs(48, 0).addBox(-11.5F, 2.7895F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[1].texOffs(0, 32).addBox(-7.5F, -8.7105F, -6.5789F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		body[1].texOffs(29, 98).addBox(-2.5F, 6.2895F, -8.5789F, 5.0F, 1.0F, 17.0F, 0.0F, false);
		body[1].texOffs(0, 0).addBox(-2.5F, 4.2895F, -8.5789F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		heads[0] = new ModelPart(this, 0, 0);
		heads[0].setPos(1.0F, -16.0F, 0.0F);
		heads[0].texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		heads[1] = new ModelPart(this, 32, 0);
		heads[1].setPos(19.0F, -9.0F, 0.0F);
		heads[1].texOffs(0, 62).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		heads[2] = new ModelPart(this, 32, 0);
		heads[2].setPos(-18.0F, -11.0F, 1.0F);
		heads[2].texOffs(36, 74).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		heads[3] = new ModelPart(this, 32, 0);
		heads[3].setPos(17.0F, -31.0F, 0.0F);
		heads[3].texOffs(48, 50).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		heads[4] = new ModelPart(this, 32, 0);
		heads[4].setPos(-13.0F, -34.0F, 1.0F);
		heads[4].texOffs(52, 20).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		ImmutableList.Builder<ModelPart> builder = ImmutableList.builder();
		builder.addAll(Arrays.asList(this.heads));
		builder.addAll(Arrays.asList(this.body));
		this.parts = builder.build();
	}

	@Override
	public ImmutableList<ModelPart> parts() {
		return this.parts;
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		float f5 = Mth.cos(f3 * 0.1F);

		//this.body[1].xRot = (0.065F + 0.05F * f5) * (float)Math.PI;
		//this.body[0].setPos(1.0F, 15.9F + MathHelper.cos(this.body[1].xRot) * 10.0F, -0.5F + MathHelper.sin(this.body[1].xRot) * 10.0F);
		//this.body[0].xRot = (0.265F + 0.1F * f5) * (float)Math.PI;

		this.heads[0].yRot = f3 * ((float)Math.PI / 180F);
		this.heads[0].xRot = f4 * ((float)Math.PI / 180F);
	}

	@Override
	public void prepareMobModel(T entity, float f, float f1, float f2) {
		for(int i = 1; i < 5; ++i) {
			this.heads[i].yRot = (entity.getHeadYRot(i - 1) - entity.yBodyRot) * ((float)Math.PI / 180F);
			this.heads[i].xRot = entity.getHeadXRot(i - 1) * ((float)Math.PI / 180F);
		}
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}