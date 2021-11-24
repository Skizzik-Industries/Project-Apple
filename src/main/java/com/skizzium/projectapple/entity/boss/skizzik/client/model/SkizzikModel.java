package com.skizzium.projectapple.entity.boss.skizzik.client.model;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikSleeping;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikStage1;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SkizzikModel extends AnimatedGeoModel<Skizzik> {
    @Override
    public ResourceLocation getTextureLocation(Skizzik skizzik) {
        if (ProjectApple.holiday > 0) {
            return new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/holidays/%s/%s.png", ProjectApple.holidayNames.get(ProjectApple.holiday), skizzik.stageManager.getCurrentStage().textureLocation()));
        }
        return new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/%s.png", skizzik.stageManager.getCurrentStage().textureLocation()));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Skizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "animations/skizzik.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Skizzik skizzik) {
        String modelName = "skizzik";
        if (skizzik.stageManager.getCurrentStage() instanceof SkizzikSleeping) {
            modelName += "_sleeping";
        }
        else if (skizzik.isTransitioning() && skizzik.stageManager.getCurrentStage() instanceof SkizzikStage1) {
            modelName += "_sleeping";
        }
        
        return new ResourceLocation(ProjectApple.MOD_ID, String.format("geo/%s.geo.json", modelName));
    }

    private static <T extends Skizzik> void setupHeadRotation(Skizzik skizzik, IBone model, int head) {
        if (!skizzik.getPreview() && model != null) {
            model.setRotationX((skizzik.getHeadXRot(head) * ((float) Math.PI / 180F) * -1));
            model.setRotationY(((skizzik.getHeadYRot(head) - skizzik.yBodyRot) * ((float) Math.PI / 180F) * -1));
        }
    }

    private static void setRot(IBone bone, float x, float y, float z) {
        bone.setRotationX(x);
        bone.setRotationY(y);
        bone.setRotationZ(z);
    }

    private static void setPos(IBone bone, float x, float y, float z) {
        bone.setPositionX(x);
        bone.setPositionY(y);
        bone.setPositionZ(z);
    }
    
    private static void setPivot(IBone bone, float x, float y, float z) {
        bone.setPivotX(x);
        bone.setPivotY(y);
        bone.setPivotZ(z);
        setPos(bone, bone.getPivotX(), bone.getPivotY(), bone.getPivotZ());
    }
    
    @Override
    public void setLivingAnimations(Skizzik skizzik, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(skizzik, uniqueID, customPredicate);
        IBone centerHead = this.getAnimationProcessor().getBone("center_head");
        IBone bottomRightHead = this.getAnimationProcessor().getBone("bottom_right_head");
        IBone bottomLeftHead = this.getAnimationProcessor().getBone("bottom_left_head");
        IBone topRightHead = this.getAnimationProcessor().getBone("top_right_head");
        IBone topLeftHead = this.getAnimationProcessor().getBone("top_left_head");

        IBone rightRibs = this.getAnimationProcessor().getBone("right_ribs");
        IBone leftRibs = this.getAnimationProcessor().getBone("left_ribs");
        IBone bottomRib = this.getAnimationProcessor().getBone("bottom_rib");
        IBone backRibs = this.getAnimationProcessor().getBone("back_ribs");

        IBone commandBlock = this.getAnimationProcessor().getBone("command_block");
        IBone body = this.getAnimationProcessor().getBone("body");
        IBone tail = this.getAnimationProcessor().getBone("tail");


        EntityModelData data = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        centerHead.setRotationX(data.headPitch * ((float) Math.PI / 180F));
        centerHead.setRotationY(data.netHeadYaw * ((float) Math.PI / 180F));

        setupHeadRotation(skizzik, bottomRightHead, 0);
        setupHeadRotation(skizzik, bottomLeftHead, 1);
        setupHeadRotation(skizzik, topRightHead, 2);
        setupHeadRotation(skizzik, topLeftHead, 3);

        List<String> hideStage2 = List.of("top_left_head");
        List<String> hideStage3 = new ArrayList<>(hideStage2);
        hideStage3.add("top_right_head");
        List<String> hideStage4 = new ArrayList<>(hideStage3);
        hideStage4.add("bottom_left_head");
        List<String> hideStage5 = new ArrayList<>(hideStage4);
        hideStage5.add("bottom_right_head");
        List<String> hideFinishHim = new ArrayList<>(hideStage5);
        hideFinishHim.add("right_ribs");
        hideFinishHim.add("left_ribs");
        hideFinishHim.add("bottom_rib");
        hideFinishHim.add("back_ribs");
        hideFinishHim.add("command_block");
        List<List<String>> hide = List.of(List.of(), hideStage2, hideStage3, hideStage4, hideStage5, hideFinishHim);

        int currentStageId = skizzik.stageManager.getCurrentStage().getStage().getId();
        for (Object bone : this.getAnimationProcessor().getModelRendererList()) {
            if (bone instanceof IBone && !(skizzik.stageManager.getCurrentStage() instanceof SkizzikSleeping)) {
                ((IBone) bone).setHidden(hide.get(currentStageId - 1).contains(((IBone) bone).getName()));
            }
        }
        
        if (currentStageId == 0) {
            setPivot(centerHead, -0.5F, 0.0F, 0.0F);
            setPivot(body, -0.5F, 0.0F, 0.0F);
        }

        if (!skizzik.isTransitioning()) {
            if (currentStageId == 2) {
                setPivot(topRightHead, -21.0F, 2.0F, 0.0F);
            }

            if (currentStageId == 3) {
                setPivot(bottomRightHead, 0.0F, 4.0F, 0.0F);
            }

            if (currentStageId == 4) {
                setPivot(bottomRightHead, -8.0F, 23.0F, 0.0F);
            }

            if (currentStageId == 5) {
                setPivot(centerHead, 0.0F, 5.0F, 0.0F);
                setPivot(commandBlock, 0.0F, 0.0F, -10.0F);

                setRot(rightRibs, 0.0F, -6.05352F, 0.0F);
                setRot(leftRibs, 0.0F, 6.05352F, 0.0F);
                setRot(bottomRib, -0.22394F, 0.0F, 0.0F);
            }

            if (currentStageId == 6) {
                setPivot(centerHead, 0.0F, -2.3F, 0.0F);
            }
        }
    }
}
