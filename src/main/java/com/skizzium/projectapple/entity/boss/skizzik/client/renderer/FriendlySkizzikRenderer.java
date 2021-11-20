package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzik;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.FriendlySkizzikModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.FriendlySkizzikGemLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FriendlySkizzikRenderer extends GeoEntityRenderer<FriendlySkizzik> {
    public FriendlySkizzikRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new FriendlySkizzikModel());
        this.shadowRadius = 1.0F;
        this.addLayer(new FriendlySkizzikGemLayer<>(this));
    }

    @Override
    protected int getBlockLightLevel(FriendlySkizzik entity, BlockPos pos) {
        return 15;
    }
}
