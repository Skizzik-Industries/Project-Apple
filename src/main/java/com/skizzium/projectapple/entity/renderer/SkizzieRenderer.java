package com.skizzium.projectapple.entity.renderer;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.entity.layer.SkizzieGlowLayer;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieRenderer extends MobRenderer<Mob, SkizzieModel<Mob>> {
    private static final ResourceLocation FRIENDLY_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_skizzie.png");
    private static final ResourceLocation SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie.png");
    private static final ResourceLocation KABOOM_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie.png");
    private static final ResourceLocation CORRUPTED_SKIZZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/skizzie/corrupted_skizzie.png");

    private static final ResourceLocation FRIENDLY_SPOOKZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/friendly_spookzie/friendly_spookzie.png");
    private static final ResourceLocation SPOOKZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzie/spookzie.png");
    private static final ResourceLocation KABOOM_SPOOKZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzie/kaboom_spookzie.png");
    private static final ResourceLocation CORRUPTED_SPOOKZIE_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzie/corrupted_spookzie.png");

    public SkizzieRenderer(EntityRendererProvider.Context renderer) {
        super(renderer, new SkizzieModel<>(renderer.bakeLayer(PA_ModelLayers.SKIZZIE_LAYER)), 0.45F);
        this.addLayer(new SkizzieGlowLayer<>(this));
    }

    public ResourceLocation getTextureLocation(Mob entity) {
        if (entity instanceof FriendlySkizzie) {
            return ((FriendlySkizzie) entity).getHolidayVariation() == 1 ? FRIENDLY_SPOOKZIE_LOCATION : FRIENDLY_SKIZZIE_LOCATION;
        }

        if (ProjectApple.holiday == 1) {
            return entity instanceof KaboomSkizzie ? KABOOM_SPOOKZIE_LOCATION :
                   entity instanceof CorruptedSkizzie ? CORRUPTED_SPOOKZIE_LOCATION : SPOOKZIE_LOCATION;
        }

        return entity instanceof KaboomSkizzie ? KABOOM_SKIZZIE_LOCATION :
               entity instanceof CorruptedSkizzie ? CORRUPTED_SKIZZIE_LOCATION : SKIZZIE_LOCATION;
    }
}
