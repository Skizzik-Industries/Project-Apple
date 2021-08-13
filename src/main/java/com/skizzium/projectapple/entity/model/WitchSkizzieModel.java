package com.skizzium.projectapple.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.WitchSkizzie;
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

	public WitchSkizzieModel(ModelPart part) {
		this.lowerBody = part.getChild("lowerBody");
		this.upperBody = part.getChild("upperBody");
		this.head = part.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		part.addOrReplaceChild("lowerBody", CubeListBuilder.create().texOffs(12, 29).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, false), PartPose.offsetAndRotation(2.0F, 17.0F, -0.5F, 0.5F, 0.0F, 0.0F));
		part.addOrReplaceChild("upperBody", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, false)
																			 .texOffs(0, 24).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, false)
																			 .texOffs(0, 20).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, false)
																			 .texOffs(0, 16).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, false), PartPose.offset(2.0F, 7.0F, -0.5F));
		part.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, false)
																		.texOffs(30, 19).addBox(-1.5F, -9.0F, -2.0F, 2.0F, 2.0F, 4.0F, false)
																		.texOffs(24, 0).addBox(-1.5F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, false)
																		.texOffs(24, 16).addBox(-1.5F, -12.0F, -1.0F, 1.0F, 1.0F, 1.0F, false)
																		.texOffs(0, 0).addBox(-1.5F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, false)
																		.texOffs(32, 8).addBox(-2.5F, -9.0F, -1.0F, 4.0F, 2.0F, 2.0F, false)
																		.texOffs(24, 29).addBox(-3.5F, -7.0F, -1.0F, 6.0F, 2.0F, 2.0F, false)
																		.texOffs(26, 16).addBox(-4.5F, -5.0F, -1.0F, 8.0F, 1.0F, 2.0F, false)
																		.texOffs(30, 25).addBox(-3.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, false)
																		.texOffs(32, 12).addBox(0.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, false)
																		.texOffs(0, 4).addBox(0.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, false)
																		.texOffs(24, 0).addBox(-2.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, false)
																		.texOffs(24, 3).addBox(0.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, false)
																		.texOffs(9, 28).addBox(0.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, false)
																		.texOffs(30, 19).addBox(-2.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, false)
																		.texOffs(24, 33).addBox(-3.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, false)
																		.texOffs(18, 20).addBox(-1.5F, -5.0F, -4.0F, 2.0F, 1.0F, 8.0F, false), PartPose.offset(0.0F, 3.0F, 0.0F));

		return LayerDefinition.create(mesh, 64, 64);
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