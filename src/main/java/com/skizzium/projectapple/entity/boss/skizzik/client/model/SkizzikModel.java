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

    private static <T extends Skizzik> void setupHeadRotation(Skizzik skizzik, IBone model, int head) {
        if (!skizzik.getPreview() && model != null) {
            model.setRotationX(skizzik.getHeadXRot(head) * 0.017453292F);
            model.setRotationY((skizzik.getHeadYRot(head) - skizzik.yBodyRot) * 0.017453292F);
        }
    }
    
    @Override
    public void setLivingAnimations(Skizzik skizzik, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(skizzik, uniqueID, customPredicate);
        IBone centerHead = this.getAnimationProcessor().getBone("center_head");

        EntityModelData data = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        centerHead.setRotationX(data.headPitch * ((float) Math.PI / 180F));
        centerHead.setRotationY(data.netHeadYaw * ((float) Math.PI / 180F));
        
        int stage = skizzik.stageManager.getCurrentStage().getStage().getId();
        setupHeadRotation(skizzik, this.getAnimationProcessor().getBone("bottom_right_head"), 0);
        setupHeadRotation(skizzik, this.getAnimationProcessor().getBone("bottom_left_head"), 1);
        setupHeadRotation(skizzik, this.getAnimationProcessor().getBone("top_right_head"), 2);
        setupHeadRotation(skizzik, this.getAnimationProcessor().getBone("top_left_head"), 3);
    }
}
