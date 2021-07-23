package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.layer.FriendlyWitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class FriendlyWitchSkizzieRenderer extends DrownedModel<FriendlyWitchSkizzie, WitchSkizzieModel<FriendlyWitchSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_witch_skizzie/friendly_witch_skizzie.png");

   public FriendlyWitchSkizzieRenderer(EntityRenderDispatcher manager) {
      super(manager, new WitchSkizzieModel(), 0.45F);
      this.addLayer(new FriendlyWitchSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(FriendlyWitchSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}