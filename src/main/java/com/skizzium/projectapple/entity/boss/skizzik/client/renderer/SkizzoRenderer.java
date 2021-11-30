package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzoModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.SkizzoGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class SkizzoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {
    public SkizzoRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzoModel<>());
        this.shadowRadius = 0.45F;
        this.addLayer(new SkizzoGlowLayer<>(this));
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos pos) {
        return 15;
    }
}
