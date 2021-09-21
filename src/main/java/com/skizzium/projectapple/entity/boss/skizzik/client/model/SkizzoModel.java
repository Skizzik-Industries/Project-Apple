package com.skizzium.projectapple.entity.boss.skizzik.client.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzo;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzoModel<T extends Skizzo> extends EntityModel<T> {
	private final ModelPart head;

	public SkizzoModel(ModelPart part) {
		this.head = part.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		part.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -27.0F, -6.0F, 12.0F, 12.0F, 12.0F, false)
																		.texOffs(71, 15).addBox(-4.0F, -15.0F, -4.0F, 8.0F, 1.0F, 8.0F, false)
																		.texOffs(75, 5).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 3.0F, 6.0F, false)
																		.texOffs(54, 17).addBox(-2.0F, -11.0F, -2.0F, 4.0F, 3.0F, 4.0F, false)
																		.texOffs(58, 11).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 3.0F, 2.0F, false)
																		.texOffs(49, 18).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, false), PartPose.offset(0.0F, 18.0F, 0.0F));

		return LayerDefinition.create(mesh, 103, 24);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float f2, float f3, float f4) {
		this.head.yRot = f3 / (180F / (float) Math.PI);
		this.head.xRot = f4 / (180F / (float) Math.PI);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay);
	}
}