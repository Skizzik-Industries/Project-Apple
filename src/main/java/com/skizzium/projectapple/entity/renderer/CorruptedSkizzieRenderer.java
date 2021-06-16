package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.CorruptedSkizzie;
import com.skizzium.projectapple.entity.layer.CorruptedSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CorruptedSkizzieRenderer extends MobRenderer<CorruptedSkizzie, SkizzieModel<CorruptedSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/corrupted_skizzie.png");

   public CorruptedSkizzieRenderer(EntityRendererManager manager) {
      super(manager, new SkizzieModel(), 0.45F);
      this.addLayer(new CorruptedSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(CorruptedSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}