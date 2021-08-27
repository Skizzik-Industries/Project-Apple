package com.skizzium.projectapple.entity.layer;

import com.skizzium.projectapple.entity.Skizzo;
import com.skizzium.projectapple.entity.model.SkizzoModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzoGlowLayer<T extends Skizzo, M extends SkizzoModel<T>> extends AbstractEyesLayer<T, M> {
    private static final RenderType SKIZZO_GLOW = RenderType.eyes(new ResourceLocation("skizzik:textures/entity/skizzik/skizzo_glow.png"));

    public SkizzoGlowLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return SKIZZO_GLOW;
    }
}
