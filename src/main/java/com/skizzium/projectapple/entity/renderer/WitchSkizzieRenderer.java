package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.WitchSkizzie;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.layer.WitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieRenderer extends MobRenderer<WitchSkizzie, WitchSkizzieModel<WitchSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/witch_skizzie/witch_skizzie.png");

   public WitchSkizzieRenderer(EntityRendererManager manager) {
      super(manager, new WitchSkizzieModel(), 0.45F);
      this.addLayer(new WitchSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(WitchSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}