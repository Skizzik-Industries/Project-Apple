package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.FriendlySkizzie;
import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlySkizzieRenderer extends MobRenderer<FriendlySkizzie, SkizzieModel<FriendlySkizzie>> {
    private static final ResourceLocation FRIENDLY_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_skizzie.png");

    public FriendlySkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzieModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZIE_LAYER)), 0.45F);
        this.addLayer(new SkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(FriendlySkizzie entity) {
        return FRIENDLY_SKIZZIE_LOCATION;
    }
}
