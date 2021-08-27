package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzik;
import com.skizzium.projectapple.entity.layer.SkizzikGlowLayer;
import com.skizzium.projectapple.entity.model.*;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikRenderer extends MobRenderer<Skizzik, EntityModel<Skizzik>> {
   private static final ResourceLocation SLEEPING_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_sleeping.png");
   private static final ResourceLocation NORMAL_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png");
   private static final ResourceLocation STAGE_5_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_stage-5.png");
   private static final ResourceLocation FINISH_HIM_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_finish-him.png");

   public SkizzikRenderer(EntityRendererManager renderer) {
      super(renderer, new SkizzikModel<>(), 1.0F);
      this.addLayer(new SkizzikGlowLayer<>(this));
   }

   @Override
   protected int getBlockLightLevel(Skizzik entity, BlockPos pos) {
      return entity.getStage() <= 0 || entity.getStage() >= 6 ? 0 : 15;
   }

   @Override
   public ResourceLocation getTextureLocation(Skizzik entity) {
      int stage = entity.getStage();
      return stage == 0 ? SLEEPING_LOCATION :
              stage >= 1 && stage <= 4 ? NORMAL_LOCATION :
              stage == 5 ? STAGE_5_LOCATION :
              FINISH_HIM_LOCATION;
   }
}