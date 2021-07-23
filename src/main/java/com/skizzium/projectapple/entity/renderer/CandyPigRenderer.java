package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.CandyPig;
import com.skizzium.projectapple.entity.model.CandyPigModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class CandyPigRenderer extends DrownedModel<CandyPig, CandyPigModel<CandyPig>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("skizzik:textures/entity/candy_pig/candy_pig.png");

   public CandyPigRenderer(EntityRenderDispatcher manager) {
      super(manager, new CandyPigModel<>(), 0.7F);
      this.addLayer(new SaddleLayer<>(this, new CandyPigModel<>(0.5F), new ResourceLocation("minecraft:textures/entity/pig/pig_saddle.png")));
   }

   public ResourceLocation getTextureLocation(CandyPig entity) {
      return TEXTURE_LOCATION;
   }
}