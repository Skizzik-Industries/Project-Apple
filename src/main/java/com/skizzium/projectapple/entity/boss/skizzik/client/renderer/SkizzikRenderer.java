package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzikModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.SkizzikGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SkizzikRenderer extends GeoEntityRenderer<Skizzik> {
    public SkizzikRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzikModel());
        this.shadowRadius = 1.0F;
        //this.addLayer(new SkizzikGlowLayer(this));
    }
}
