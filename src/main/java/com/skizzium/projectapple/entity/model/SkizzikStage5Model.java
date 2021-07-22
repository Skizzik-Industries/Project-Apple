package com.skizzium.projectapple.entity.model;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class SkizzikStage5Model<T extends Skizzik> extends SegmentedModel<T> {
	private final ModelRenderer[] body;
	private final ModelRenderer[] block_holders;
	private final ModelRenderer[] head;
	private final ImmutableList<ModelRenderer> parts;

	public SkizzikStage5Model() {
		texWidth = 256;
		texHeight = 256;

		body = new ModelRenderer[2];
		block_holders = new ModelRenderer[3];
		head = new ModelRenderer[1];

		body[0] = new ModelRenderer(this, 0, 16);
		body[0].setPos(1.0F, 11.0F, 13.0F);
		setRotationAngle(body[0], 0.6981F, 0.0F, 0.0F);
		body[0].texOffs(101, 101).addBox(-4.0F, -7.0026F, -3.3586F, 7.0F, 13.0F, 7.0F, 0.0F, false);

		body[1] = (new ModelRenderer(this)).setTexSize(this.texWidth, this.texHeight);
		body[1].setPos(0.0F, 0.0F, 0.0F);
		body[1].texOffs(73, 100).addBox(-3.0F, -11.0F, 6.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);
		body[1].texOffs(84, 46).addBox(-11.0F, -9.5F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[1].texOffs(48, 8).addBox(-11.0F, -4.0F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[1].texOffs(48, 0).addBox(-11.0F, 1.5F, 7.5F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[1].texOffs(0, 32).addBox(-7.0F, -10.0F, -19.0F, 15.0F, 15.0F, 15.0F, 0.0F, false);

		block_holders[0] = (new ModelRenderer(this)).setTexSize(this.texWidth, this.texHeight);
		block_holders[0].setPos(7.0F, 8.1F, 0.5F);
		setRotationAngle(block_holders[0], 0.0F, -0.4363F, 0.0F);
		block_holders[0].texOffs(0, 86).addBox(4.0F, -16.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders[0].texOffs(0, 42).addBox(1.0F, -16.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders[0].texOffs(0, 37).addBox(1.0F, -5.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders[0].texOffs(90, 76).addBox(4.0F, -11.6F, -12.0F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		block_holders[0].texOffs(76, 54).addBox(4.0F, -5.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders[0].texOffs(0, 4).addBox(1.0F, -11.6F, -12.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		block_holders[1] = (new ModelRenderer(this)).setTexSize(this.texWidth, this.texHeight);
		block_holders[1].setPos(1.0F, 8.1F, -3.5F);
		setRotationAngle(block_holders[1], 0.0F, 0.4363F, 0.0F);
		block_holders[1].texOffs(80, 24).addBox(-14.0F, -16.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders[1].texOffs(60, 44).addBox(-11.0F, -16.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders[1].texOffs(0, 32).addBox(-11.0F, -5.6F, -12.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		block_holders[1].texOffs(64, 78).addBox(-14.0F, -5.6F, -12.0F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		block_holders[1].texOffs(88, 0).addBox(-14.0F, -11.6F, -12.0F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		block_holders[1].texOffs(0, 10).addBox(-11.0F, -11.6F, -12.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		block_holders[2] = (new ModelRenderer(this)).setTexSize(this.texWidth, this.texHeight);
		block_holders[2].setPos(-14.0F, 14.1F, 3.5F);
		setRotationAngle(block_holders[2], 0.3491F, 0.0F, 0.0F);
		block_holders[2].texOffs(29, 98).addBox(12.0F, -7.1F, -11.5F, 5.0F, 1.0F, 17.0F, 0.0F, false);
		block_holders[2].texOffs(0, 0).addBox(12.0F, -9.1F, -11.5F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		head[0] = new ModelRenderer(this, 0, 0);
		head[0].setPos(1.0F, -20.9F, 8.5F);
		head[0].texOffs(0, 0).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
		builder.addAll(Arrays.asList(this.head));
		builder.addAll(Arrays.asList(this.block_holders));
		builder.addAll(Arrays.asList(this.body));
		this.parts = builder.build();
	}

	@Override
	public ImmutableList<ModelRenderer> parts() {
		return this.parts;
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		float f5 = MathHelper.cos(f3 * 0.1F);

		this.head[0].yRot = f3 * ((float)Math.PI / 180F);
		this.head[0].xRot = f4 * ((float)Math.PI / 180F);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}