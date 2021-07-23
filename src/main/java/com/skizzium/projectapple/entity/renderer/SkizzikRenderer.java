package com.skizzium.projectapple.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.entity.Skizzik;
import com.skizzium.projectapple.entity.layer.SkizzikGlowLayer;
import com.skizzium.projectapple.entity.model.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikRenderer extends DrownedModel<Skizzik, EntityModel<Skizzik>> {
   private int stage;

   private static final ResourceLocation SLEEPING_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_sleeping.png");
   private static final ResourceLocation NORMAL_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png");
   private static final ResourceLocation STAGE_5_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_stage-5.png");
   private static final ResourceLocation FINISH_HIM_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_finish-him.png");

   private final SkizzikSleepingModel<Skizzik> SLEEPING = new SkizzikSleepingModel<>();
   private final SkizzikModel<Skizzik> STAGE_1 = new SkizzikModel<>();
   private final SkizzikStage2Model<Skizzik> STAGE_2 = new SkizzikStage2Model<>();
   private final SkizzikStage3Model<Skizzik> STAGE_3 = new SkizzikStage3Model<>();
   private final SkizzikStage4Model<Skizzik> STAGE_4 = new SkizzikStage4Model<>();
   private final SkizzikStage5Model<Skizzik> STAGE_5 = new SkizzikStage5Model<>();
   private final SkizzikFinishHimModel<Skizzik> FINISH_HIM = new SkizzikFinishHimModel<>();

   public SkizzikRenderer(EntityRenderDispatcher entity) {
      super(entity, new SkizzikSleepingModel<>(), 1.0F);
      this.addLayer(new SkizzikGlowLayer<>(this));
      this.stage = 0;
   }

   @Override
   protected int getBlockLightLevel(Skizzik entity, BlockPos pos) {
      return 15;
   }

   @Override
   public ResourceLocation getTextureLocation(Skizzik entity) {
      int stage = entity.getStage();
      return stage == 0 ? SLEEPING_LOCATION :
              stage >= 1 && stage <= 4 ? NORMAL_LOCATION :
              stage == 5 ? STAGE_5_LOCATION :
              FINISH_HIM_LOCATION;
   }

   @Override
   public void render(Skizzik entity, float headYaw, float headPitch, PoseStack matrix, MultiBufferSource buffer, int overlay) {
      int localStage = entity.getStage();

      if (localStage != this.stage) {
         if (localStage == 0) {
            this.model = this.SLEEPING;
         }
         else if (localStage == 1) {
            this.model = this.STAGE_1;
         }
         else if (localStage == 2) {
            this.model = this.STAGE_2;
         }
         else if (localStage == 3) {
            this.model = this.STAGE_3;
         }
         else if (localStage == 4) {
            this.model = this.STAGE_4;
         }
         else if (localStage == 5) {
            this.model = this.STAGE_5;
         }
         else {
            this.model = this.FINISH_HIM;
         }
      }

      this.stage = localStage;

      super.render(entity, headYaw, headPitch, matrix, buffer, overlay);
   }
}