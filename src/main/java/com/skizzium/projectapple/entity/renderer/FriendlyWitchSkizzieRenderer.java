package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.layer.WitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlyWitchSkizzieRenderer extends MobRenderer<FriendlyWitchSkizzie, WitchSkizzieModel<FriendlyWitchSkizzie>> {
    private static final ResourceLocation SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_witch_skizzie/friendly_witch_skizzie.png");

    public FriendlyWitchSkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new WitchSkizzieModel<>(renderer.bakeLayer(PA_ModelLayers.WITCH_SKIZZIE_LAYER)), 0.45F);
        this.addLayer(new WitchSkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(FriendlyWitchSkizzie entity) {
        return SKIZZIE_LOCATION;
    }
}
