package com.skizzium.projectapple.entity.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import Entity;

@OnlyIn(Dist.CLIENT)
public class SkizzoModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart head;

	public SkizzoModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelPart(this);
		head.setPos(0.0F, 18.0F, 0.0F);
		head.texOffs(0, 0).addBox(-6.0F, -27.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.texOffs(0, 24).addBox(-4.0F, -15.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		head.texOffs(26, 27).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);
		head.texOffs(0, 33).addBox(-2.0F, -11.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 5).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float f, float f1, float f2, float f3, float f4) {
		this.head.yRot = f3 / (180F / (float) Math.PI);
		this.head.xRot = f4 / (180F / (float) Math.PI);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, light, overlay);
	}
}