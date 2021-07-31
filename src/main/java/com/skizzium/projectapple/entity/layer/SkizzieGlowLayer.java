package com.skizzium.projectapple.entity.layer;

import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieGlowLayer<T extends Skizzie> extends EyesLayer<T, SkizzieModel<T>> {
    private static final RenderType SKIZZIE_GLOW = RenderType.eyes(new ResourceLocation("skizzik:textures/entity/skizzie/skizzie_glow.png"));

    public SkizzieGlowLayer(RenderLayerParent<T, SkizzieModel<T>> renderer) {
        super(renderer);
    }

    public RenderType renderType() {
        return SKIZZIE_GLOW;
    }
}
