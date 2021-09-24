package com.skizzium.projectapple.entity.boss.skizzik.client.model;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base.SkizzikFinishHim;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base.SkizzikSleeping;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SkizzikModel extends AnimatedGeoModel<Skizzik> {
    @Override
    public ResourceLocation getTextureLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/%s.png", skizzik.stageManager.getCurrentStage().resourceLocation()));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, String.format("animations/%s.animation.json", skizzik.stageManager.getCurrentStage().resourceLocation()));
    }

    @Override
    public ResourceLocation getModelLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, String.format("geo/%s.geo.json", skizzik.stageManager.getCurrentStage().resourceLocation()));
    }
}
