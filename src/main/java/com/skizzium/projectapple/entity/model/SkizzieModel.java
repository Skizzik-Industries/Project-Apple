package com.skizzium.projectapple.entity.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart lowerBody;
	private final ModelPart upperBody;
	private final ModelPart head;

	public SkizzieModel(ModelPart part) {
		this.lowerBody = p_170724_.getChild("right_chest");
		this.upperBody = part.getChild("body");
		this.head = part.getChild("head");

		this.texWidth = 64;
		this.texHeight = 64;

		lowerBody = new ModelPart(this);
		lowerBody.setPos(2.0F, 17F, -0.5F);
		setRotationAngle(lowerBody, 0.5F, 0.0F, 0.0F);
		lowerBody.texOffs(26, 16).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, true);

		upperBody = new ModelPart(this);
		upperBody.setPos(2.0F, 7F, -0.5F);
		upperBody.texOffs(23, 25).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, 0.0F, true);
		upperBody.texOffs(0, 16).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);
		upperBody.texOffs(0, 20).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);
		upperBody.texOffs(0, 24).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, true);

		head = new ModelPart(this);
		head.setPos(0.0F, 3.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
	}

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		part.addOrReplaceChild("lowerBody", CubeListBuilder.create().texOffs(29, 0).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, deformation), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
		part.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, deformation).texOffs(0, 14).addBox("neck", -4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, deformation).texOffs(17, 0).addBox("ear", -4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, deformation).texOffs(17, 0).addBox("ear", 1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, deformation), PartPose.offset(0.0F, 7.0F, -6.0F));
		part.addOrReplaceChild("upperBody", CubeListBuilder.create().texOffs(45, 28).addBox(-3.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, deformation), PartPose.offsetAndRotation(-8.5F, 3.0F, 3.0F, 0.0F, ((float)Math.PI / 2F), 0.0F));

		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(29, 29).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, deformation);
		part.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-3.5F, 10.0F, 6.0F));
		part.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(3.5F, 10.0F, 6.0F));
		part.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-3.5F, 10.0F, -5.0F));
		part.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(3.5F, 10.0F, -5.0F));
		return LayerDefinition.create(mesh, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		this.head.yRot = f3 / (180F / (float) Math.PI);
		this.head.xRot = f4 / (180F / (float) Math.PI);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha){
		lowerBody.render(matrix, buffer, light, overlay);
		upperBody.render(matrix, buffer, light, overlay);
		head.render(matrix, buffer, light, overlay);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}