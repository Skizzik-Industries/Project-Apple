package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KaboomSkizzieRenderer extends SkizzieRenderer {
    private static final ResourceLocation KABOOM_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie.png");

    public KaboomSkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer);
    }

    @Override
    public ResourceLocation getTextureLocation(Skizzie entity) {
        return KABOOM_SKIZZIE_LOCATION;
    }
}
