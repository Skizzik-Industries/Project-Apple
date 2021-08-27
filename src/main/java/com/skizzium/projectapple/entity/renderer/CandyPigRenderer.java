package com.skizzium.projectapple.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CandyPigRenderer extends PigRenderer {
    private static final ResourceLocation CANDY_PIG_LOCATION = new ResourceLocation("skizzik:textures/entity/candy_pig/candy_pig.png");

    public CandyPigRenderer(EntityRendererManager renderer) {
        super(renderer);
    }

    @Override
    public ResourceLocation getTextureLocation(PigEntity entity) {
        return CANDY_PIG_LOCATION;
    }
}
