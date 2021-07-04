package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzo;
import com.skizzium.projectapple.entity.layer.SkizzoGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzoModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzoRenderer extends MobRenderer<Skizzo, SkizzoModel<Skizzo>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzo.png");

   public SkizzoRenderer(EntityRendererManager manager) {
      super(manager, new SkizzoModel(), 0.65F);
      this.addLayer(new SkizzoGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(Skizzo entity) {
      return TEXTURE_LOCATION;
   }
}