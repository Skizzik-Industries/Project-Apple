package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.layer.WitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlyWitchSkizzieRenderer extends MobRenderer<FriendlyWitchSkizzie, WitchSkizzieModel<FriendlyWitchSkizzie>> {
    private static final ResourceLocation SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_witch_skizzie/friendly_witch_skizzie.png");

    public FriendlyWitchSkizzieRenderer(EntityRendererManager renderer) {
        super(renderer, new WitchSkizzieModel<>(), 0.45F);
        this.addLayer(new WitchSkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(FriendlyWitchSkizzie entity) {
        return SKIZZIE_LOCATION;
    }
}
