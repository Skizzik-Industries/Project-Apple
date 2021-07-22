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
public class SkizzikSleepingModel<T extends Skizzik> extends SegmentedModel<T> {
	private final ModelRenderer[] body;
	private final ModelRenderer[] heads;
	private final ImmutableList<ModelRenderer> parts;

	public SkizzikSleepingModel() {
		texWidth = 256;
		texHeight = 256;

		body = new ModelRenderer[1];
		heads = new ModelRenderer[5];

		body[0] = new ModelRenderer(this, 0, 16);
		body[0].setPos(0.5F, -7.3895F, -10.9211F);
		body[0].texOffs(73, 100).addBox(-3.5F, -9.7105F, 8.4211F, 7.0F, 19.0F, 7.0F, 0.0F, false);
		body[0].texOffs(84, 46).addBox(-11.5F, -8.2105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[0].texOffs(0, 86).addBox(7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[0].texOffs(80, 24).addBox(-10.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[0].texOffs(60, 44).addBox(-7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[0].texOffs(0, 42).addBox(4.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[0].texOffs(0, 37).addBox(4.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[0].texOffs(0, 32).addBox(-7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		body[0].texOffs(64, 78).addBox(-10.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[0].texOffs(90, 76).addBox(7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body[0].texOffs(88, 0).addBox(-10.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
		body[0].texOffs(76, 54).addBox(7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
		body[0].texOffs(0, 10).addBox(-7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body[0].texOffs(0, 4).addBox(4.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		body[0].texOffs(48, 8).addBox(-11.5F, -2.7105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[0].texOffs(48, 0).addBox(-11.5F, 2.7895F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
		body[0].texOffs(0, 32).addBox(-7.5F, -8.7105F, -6.5789F, 15.0F, 15.0F, 15.0F, 0.0F, false);
		body[0].texOffs(29, 98).addBox(-2.5F, 6.2895F, -8.5789F, 5.0F, 1.0F, 17.0F, 0.0F, false);
		body[0].texOffs(0, 0).addBox(-2.5F, 4.2895F, -8.5789F, 5.0F, 2.0F, 2.0F, 0.0F, false);

		heads[0] = new ModelRenderer(this);
		heads[0].setPos(1.0F, -40.0F, 0.0F);
		heads[0].texOffs(0, 0).addBox(-9.0F, 7.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		heads[1] = new ModelRenderer(this);
		heads[1].setPos(19.0F, -33.0F, 0.0F);
		heads[1].texOffs(0, 62).addBox(-6.0F, 21.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		heads[2] = new ModelRenderer(this);
		heads[2].setPos(-18.0F, -35.0F, 1.0F);
		heads[2].texOffs(36, 74).addBox(-6.0F, 23.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		heads[3] = new ModelRenderer(this);
		heads[3].setPos(-17.0F, -19.0F, 1.0F);
		heads[3].texOffs(52, 20).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		heads[4] = new ModelRenderer(this);
		heads[4].setPos(17.0F, -55.0F, 0.0F);
		heads[4].texOffs(48, 50).addBox(-5.0F, 31.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
		builder.addAll(Arrays.asList(this.heads));
		builder.addAll(Arrays.asList(this.body));
		this.parts = builder.build();
	}

	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}

	@Override
	public ImmutableList<ModelRenderer> parts() {
		return this.parts;
	}
}