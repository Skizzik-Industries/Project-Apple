package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.WitchSkizzie;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieGlowLayer<T extends WitchSkizzie> extends EyesLayer<T, WitchSkizzieModel<T>> {
    private static final RenderType WITCH_SKIZZIE_GLOW = RenderType.eyes(new ResourceLocation("skizzik:textures/entity/witch_skizzie/witch_skizzie_glow.png"));

    public WitchSkizzieGlowLayer(RenderLayerParent<T, WitchSkizzieModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return WITCH_SKIZZIE_GLOW;
    }
}
