package com.skizzium.projectapple.tileentity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PA_SkullModel extends SkullModel {
    public PA_SkullModel(ModelPart part) {
        super(part);
    }

    public static LayerDefinition createSkizzikHeadLayer() {
        MeshDefinition mesh = createHeadModel();
        return LayerDefinition.create(mesh, 32, 16);
    }

    public static LayerDefinition createSkizzikHeadWithGemsLayer() {
        MeshDefinition mesh = createHeadModel();
        return LayerDefinition.create(mesh, 128, 128);
    }
}
