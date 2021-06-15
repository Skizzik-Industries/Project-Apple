package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.layer.KaboomSkizzieGlowLayer;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperChargeLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KaboomSkizzieRenderer extends MobRenderer<KaboomSkizzie, SkizzieModel<KaboomSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie.png");

   public KaboomSkizzieRenderer(EntityRendererManager manager) {
      super(manager, new SkizzieModel(), 0.45F);
      this.addLayer(new KaboomSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(KaboomSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}