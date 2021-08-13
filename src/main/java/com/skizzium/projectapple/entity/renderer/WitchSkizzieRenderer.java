package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.WitchSkizzie;
import com.skizzium.projectapple.entity.layer.WitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieRenderer extends MobRenderer<WitchSkizzie, WitchSkizzieModel<WitchSkizzie>> {
    private static final ResourceLocation SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/witch_skizzie/witch_skizzie.png");

    public WitchSkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new WitchSkizzieModel<>(renderer.bakeLayer(PA_ModelLayers.WITCH_SKIZZIE_LAYER)), 0.45F);
        this.addLayer(new WitchSkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(WitchSkizzie entity) {
        return SKIZZIE_LOCATION;
    }
}
