package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzikModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.SkizzikGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SkizzikRenderer extends GeoEntityRenderer<Skizzik> {
    public SkizzikRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzikModel());
        this.shadowRadius = 1.0F;
        //this.addLayer(new SkizzikGlowLayer(this));
    }

    @Override
    protected int getBlockLightLevel(Skizzik entity, BlockPos pos) {
        int stage = entity.stageManager.getCurrentStage().getStage().getId();
        return stage <= 0 || stage >= 6 ? 0 : 15;
    }
}
