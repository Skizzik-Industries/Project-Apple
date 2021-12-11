package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.skizzium.projectapple.entity.boss.friendlyskizzik.skizzie.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer.WitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.WitchSkizzieModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieRenderer extends MobRenderer<Mob, WitchSkizzieModel<Mob>> {
    private static final ResourceLocation FRIENDLY_WITCH_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_witch_skizzie/friendly_witch_skizzie.png");
    private static final ResourceLocation WITCH_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/witch_skizzie/witch_skizzie.png");

    private static final ResourceLocation FRIENDLY_WITCH_SPOOKZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/friendly_witch_spookzie/friendly_witch_spookzie.png");
    private static final ResourceLocation WITCH_SPOOKZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/witch_spookzie/witch_spookzie.png");

    public WitchSkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new WitchSkizzieModel<>(renderer.bakeLayer(PA_ModelLayers.WITCH_SKIZZIE_LAYER)), 0.45F);
        this.addLayer(new WitchSkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(Mob entity) {
        if (entity instanceof FriendlyWitchSkizzie) {
            return ((FriendlyWitchSkizzie) entity).getHoliday() == 1 ? FRIENDLY_WITCH_SPOOKZIE_LOCATION : FRIENDLY_WITCH_SKIZZIE_LOCATION;
        }

        return ((WitchSkizzie) entity).getHoliday() == 1 ? WITCH_SPOOKZIE_LOCATION : WITCH_SKIZZIE_LOCATION;
    }
}
