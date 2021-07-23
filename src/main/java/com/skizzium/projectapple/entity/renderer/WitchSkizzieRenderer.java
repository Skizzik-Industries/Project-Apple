package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.WitchSkizzie;
import com.skizzium.projectapple.entity.layer.WitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieRenderer extends DrownedModel<WitchSkizzie, WitchSkizzieModel<WitchSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/witch_skizzie/witch_skizzie.png");

   public WitchSkizzieRenderer(EntityRenderDispatcher manager) {
      super(manager, new WitchSkizzieModel(), 0.45F);
      this.addLayer(new WitchSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(WitchSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}