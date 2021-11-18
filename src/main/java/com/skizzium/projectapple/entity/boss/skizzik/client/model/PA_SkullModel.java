package com.skizzium.projectapple.entity.boss.skizzik.client.model;

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

    public static MeshDefinition createSmallHeadModel() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.ZERO);
        return mesh;
    }

    public static LayerDefinition createSmallSkizzikHeadLayer() {
        MeshDefinition mesh = createSmallHeadModel();
        return LayerDefinition.create(mesh, 24, 12);
    }

    public static LayerDefinition createSkizzikHeadLayer() {
        MeshDefinition mesh = createHeadModel();
        return LayerDefinition.create(mesh, 32, 16);
    }

    public static LayerDefinition createSkizzikHeadWithGemsLayer() {
        MeshDefinition mesh = createHeadModel();
        return LayerDefinition.create(mesh, 78, 53);
    }
}
