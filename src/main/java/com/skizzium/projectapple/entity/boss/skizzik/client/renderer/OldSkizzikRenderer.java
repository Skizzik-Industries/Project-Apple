package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.OldSkizzikModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.OldSkizzikGlowLayer;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OldSkizzikRenderer extends MobRenderer<Skizzik, EntityModel<Skizzik>> {
   private static final ResourceLocation SLEEPING_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_sleeping.png");
   private static final ResourceLocation NORMAL_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png");
   private static final ResourceLocation STAGE_5_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_stage-5.png");
   private static final ResourceLocation FINISH_HIM_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_finish-him.png");

   private static final ResourceLocation SPOOKZIK_SLEEPING_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_sleeping.png");
   private static final ResourceLocation SPOOKZIK_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik.png");
   private static final ResourceLocation SPOOKZIK_STAGE_5_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_stage-5.png");
   private static final ResourceLocation SPOOKZIK_FINISH_HIM_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_finish-him.png");

   public OldSkizzikRenderer(EntityRendererProvider.Context renderer) {
      super(renderer, new OldSkizzikModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZIK_LAYER)), 1.0F);
      this.addLayer(new OldSkizzikGlowLayer<>(this));
   }

   @Override
   protected int getBlockLightLevel(Skizzik entity, BlockPos pos) {
      int stage = entity.stageManager.getCurrentStage().getStage().getId();
      return stage <= 0 || stage >= 6 ? 0 : 15;
   }

   @Override
   public ResourceLocation getTextureLocation(Skizzik entity) {
      int stage = entity.stageManager.getCurrentStage().getStage().getId();

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