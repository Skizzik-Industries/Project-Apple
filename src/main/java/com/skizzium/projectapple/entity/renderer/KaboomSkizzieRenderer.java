package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.layer.KaboomSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class KaboomSkizzieRenderer extends DrownedModel<KaboomSkizzie, SkizzieModel<KaboomSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie.png");

   public KaboomSkizzieRenderer(EntityRenderDispatcher manager) {
      super(manager, new SkizzieModel(), 0.45F);
      this.addLayer(new KaboomSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(KaboomSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}