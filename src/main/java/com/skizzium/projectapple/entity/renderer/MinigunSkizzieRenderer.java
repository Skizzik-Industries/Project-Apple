package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.MinigunSkizzie;
import com.skizzium.projectapple.entity.layer.MinigunSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class MinigunSkizzieRenderer extends DrownedModel<MinigunSkizzie, SkizzieModel<MinigunSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/minigun_skizzie.png");

   public MinigunSkizzieRenderer(EntityRenderDispatcher manager) {
      super(manager, new SkizzieModel(), 0.45F);
      this.addLayer(new MinigunSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(MinigunSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}