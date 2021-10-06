package com.skizzium.projectapple.entity.boss.skizzik.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart lowerBody;
	private final ModelPart upperBody;
	private final ModelPart head;

	private final ModelPart hat;
	private final ModelPart layer1;
	private final ModelPart layer2;
	private final ModelPart layer3;
	private final ModelPart top;

	public WitchSkizzieModel(ModelPart part) {
		this.lowerBody = part.getChild("lower_body");
		this.upperBody = part.getChild("upper_body");
		this.head = part.getChild("head");

		this.hat = head.getChild("hat");
		this.layer1 = hat.getChild("layer_1");
		this.layer2 = hat.getChild("layer_2");
		this.layer3 = hat.getChild("layer_3");
		this.top = hat.getChild("top");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild("lower_body", CubeListBuilder.create().texOffs(13, 26).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, false), PartPose.offsetAndRotation(2.0F, 17.0F, -0.5F, 0.5F, 0.0F, 0.0F));
		root.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, false)
																			 .texOffs(3, 17).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, false)
																			 .texOffs(3, 17).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, false)
																			 .texOffs(3, 17).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, false), PartPose.offset(2.0F, 7.0F, -0.5F));
		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, false), PartPose.offset(0.0F, 3.0F, 0.0F));
		
		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(-0.5F, 21.0F, 0.0F));
		hat.addOrReplaceChild("layer_1", CubeListBuilder.create().texOffs(59, 12).addBox(-1.0F, -26.0F, -4.0F, 2.0F, 1.0F, 8.0F, false)
																		  .texOffs(59, 22).addBox(-4.0F, -26.0F, -1.0F, 8.0F, 1.0F, 2.0F, false)
																		  .texOffs(33, 16).addBox(-3.0F, -26.0F, -3.0F, 2.0F, 1.0F, 2.0F, false)
																		  .texOffs(33, 16).addBox(1.0F, -26.0F, -3.0F, 2.0F, 1.0F, 2.0F, false)
																		  .texOffs(33, 16).addBox(1.0F, -26.0F, 1.0F, 2.0F, 1.0F, 2.0F, false)
																		  .texOffs(33, 16).addBox(-3.0F, -26.0F, 1.0F, 2.0F, 1.0F, 2.0F, false), PartPose.ZERO);
		hat.addOrReplaceChild("layer_2", CubeListBuilder.create().texOffs(42, 8).addBox(-1.0F, -28.0F, -3.0F, 2.0F, 2.0F, 6.0F, false)
																		  .texOffs(42, 17).addBox(-3.0F, -28.0F, -1.0F, 6.0F, 2.0F, 2.0F, false)
																		  .texOffs(35, 0).addBox(-2.0F, -28.0F, -2.0F, 1.0F, 2.0F, 1.0F, false)
																		  .texOffs(35, 4).addBox(1.0F, -28.0F, -2.0F, 1.0F, 2.0F, 1.0F, false)
																		  .texOffs(35, 8).addBox(1.0F, -28.0F, 1.0F, 1.0F, 2.0F, 1.0F, false)
																		  .texOffs(35, 12).addBox(-2.0F, -28.0F, 1.0F, 1.0F, 2.0F, 1.0F, false), PartPose.ZERO);
		hat.addOrReplaceChild("layer_3", CubeListBuilder.create().texOffs(63, 0).addBox(-1.0F, -30.0F, -2.0F, 2.0F, 2.0F, 4.0F, false)
															  			  .texOffs(63, 7).addBox(-2.0F, -30.0F, -1.0F, 4.0F, 2.0F, 2.0F, false), PartPose.ZERO);
		hat.addOrReplaceChild("top", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -33.0F, -1.0F, 1.0F, 1.0F, 1.0F, false)
																	  .texOffs(46, 3).addBox(-1.0F, -32.0F, -1.0F, 2.0F, 2.0F, 2.0F, false), PartPose.ZERO);
		
		return LayerDefinition.create(mesh, 79, 35);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		this.head.yRot = f3 / (180F / (float) Math.PI);
		this.head.xRot = f4 / (180F / (float) Math.PI);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		lowerBody.render(matrix, buffer, light, overlay);
		upperBody.render(matrix, buffer, light, overlay);
		head.render(matrix, buffer, light, overlay);
	}
}