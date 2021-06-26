package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.MinigunSkizzie;
import com.skizzium.projectapple.entity.layer.MinigunSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MinigunSkizzieRenderer extends MobRenderer<MinigunSkizzie, SkizzieModel<MinigunSkizzie>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/minigun_skizzie.png");

   public MinigunSkizzieRenderer(EntityRendererManager manager) {
      super(manager, new SkizzieModel(), 0.45F);
      this.addLayer(new MinigunSkizzieGlowLayer(this));
   }

   public ResourceLocation getTextureLocation(MinigunSkizzie entity) {
      return TEXTURE_LOCATION;
   }
}