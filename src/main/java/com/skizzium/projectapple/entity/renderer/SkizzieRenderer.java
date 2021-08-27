package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.CorruptedSkizzie;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieRenderer extends MobRenderer<Skizzie, SkizzieModel<Skizzie>> {
    private static final ResourceLocation SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie.png");
    private static final ResourceLocation KABOOM_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie.png");
    private static final ResourceLocation CORRUPTED_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/corrupted_skizzie.png");

    public SkizzieRenderer(EntityRendererManager renderer) {
        super(renderer, new SkizzieModel<>(), 0.45F);
        this.addLayer(new SkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(Skizzie entity) {
        return entity instanceof KaboomSkizzie ? KABOOM_SKIZZIE_LOCATION :
                entity instanceof CorruptedSkizzie ? CORRUPTED_SKIZZIE_LOCATION : SKIZZIE_LOCATION;
    }
}
