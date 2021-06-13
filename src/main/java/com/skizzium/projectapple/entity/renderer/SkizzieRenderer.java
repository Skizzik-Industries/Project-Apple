package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.CandyPig;
import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.CandyPigModel;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieRenderer extends MobRenderer<Skizzie, SkizzieModel> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie.png");

   public SkizzieRenderer(EntityRendererManager manager) {
      super(manager, new SkizzieModel(), 0.45F);
      this.addLayer(new SkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(Skizzie entity) {
      return TEXTURE_LOCATION;
   }
}