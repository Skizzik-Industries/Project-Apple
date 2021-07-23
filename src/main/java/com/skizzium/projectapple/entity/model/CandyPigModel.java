package com.skizzium.projectapple.entity.model;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CandyPigModel<T extends Entity> extends QuadrupedModel<T> {
   public CandyPigModel(ModelPart p_170799_) {
      super(p_170799_, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
   }

   public static LayerDefinition createBodyLayer(CubeDeformation p_170801_) {
      MeshDefinition var1 = QuadrupedModel.createBodyMesh(6, p_170801_);
      PartDefinition var2 = var1.getRoot();
      var2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, p_170801_).texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, p_170801_), PartPose.offset(0.0F, 12.0F, -6.0F));
      return LayerDefinition.create(var1, 64, 32);
   }
}