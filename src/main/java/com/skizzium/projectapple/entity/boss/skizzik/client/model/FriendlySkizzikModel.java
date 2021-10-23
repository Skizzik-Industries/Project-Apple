package com.skizzium.projectapple.entity.boss.skizzik.client.model;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzik;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class FriendlySkizzikModel extends AnimatedGeoModel<FriendlySkizzik> {
    @Override
    public ResourceLocation getTextureLocation(FriendlySkizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/friendly_skizzik/friendly_skizzik.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FriendlySkizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "animations/skizzik.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(FriendlySkizzik skizzik) {
        return modelLocation(skizzik);
    }

    public static ResourceLocation modelLocation(FriendlySkizzik skizzik) {
        return new ResourceLocation(ProjectApple.MOD_ID, "geo/skizzik/skizzik.geo.json");
    }

    private static void setupHeadRotation(FriendlySkizzik skizzik, IBone model, FriendlySkizzik.Heads head) {
        if (skizzik.getAddedHeads().contains(head) && model != null) {
            if (skizzik.getPassengers().size() > 2) {
                model.setRotationX((skizzik.getPassengers().get(head.ordinal() + 1).getXRot() * 0.5F) * ((float) Math.PI / 180F) * -1);
                model.setRotationY((skizzik.getPassengers().get(head.ordinal() + 1).getYHeadRot() - skizzik.yBodyRot) * ((float) Math.PI / 180F) * -1);
            }
            else {
                model.setRotationX((skizzik.getHeadXRot(head.ordinal()) * ((float) Math.PI / 180F) * -1));
                model.setRotationY(((skizzik.getHeadYRot(head.ordinal()) - skizzik.yBodyRot) * ((float) Math.PI / 180F) * -1));
            }
        }
    }
    
    @Override
    public void setLivingAnimations(FriendlySkizzik skizzik, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(skizzik, uniqueID, customPredicate);
        IBone centerHead = this.getAnimationProcessor().getBone("center_head");
        IBone bottomRightHead = this.getAnimationProcessor().getBone("bottom_right_head");
        IBone bottomLeftHead = this.getAnimationProcessor().getBone("bottom_left_head");
        IBone topRightHead = this.getAnimationProcessor().getBone("top_right_head");
        IBone topLeftHead = this.getAnimationProcessor().getBone("top_left_head");
        
        IBone commandBlock = this.getAnimationProcessor().getBone("command_block");
        IBone rightRibs = this.getAnimationProcessor().getBone("right_ribs");
        IBone leftRibs = this.getAnimationProcessor().getBone("left_ribs");
        IBone bottomRib = this.getAnimationProcessor().getBone("bottom_rib");

        EntityModelData data = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        centerHead.setRotationX(data.headPitch * ((float) Math.PI / 180F));
        centerHead.setRotationY(data.netHeadYaw * ((float) Math.PI / 180F));

        setupHeadRotation(skizzik, bottomRightHead, FriendlySkizzik.Heads.BOTTOM_RIGHT_HEAD);
        setupHeadRotation(skizzik, bottomLeftHead, FriendlySkizzik.Heads.BOTTOM_LEFT_HEAD);
        setupHeadRotation(skizzik, topRightHead, FriendlySkizzik.Heads.TOP_RIGHT_HEAD);
        setupHeadRotation(skizzik, topLeftHead, FriendlySkizzik.Heads.TOP_LEFT_HEAD);

        if (!skizzik.isCommandBlockPlaced()) {
            commandBlock.setHidden(true);
            rightRibs.setHidden(true);
            leftRibs.setHidden(true);
            bottomRib.setHidden(true);
        }
        else {
            commandBlock.setHidden(false);
            rightRibs.setHidden(false);
            leftRibs.setHidden(false);
            bottomRib.setHidden(false);
        }

        bottomRightHead.setHidden(!skizzik.getAddedHeads().contains(FriendlySkizzik.Heads.BOTTOM_RIGHT_HEAD));
        bottomLeftHead.setHidden(!skizzik.getAddedHeads().contains(FriendlySkizzik.Heads.BOTTOM_LEFT_HEAD));
        topRightHead.setHidden(!skizzik.getAddedHeads().contains(FriendlySkizzik.Heads.TOP_RIGHT_HEAD));
        topLeftHead.setHidden(!skizzik.getAddedHeads().contains(FriendlySkizzik.Heads.TOP_LEFT_HEAD));
    }
}
