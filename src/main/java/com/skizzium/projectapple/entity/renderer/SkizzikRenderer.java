package com.skizzium.projectapple.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.skizzium.projectapple.entity.Skizzik;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.layer.SkizzikGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzikModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WitherAuraLayer;
import net.minecraft.client.renderer.entity.model.WitherModel;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikRenderer extends MobRenderer<Skizzik, SkizzikModel<Skizzik>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png");

   public SkizzikRenderer(EntityRendererManager entity) {
      super(entity, new SkizzikModel(), 1.0F);
      this.addLayer(new SkizzikGlowLayer(this));
   }

   protected int getBlockLightLevel(Skizzik entity, BlockPos pos) {
      return 15;
   }

   public ResourceLocation getTextureLocation(Skizzik entity) {
      return TEXTURE_LOCATION;
   }
}