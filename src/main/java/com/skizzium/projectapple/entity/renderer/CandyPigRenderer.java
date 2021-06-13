package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.CandyPig;
import com.skizzium.projectapple.entity.model.CandyPigModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CandyPigRenderer extends MobRenderer<CandyPig, CandyPigModel<CandyPig>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/candy_pig/candy_pig.png");

   public CandyPigRenderer(EntityRendererManager manager) {
      super(manager, new CandyPigModel<>(), 0.7F);
      this.addLayer(new SaddleLayer<>(this, new CandyPigModel<>(0.5F), new ResourceLocation("minecraft:textures/entity/pig/pig_saddle.png")));
   }

   public ResourceLocation getTextureLocation(CandyPig entity) {
      return TEXTURE_LOCATION;
   }
}