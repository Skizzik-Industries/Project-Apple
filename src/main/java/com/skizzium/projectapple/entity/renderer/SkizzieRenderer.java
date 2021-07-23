package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class SkizzieRenderer extends DrownedModel<Skizzie, SkizzieModel<Skizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie.png");

   public SkizzieRenderer(EntityRenderDispatcher manager) {
      super(manager, new SkizzieModel(), 0.45F);
      this.addLayer(new SkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(Skizzie entity) {
      return TEXTURE_LOCATION;
   }
}