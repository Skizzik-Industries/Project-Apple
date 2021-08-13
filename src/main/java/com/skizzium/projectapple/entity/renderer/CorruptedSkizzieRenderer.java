package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CorruptedSkizzieRenderer extends SkizzieRenderer {
    private static final ResourceLocation CORRUPTED_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/corrupted_skizzie.png");

    public CorruptedSkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer);
    }

    @Override
    public ResourceLocation getTextureLocation(Skizzie entity) {
        return CORRUPTED_SKIZZIE_LOCATION;
    }
}
