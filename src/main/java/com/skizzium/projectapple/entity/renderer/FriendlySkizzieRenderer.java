package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.FriendlySkizzie;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlySkizzieRenderer extends MobRenderer<FriendlySkizzie, SkizzieModel<FriendlySkizzie>> {
    private static final ResourceLocation FRIENDLY_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_skizzie.png");

    public FriendlySkizzieRenderer(EntityRendererManager renderer) {
        super(renderer, new SkizzieModel<>(), 0.45F);
        this.addLayer(new SkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(FriendlySkizzie entity) {
        return FRIENDLY_SKIZZIE_LOCATION;
    }
}
