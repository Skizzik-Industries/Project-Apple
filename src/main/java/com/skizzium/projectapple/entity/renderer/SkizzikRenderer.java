package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.Skizzik;
import com.skizzium.projectapple.entity.layer.SkizzikGlowLayer;
import com.skizzium.projectapple.entity.model.*;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikRenderer extends MobRenderer<Skizzik, EntityModel<Skizzik>> {
   private static final ResourceLocation SLEEPING_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_sleeping.png");
   private static final ResourceLocation NORMAL_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png");
   private static final ResourceLocation STAGE_5_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_stage-5.png");
   private static final ResourceLocation FINISH_HIM_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_finish-him.png");

   private static final ResourceLocation SPOOKZIK_SLEEPING_LOCATION = new ResourceLocation("skizzik:textures/entity/holiday/spooktober/spookzik/spookzik_sleeping.png");
   private static final ResourceLocation SPOOKZIK_LOCATION = new ResourceLocation("skizzik:textures/entity/holiday/spooktober/spookzik/spookzik.png");
   private static final ResourceLocation SPOOKZIK_STAGE_5_LOCATION = new ResourceLocation("skizzik:textures/entity/holiday/spooktober/spookzik/spookzik_stage-5.png");
   private static final ResourceLocation SPOOKZIK_FINISH_HIM_LOCATION = new ResourceLocation("skizzik:textures/entity/holiday/spooktober/spookzik/spookzik_finish-him.png");

   public SkizzikRenderer(EntityRendererProvider.Context renderer) {
      super(renderer, new SkizzikModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZIK_LAYER)), 1.0F);
      this.addLayer(new SkizzikGlowLayer<>(this));
   }

   @Override
   protected int getBlockLightLevel(Skizzik entity, BlockPos pos) {
      return entity.getStage() <= 0 || entity.getStage() >= 6 ? 0 : 15;
   }

   @Override
   public ResourceLocation getTextureLocation(Skizzik entity) {
      int stage = entity.getStage();

      if (ProjectApple.holiday == 1) {
         return stage == 0 ? SPOOKZIK_SLEEPING_LOCATION :
                stage >= 1 && stage <= 4 ? SPOOKZIK_LOCATION :
                stage == 5 ? SPOOKZIK_STAGE_5_LOCATION :
                SPOOKZIK_FINISH_HIM_LOCATION;
      }

      return stage == 0 ? SLEEPING_LOCATION :
              stage >= 1 && stage <= 4 ? NORMAL_LOCATION :
              stage == 5 ? STAGE_5_LOCATION :
              FINISH_HIM_LOCATION;
   }
}