package com.skizzium.projectapple.entity.model;

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
public class SkizzieModel<T extends LivingEntity> extends EntityModel<T> {
    private final ModelPart lowerBody;
    private final ModelPart upperBody;
    private final ModelPart head;

    public SkizzieModel(ModelPart part) {
        this.lowerBody = part.getChild("lower_body");
        this.upperBody = part.getChild("upper_body");
        this.head = part.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("lower_body", CubeListBuilder.create().texOffs(13, 26).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, true), PartPose.offsetAndRotation(2.0F, 17.0F, -0.5F, 0.5F, 0.0F, 0.0F));
        part.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, true)
                                                                                .texOffs(3, 17).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, true)
                                                                                .texOffs(3, 17).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, true)
                                                                                .texOffs(3, 17).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, true), PartPose.offset(2.0F, 7.0F, -0.5F));
        part.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, true), PartPose.offset(0.0F, 3.0F, 0.0F));

        return LayerDefinition.create(mesh, 32, 35);
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
