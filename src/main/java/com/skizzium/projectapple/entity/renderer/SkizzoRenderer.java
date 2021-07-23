package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzo;
import com.skizzium.projectapple.entity.layer.SkizzoGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzoModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class SkizzoRenderer extends DrownedModel<Skizzo, SkizzoModel<Skizzo>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzo.png");

   public SkizzoRenderer(EntityRenderDispatcher manager) {
      super(manager, new SkizzoModel(), 0.65F);
      this.addLayer(new SkizzoGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(Skizzo entity) {
      return TEXTURE_LOCATION;
   }
}