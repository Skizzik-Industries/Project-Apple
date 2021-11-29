package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzo;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzoModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.SkizzoGlowLayer;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzoRenderer extends MobRenderer<Skizzo, SkizzoModel<Skizzo>> {
    private static final ResourceLocation SKIZZO_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzik/skizzo.png");
    private static final ResourceLocation SPOOKZO_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzo.png");

    public SkizzoRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzoModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZO_LAYER)), 0.45F);
        this.addLayer(new SkizzoGlowLayer<>(this));
    }

    @Override
    protected int getBlockLightLevel(Skizzo pEntity, BlockPos pPos) {
        return 15;
    }

    public ResourceLocation getTextureLocation(Skizzo entity) {
        return new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png");
    }
}
