package com.skizzium.projectapple.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CandyPigRenderer extends PigRenderer {
    private static final ResourceLocation CANDY_PIG_LOCATION = new ResourceLocation("skizzik:textures/entity/candy_pig/candy_pig.png");

    public CandyPigRenderer(EntityRendererProvider.Context renderer) {
        super(renderer);
    }

    @Override
    public ResourceLocation getTextureLocation(Pig entity) {
        return CANDY_PIG_LOCATION;
    }
}
