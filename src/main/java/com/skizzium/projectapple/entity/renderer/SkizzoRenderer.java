package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzo;
import com.skizzium.projectapple.entity.layer.SkizzoGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzoModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzoRenderer extends MobRenderer<Skizzo, SkizzoModel<Skizzo>> {
    private static final ResourceLocation SKIZZO_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzo.png");

    public SkizzoRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzoModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZO_LAYER)), 0.45F);
        this.addLayer(new SkizzoGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(Skizzo entity) {
        return SKIZZO_LOCATION;
    }
}
