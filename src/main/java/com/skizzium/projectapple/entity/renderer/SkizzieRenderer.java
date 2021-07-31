package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.skizzium.projectapple.ProjectApple.MOD_ID;

@OnlyIn(Dist.CLIENT)
public class SkizzieRenderer extends MobRenderer<Skizzie, SkizzieModel<Skizzie>> {
    private static final ResourceLocation SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie.png");

    public SkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzieModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZIE_LAYER)), 0.45F);
        this.addLayer(new SkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(Skizzie entity) {
        return SKIZZIE_LOCATION;
    }
}
