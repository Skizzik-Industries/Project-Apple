package com.skizzium.projectapple.entity.model;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class SkizzikFinishHimModel<T extends Skizzik> extends ListModel<T> {
	private final ModelPart[] body;
	private final ModelPart[] head;
	private final ImmutableList<ModelPart> parts;

	public SkizzikFinishHimModel() {
		texWidth = 256;
		texHeight = 256;

		body = new ModelPart[2];
		head = new ModelPart[1];

		body[0] = new ModelPart(this, 0, 16);
		body[0].setPos(0.0F, 0.0F, 0.0F);
		body[0].texOffs(73, 100).addBox(-3.0F, -11.0F, 6.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);

		body[1] = (new ModelPart(this)).setTexSize(this.texWidth, this.texHeight);
		body[1].setPos(1.0F, 11.0F, 13.0F);
		setRotationAngle(body[1], 0.6981F, 0.0F, 0.0F);
		body[1].texOffs(101, 101).addBox(-4.0F, -7.0026F, -3.3586F, 7.0F, 13.0F, 7.0F, 0.0F, false);

		head[0] = new ModelPart(this, 0, 0);
		head[0].setPos(1.0F, -20.9F, 8.5F);
		head[0].texOffs(0, 0).addBox(-8.5F, -6.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		ImmutableList.Builder<ModelPart> builder = ImmutableList.builder();
		builder.addAll(Arrays.asList(this.body));
		builder.addAll(Arrays.asList(this.head));
		this.parts = builder.build();
	}

	@Override
	public ImmutableList<ModelPart> parts() {
		return this.parts;
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		float f5 = Mth.cos(f3 * 0.1F);

		this.head[0].yRot = f3 * ((float)Math.PI / 180F);
		this.head[0].xRot = f4 * ((float)Math.PI / 180F);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}