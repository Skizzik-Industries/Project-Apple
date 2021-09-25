package com.skizzium.projectapple.entity.boss.skizzik.client.model;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base.SkizzikFinishHim;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base.SkizzikSleeping;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class SkizzikModel extends AnimatedGeoModel<Skizzik> {
    @Override
    public ResourceLocation getTextureLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/%s.png", skizzik.stageManager.getCurrentStage().textureLocation()));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "animations/skizzik.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, String.format("geo/%s.geo.json", skizzik.stageManager.getCurrentStage().modelLocation()));
    }

    private static <T extends Skizzik> void setupHeadRotation(Skizzik entity, IBone model, int head) {
        if (!entity.getPreview() && !model.isHidden()) {
            model.setRotationY((entity.getHeadYRot(head) - entity.yBodyRot) * 0.017453292F);
            model.setRotationX(entity.getHeadXRot(head) * 0.017453292F);
        }
    }
    
    @Override
    public void setLivingAnimations(Skizzik entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        /*IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData data = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(data.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(data.netHeadYaw * ((float) Math.PI / 180F));

        setupHeadRotation(entity, this.getAnimationProcessor().getBone("bottomRightHead"), 0);
        setupHeadRotation(entity, this.getAnimationProcessor().getBone("bottomLeftHead"), 1);
        setupHeadRotation(entity, this.getAnimationProcessor().getBone("topRightHead"), 2);
        setupHeadRotation(entity, this.getAnimationProcessor().getBone("topLeftHead"), 3);*/
    }
}
