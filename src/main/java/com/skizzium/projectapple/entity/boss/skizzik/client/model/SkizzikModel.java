package com.skizzium.projectapple.entity.boss.skizzik.client.model;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SkizzikModel extends AnimatedGeoModel<Skizzik> {
    @Override
    public ResourceLocation getTextureLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/skizzik/skizzik_sleeping.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "animations/skizzik.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "geo/skizzik/skizzik_sleeping.geo.json");
    }
}
