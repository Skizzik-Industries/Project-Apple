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
        return new ResourceLocation(ProjectApple.MOD_ID, "geo/skizzik/skizzik.geo.json");
    }

    private static <T extends FriendlySkizzik> void setupHeadRotation(FriendlySkizzik skizzik, IBone model, int head) {
        if (model != null) {
            model.setRotationX((skizzik.getHeadXRot(head) * ((float) Math.PI / 180F) * -1));
            model.setRotationY(((skizzik.getHeadYRot(head) - skizzik.yBodyRot) * ((float) Math.PI / 180F) * -1));
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

        EntityModelData data = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        centerHead.setRotationX(data.headPitch * ((float) Math.PI / 180F));
        centerHead.setRotationY(data.netHeadYaw * ((float) Math.PI / 180F));

        setupHeadRotation(skizzik, bottomRightHead, 0);
        setupHeadRotation(skizzik, bottomLeftHead, 1);
        setupHeadRotation(skizzik, topRightHead, 2);
        setupHeadRotation(skizzik, topLeftHead, 3);
    }
}
