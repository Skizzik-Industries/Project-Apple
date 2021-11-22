package com.skizzium.projectapple.entity.boss.friendlyskizzik.client.renderer;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzo;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzoModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.SkizzoGlowLayer;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlySkizzoRenderer extends MobRenderer<FriendlySkizzo, SkizzoModel<FriendlySkizzo>> {
    private static final ResourceLocation FRIENDLY_SKIZZO_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_skizzik/friendly_skizzo.png");
    private static final ResourceLocation FRIENDLY_SPOOKZO_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/friendly_spookzik/friendly_spookzo.png");

    public FriendlySkizzoRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzoModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZO_LAYER)), 0.45F);
        this.addLayer(new SkizzoGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(FriendlySkizzo entity) {
        return ProjectApple.holiday == 1 ? FRIENDLY_SPOOKZO_LOCATION : FRIENDLY_SKIZZO_LOCATION;
    }
}
