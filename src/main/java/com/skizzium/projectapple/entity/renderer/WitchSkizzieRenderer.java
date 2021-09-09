package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.CorruptedSkizzie;
import com.skizzium.projectapple.entity.FriendlySkizzie;
import com.skizzium.projectapple.entity.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.layer.WitchSkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
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
        if (ProjectApple.holiday == 1) {
            return entity instanceof FriendlyWitchSkizzie ? FRIENDLY_WITCH_SPOOKZIE_LOCATION : WITCH_SPOOKZIE_LOCATION;
        }

        return entity instanceof FriendlyWitchSkizzie ? FRIENDLY_WITCH_SKIZZIE_LOCATION : WITCH_SKIZZIE_LOCATION;
    }
}
