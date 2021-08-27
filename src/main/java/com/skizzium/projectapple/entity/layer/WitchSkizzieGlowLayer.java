package com.skizzium.projectapple.entity.layer;

import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieGlowLayer<T extends LivingEntity, M extends WitchSkizzieModel<T>> extends AbstractEyesLayer<T, M> {
    private static final RenderType WITCH_SKIZZIE_GLOW = RenderType.eyes(new ResourceLocation("skizzik:textures/entity/witch_skizzie/witch_skizzie_glow.png"));

    public WitchSkizzieGlowLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return WITCH_SKIZZIE_GLOW;
    }
}
