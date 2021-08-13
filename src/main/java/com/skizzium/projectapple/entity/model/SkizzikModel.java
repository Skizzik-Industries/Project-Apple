package com.skizzium.projectapple.entity.model;

import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SkizzikModel<T extends Skizzik> extends HierarchicalModel<T> {
	private final ModelPart root;

	private final ModelPart tail;
	private final ModelPart body;

	private final ModelPart commandBlock;

	private final ModelPart rightRibs;
	private final ModelPart leftRibs;
	private final ModelPart bottomRib;
	private final ModelPart backRibs;

	private final ModelPart centerHead;
	private final ModelPart bottomRightHead;
	private final ModelPart bottomLeftHead;
	private final ModelPart topRightHead;
	private final ModelPart topLeftHead;

	public SkizzikModel(ModelPart part) {
		this.root = part;

		this.tail = part.getChild("tail");
		this.body = part.getChild("body");

		this.commandBlock = this.body.getChild("command_block");

		this.rightRibs = this.body.getChild("right_ribs");
		this.leftRibs = this.body.getChild("left_ribs");
		this.bottomRib = this.body.getChild("bottom_rib");
		this.backRibs = this.body.getChild("back_ribs");

		this.centerHead = part.getChild("center_head");
		this.bottomRightHead = part.getChild("bottom_right_head");
		this.bottomLeftHead = part.getChild("bottom_left_head");
		this.topRightHead = part.getChild("top_right_head");
		this.topLeftHead = part.getChild("top_left_head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(101, 101).addBox(-4.0F, -7.0F, -3.4F, 7.0F, 13.0F, 7.0F, false), PartPose.offsetAndRotation(1.0F, 15.9F, 4.5F, 0.7F, 0.0F, 0.0F));
		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(73, 100).addBox(-3.5F, -9.7F, 8.4F, 7.0F, 19.0F, 7.0F, false), PartPose.offset(0.5F, 4.0F, -11.0F));

		body.addOrReplaceChild("command_block", CubeListBuilder.create().texOffs(0, 32).addBox(-7.5F, -8.7F, -6.6F, 15.0F, 15.0F, 15.0F, false), PartPose.ZERO);

		body.addOrReplaceChild("right_ribs", CubeListBuilder.create().texOffs(0, 86).addBox(-2.0F, -6.6F, -21.0F, 3.0F, 2.0F, 20.0F, false)
																				.texOffs(90, 76).addBox(-2.0F, -1.6F, -21.0F, 3.0F, 3.0F, 19.0F, false)
																				.texOffs(76, 54).addBox(-2.0F, 4.4F, -21.0F, 3.0F, 2.0F, 20.0F, false)
																				.texOffs(0, 37).addBox(-5.0F, 4.4F, -21.0F, 3.0F, 2.0F, 3.0F, false)
																				.texOffs(0, 4).addBox(-5.0F, -1.6F, -21.0F, 3.0F, 3.0F, 3.0F, false)
																				.texOffs(0, 42).addBox(-5.0F, -6.6F, -21.0F, 3.0F, 2.0F, 3.0F, false), PartPose.offset(9.5F, -1.0F, 12.0F));
		body.addOrReplaceChild("left_ribs", CubeListBuilder.create().texOffs(64, 78).addBox(-1.5F, 3.9F, -21.0F, 3.0F, 2.0F, 20.0F, false)
																				.texOffs(0, 32).addBox(1.5F, 3.9F, -21.0F, 3.0F, 2.0F, 3.0F, false)
																				.texOffs(88, 0).addBox(-1.5F, -2.1F, -21.0F, 3.0F, 3.0F, 19.0F, false)
																				.texOffs(0, 10).addBox(1.5F, -2.1F, -21.0F, 3.0F, 3.0F, 3.0F, false)
																				.texOffs(80, 24).addBox(-1.5F, -7.1F, -21.0F, 3.0F, 2.0F, 20.0F, false)
																				.texOffs(60, 44).addBox(1.5F, -7.1F, -21.0F, 3.0F, 2.0F, 3.0F, false), PartPose.offset(-9.0F, -0.5F, 12.0F));
		body.addOrReplaceChild("bottom_rib", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.0F, -20.5F, 5.0F, 2.0F, 2.0F, false)
																				.texOffs(29, 98).addBox(-2.5F, -1.0F, -18.5F, 5.0F, 1.0F, 17.0F, false), PartPose.offset(0.0F, 7.0F, 12.0F));
		body.addOrReplaceChild("back_ribs", CubeListBuilder.create().texOffs(48, 0).addBox(-11.5F, 3.5F, -2.0F, 23.0F, 4.0F, 4.0F, false)
																				.texOffs(84, 46).addBox(-11.5F, -7.5F, -2.0F, 23.0F, 4.0F, 4.0F, false)
																				.texOffs(48, 8).addBox(-11.5F, -2.0F, -2.0F, 23.0F, 4.0F, 4.0F, false), PartPose.offset(0.0F, -1.1F, 12.0F));

		root.addOrReplaceChild("center_head", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, false), PartPose.offset(1.0F, -16.0F, 0.0F));
		root.addOrReplaceChild("bottom_right_head", CubeListBuilder.create().texOffs(0, 62).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, false), PartPose.offset(19.0F, -9.0F, 0.0F));
		root.addOrReplaceChild("bottom_left_head", CubeListBuilder.create().texOffs(0, 62).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, false), PartPose.offset(-18.0F, -11.0F, 1.0F));
		root.addOrReplaceChild("top_right_head", CubeListBuilder.create().texOffs(0, 62).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, false), PartPose.offset(17.0F, -31.0F, 0.0F));
		root.addOrReplaceChild("top_left_head", CubeListBuilder.create().texOffs(0, 62).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, false), PartPose.offset(-13.0F, -34.0F, 1.0F));

		return LayerDefinition.create(mesh, 256, 256);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
//		float f5 = Mth.cos(f2 * 0.1F);
//		this.body.xRot = (0.065F + 0.05F * f5) * 3.1415927F;
//
//		this.tail.setPos(-2.0F, 6.9F + Mth.cos(this.body.xRot) * 10.0F, -0.5F + Mth.sin(this.body.xRot) * 10.0F);
//		this.tail.xRot = (0.265F + 0.1F * f5) * 3.1415927F;

		this.centerHead.yRot = f3 * ((float)Math.PI / 180F);
		this.centerHead.xRot = f4 * ((float)Math.PI / 180F);

		if (entity.getStage() == 0) {
			this.centerHead.setPos(1.0F, -1.0F, 0.0F);
			this.bottomRightHead.setPos(19.0F, 18.0F, 0.0F);
			this.bottomLeftHead.setPos(-18.0F, 18.0F, 1.0F);
			this.topRightHead.setPos(18.0F, 6.0F, 0.0F);
			this.topLeftHead.setPos(-17.0F, 5.0F, 1.0F);

			this.body.setPos(0.5F, 17.0F, -11.0F);
			this.tail.setRotation(1.5F, 0.6F, 0.0F);
			this.tail.setPos(4.0F, 20.9F, 7.5F);
		}
		else if (entity.getStage() == 2) {
			this.topRightHead.setPos(-4.0F, -33.0F, 1.0F);
		}
		else if (entity.getStage() == 3) {
			this.bottomRightHead.setPos(19.0F, -13.0F, 0.0F);
		}
		else if (entity.getStage() == 4) {
			this.bottomRightHead.setPos(11.0F, -32.0F, 0.0F);
		}
		else if (entity.getStage() == 5) {
			this.centerHead.setPos(1.0F, -21.0F, 0.0F);

			this.commandBlock.setPos(0.0F, 0.0F, -10.0F);

			this.rightRibs.setRotation(0.0F, -0.35F, 0.0F);
			this.leftRibs.setRotation(0.0F, 0.35F, 0.0F);
			this.bottomRib.setRotation(0.25F, 0.0F, 0.0F);
		}
		else if (entity.getStage() == 6) {
			this.centerHead.setPos(1.0F, -13.0F, 0.0F);

			this.commandBlock.visible = false;

			this.rightRibs.visible = false;
			this.leftRibs.visible = false;
			this.bottomRib.visible = false;
			this.backRibs.visible = false;
		}
		setDefaults(entity);

		if (entity.getStage() == 2) {
			this.bottomRightHead.visible = true;
			this.bottomLeftHead.visible = true;
			this.topRightHead.visible = true;
			this.topLeftHead.visible = false;
		}
		else if (entity.getStage() == 3) {
			this.bottomRightHead.visible = true;
			this.bottomLeftHead.visible = true;
			this.topRightHead.visible = false;
			this.topLeftHead.visible = false;
		}
		else if (entity.getStage() == 4) {
			this.bottomRightHead.visible = true;
			this.bottomLeftHead.visible = false;
			this.topRightHead.visible = false;
			this.topLeftHead.visible = false;
		}
		else if (entity.getStage() == 5 || entity.getStage() == 6) {
			this.bottomRightHead.visible = false;
			this.bottomLeftHead.visible = false;
			this.topRightHead.visible = false;
			this.topLeftHead.visible = false;
		}
		else {
			this.bottomRightHead.visible = true;
			this.bottomLeftHead.visible = true;
			this.topRightHead.visible = true;
			this.topLeftHead.visible = true;
		}
	}

	@Override
	public void prepareMobModel(T entity, float f, float f1, float f2) {


		setupHeadRotation(entity, this.bottomRightHead, 0);
		setupHeadRotation(entity, this.bottomLeftHead, 1);
		setupHeadRotation(entity, this.topRightHead, 2);
		setupHeadRotation(entity, this.topLeftHead, 3);
	}

	private void setDefaults(Skizzik entity) {
		if (entity.getStage() != 0 && entity.getStage() != 5 && entity.getStage() != 6) {
			this.centerHead.setPos(1.0F, -16.0F, 0.0F);
		}

		if (entity.getStage() != 0) {
			if (entity.getStage() < 2 && entity.getStage() != 0) {
				this.topRightHead.setPos(17.0F, -31.0F, 0.0F);
			}
			if (entity.getStage() < 4) {
				this.bottomRightHead.setPos(19.0F, -9.0F, 0.0F);
				this.bottomLeftHead.setPos(-18.0F, -11.0F, 1.0F);
			}
		}

		if (entity.getStage() != 0) {
			this.topLeftHead.setPos(-13.0F, -34.0F, 1.0F);

			this.body.setPos(0.5F, 4.0F, -11.0F);
			this.tail.setRotation(0.7F, 0.0F, 0.0F);
			this.tail.setPos(1.0F, 15.9F, 4.5F);
		}

		if (entity.getStage() != 5) {
			this.commandBlock.setPos(0.0F, 0.0F, 0.0F);

			this.rightRibs.setRotation(0.0F, 0.0F, 0.0F);
			this.leftRibs.setRotation(0.0F, 0.0F, 0.0F);
			this.bottomRib.setRotation(0.0F, 0.0F, 0.0F);
		}

		if (entity.getStage() != 6) {
			this.commandBlock.visible = true;

			this.rightRibs.visible = true;
			this.leftRibs.visible = true;
			this.bottomRib.visible = true;
			this.backRibs.visible = true;
		}
	}

	private static <T extends Skizzik> void setupHeadRotation(T entity, ModelPart model, int i) {
		if (model.visible) {
			model.yRot = (entity.getHeadYRot(i) - entity.yBodyRot) * 0.017453292F;
			model.xRot = entity.getHeadXRot(i) * 0.017453292F;
		}
	}
}