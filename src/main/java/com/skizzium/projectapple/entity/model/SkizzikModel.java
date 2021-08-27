package com.skizzium.projectapple.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class SkizzikModel<T extends Skizzik> extends SegmentedModel<T> {
	private final ModelRenderer[] body; // 0 - tail; 1 - spine;
	private final ModelRenderer commandBlock;
	private final ModelRenderer[] ribs; // 0 - right_ribs; 1 - left_ribs; 2 - bottom_rib; 3 - back_ribs;
	private final ModelRenderer[] heads; // 0 - center_head; 1 - bottom_right_head; 2 - bottom_left_head; 3 - top_right_head; 4 - top_left_head;
	private final ImmutableList<ModelRenderer> parts;

	public SkizzikModel() {
		texWidth = 256;
		texHeight = 256;

		body = new ModelRenderer[2];
		commandBlock = new ModelRenderer(this);
		ribs = new ModelRenderer[4];
		heads = new ModelRenderer[5];

		body[0] = new ModelRenderer(this);
		body[0].setPos(1.0F, 15.9F, 0.7F);
		this.setRotation(body[0], 0.7F, 0.0F, 0.0F);
		body[0].texOffs(101, 101).addBox(-4.0F, -7.0F, -3.4F, 7.0F, 13.0F, 7.0F, false);

		body[1] = new ModelRenderer(this);
		body[1].setPos(0.5F, 4.0F, -11.0F);
		body[1].texOffs(73, 100).addBox(-3.5F, -9.7F, 8.4F, 7.0F, 19.0F, 7.0F, false);

		commandBlock.setPos(0.0F, 0.0F, 0.0F);
		commandBlock.texOffs(0, 32).addBox(-7.5F, -8.7F, -6.6F, 15.0F, 15.0F, 15.0F, false);
		body[1].addChild(commandBlock);

		ribs[0] = new ModelRenderer(this);
		ribs[0].setPos(9.5F, -1.0F, 12.0F);
		ribs[0].texOffs(0, 86).addBox(-2.0F, -6.6F, -21.0F, 3.0F, 2.0F, 20.0F, false);
		ribs[0].texOffs(90, 76).addBox(-2.0F, -1.6F, -21.0F, 3.0F, 3.0F, 19.0F, false);
		ribs[0].texOffs(76, 54).addBox(-2.0F, 4.4F, -21.0F, 3.0F, 2.0F, 20.0F, false);
		ribs[0].texOffs(0, 37).addBox(-5.0F, 4.4F, -21.0F, 3.0F, 2.0F, 3.0F, false);
		ribs[0].texOffs(0, 4).addBox(-5.0F, -1.6F, -21.0F, 3.0F, 3.0F, 3.0F, false);
		ribs[0].texOffs(0, 42).addBox(-5.0F, -6.6F, -21.0F, 3.0F, 2.0F, 3.0F, false);
		body[1].addChild(ribs[0]);

		ribs[1] = new ModelRenderer(this);
		ribs[1].setPos(-9.0F, -0.5F, 12.0F);
		ribs[1].texOffs(64, 78).addBox(-1.5F, 3.9F, -21.0F, 3.0F, 2.0F, 20.0F, false);
		ribs[1].texOffs(0, 32).addBox(1.5F, 3.9F, -21.0F, 3.0F, 2.0F, 3.0F, false);
		ribs[1].texOffs(88, 0).addBox(-1.5F, -2.1F, -21.0F, 3.0F, 3.0F, 19.0F, false);
		ribs[1].texOffs(0, 10).addBox(1.5F, -2.1F, -21.0F, 3.0F, 3.0F, 3.0F, false);
		ribs[1].texOffs(80, 24).addBox(-1.5F, -7.1F, -21.0F, 3.0F, 2.0F, 20.0F, false);
		ribs[1].texOffs(60, 44).addBox(1.5F, -7.1F, -21.0F, 3.0F, 2.0F, 3.0F, false);
		body[1].addChild(ribs[1]);

		ribs[2] = new ModelRenderer(this);
		ribs[2].setPos(0.0F, 7.0F, 12.0F);
		ribs[2].texOffs(0, 0).addBox(-2.5F, -2.0F, -20.5F, 5.0F, 2.0F, 2.0F, false);
		ribs[2].texOffs(29, 98).addBox(-2.5F, -1.0F, -18.5F, 5.0F, 1.0F, 17.0F, false);
		body[1].addChild(ribs[2]);

		ribs[3] = new ModelRenderer(this);
		ribs[3].setPos(0.0F, -1.1F, 12.0F);
		ribs[3].texOffs(48, 0).addBox(-11.5F, 3.5F, -2.0F, 23.0F, 4.0F, 4.0F, false);
		ribs[3].texOffs(84, 46).addBox(-11.5F, -7.5F, -2.0F, 23.0F, 4.0F, 4.0F, false);
		ribs[3].texOffs(48, 8).addBox(-11.5F, -2.0F, -2.0F, 23.0F, 4.0F, 4.0F, false);
		body[1].addChild(ribs[3]);

		heads[0] = new ModelRenderer(this);
		heads[0].setPos(1.0F, -16.0F, 0.0F);
		heads[0].texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, false);

		heads[1] = new ModelRenderer(this);
		heads[1].setPos(19.0F, -9.0F, 0.0F);
		heads[1].texOffs(0, 62).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, false);

		heads[2] = new ModelRenderer(this);
		heads[2].setPos(-18.0F, -11.0F, 1.0F);
		heads[2].texOffs(36, 74).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, false);

		heads[3] = new ModelRenderer(this);
		heads[3].setPos(17.0F, -31.0F, 0.0F);
		heads[3].texOffs(48, 50).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, false);

		heads[4] = new ModelRenderer(this);
		heads[4].setPos(-13.0F, -34.0F, 1.0F);
		heads[4].texOffs(52, 20).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, false);

		ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
		builder.addAll(Arrays.asList(this.heads));
		builder.addAll(Arrays.asList(this.body));
		this.parts = builder.build();
	}

	@Override
	public ImmutableList<ModelRenderer> parts() {
		return this.parts;
	}

	@Override
	public void prepareMobModel(T entity, float f, float f1, float f2) {
		for(int i = 1; i < 5; ++i) {
			this.heads[i].yRot = (entity.getHeadYRot(i - 1) - entity.yBodyRot) * ((float)Math.PI / 180F);
			this.heads[i].xRot = entity.getHeadXRot(i - 1) * ((float)Math.PI / 180F);
		}
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		this.heads[0].yRot = f3 * ((float)Math.PI / 180F);
		this.heads[0].xRot = f4 * ((float)Math.PI / 180F);

		if (entity.getStage() == 0) {
			this.heads[0].setPos(1.0F, -1.0F, 0.0F);
			this.heads[1].setPos(19.0F, 18.0F, 0.0F);
			this.heads[2].setPos(-18.0F, 18.0F, 1.0F);
			this.heads[3].setPos(18.0F, 6.0F, 0.0F);
			this.heads[4].setPos(-17.0F, 5.0F, 1.0F);

			this.body[1].setPos(0.5F, 17.0F, -11.0F);
			this.body[0].setPos(4.0F, 20.9F, 7.5F);
			this.setRotation(body[0], 1.5F, 0.6F, 0.0F);
		}
		else if (entity.getStage() == 2) {
			this.heads[3].setPos(-4.0F, -33.0F, 1.0F);
		}
		else if (entity.getStage() == 3) {
			this.heads[1].setPos(19.0F, -13.0F, 0.0F);
		}
		else if (entity.getStage() == 4) {
			this.heads[1].setPos(11.0F, -32.0F, 0.0F);
		}
		else if (entity.getStage() == 5) {
			this.heads[0].setPos(1.0F, -21.0F, 0.0F);

			this.commandBlock.setPos(0.0F, 0.0F, -10.0F);

			this.setRotation(ribs[0], 0.0F, -0.35F, 0.0F);
			this.setRotation(ribs[1], 0.0F, 0.35F, 0.0F);
			this.setRotation(ribs[2], 0.25F, 0.0F, 0.0F);
		}
		else if (entity.getStage() == 6) {
			this.heads[0].setPos(1.0F, -13.0F, 0.0F);

			this.commandBlock.visible = false;

			this.ribs[0].visible = false;
			this.ribs[1].visible = false;
			this.ribs[2].visible = false;
			this.ribs[3].visible = false;
		}
		setDefaults(entity);

		if (entity.getStage() == 2) {
			this.heads[1].visible = true;
			this.heads[2].visible = true;
			this.heads[3].visible = true;
			this.heads[4].visible = false;
		}
		else if (entity.getStage() == 3) {
			this.heads[1].visible = true;
			this.heads[2].visible = true;
			this.heads[3].visible = false;
			this.heads[4].visible = false;
		}
		else if (entity.getStage() == 4) {
			this.heads[1].visible = true;
			this.heads[2].visible = false;
			this.heads[3].visible = false;
			this.heads[4].visible = false;
		}
		else if (entity.getStage() == 5 || entity.getStage() == 6) {
			this.heads[1].visible = false;
			this.heads[2].visible = false;
			this.heads[3].visible = false;
			this.heads[4].visible = false;
		}
		else {
			this.heads[1].visible = true;
			this.heads[2].visible = true;
			this.heads[3].visible = true;
			this.heads[4].visible = true;
		}
	}

	private void setDefaults(Skizzik entity) {
		if (entity.getStage() != 0 && entity.getStage() != 5 && entity.getStage() != 6) {
			this.heads[0].setPos(1.0F, -16.0F, 0.0F);
		}

		if (entity.getStage() != 0) {
			if (entity.getStage() < 2 && entity.getStage() != 0) {
				this.heads[3].setPos(17.0F, -31.0F, 0.0F);
			}
			if (entity.getStage() < 4) {
				this.heads[1].setPos(19.0F, -9.0F, 0.0F);
				this.heads[2].setPos(-18.0F, -11.0F, 1.0F);
			}
		}

		if (entity.getStage() != 0) {
			this.heads[4].setPos(-13.0F, -34.0F, 1.0F);

			this.body[1].setPos(0.5F, 4.0F, -11.0F);
			this.setRotation(body[0], 0.7F, 0.0F, 0.0F);
			this.body[0].setPos(1.0F, 15.9F, 4.5F);
		}

		if (entity.getStage() != 5) {
			this.commandBlock.setPos(0.0F, 0.0F, 0.0F);

			this.setRotation(ribs[0], 0.0F, 0.0F, 0.0F);
			this.setRotation(ribs[1], 0.0F, 0.0F, 0.0F);
			this.setRotation(ribs[2], 0.0F, 0.0F, 0.0F);
		}

		if (entity.getStage() != 6) {
			this.commandBlock.visible = true;

			this.ribs[0].visible = true;
			this.ribs[1].visible = true;
			this.ribs[2].visible = true;
			this.ribs[3].visible = true;
		}
	}
}