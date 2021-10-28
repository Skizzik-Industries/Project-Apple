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
        return new ResourceLocation(ProjectApple.MOD_ID, "geo/friendly_skizzik.geo.json");
    }

    private static void setupHead(FriendlySkizzik skizzik, IBone model, FriendlySkizzik.Heads head) {
        if (model != null) {
            model.setHidden(skizzik.getDetachedHeads().contains(head) || !skizzik.getAddedHeads().contains(head));
            
            if (skizzik.getAddedHeads().contains(head) && !skizzik.getDetachedHeads().contains(head)) {
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
        IBone bottomRib = this.getAnimationProcessor().getBone("bottom_rib");

        EntityModelData data = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        centerHead.setRotationX(data.headPitch * ((float) Math.PI / 180F));
        centerHead.setRotationY(data.netHeadYaw * ((float) Math.PI / 180F));

        setupHead(skizzik, bottomRightHead, FriendlySkizzik.Heads.BOTTOM_RIGHT_HEAD);
        setupHead(skizzik, bottomLeftHead, FriendlySkizzik.Heads.BOTTOM_LEFT_HEAD);
        setupHead(skizzik, topRightHead, FriendlySkizzik.Heads.TOP_RIGHT_HEAD);
        setupHead(skizzik, topLeftHead, FriendlySkizzik.Heads.TOP_LEFT_HEAD);

        commandBlock.setHidden(!skizzik.isCommandBlockPlaced());
        bottomRib.setHidden(!skizzik.isBottomRibPlaced());
        
        for (FriendlySkizzik.RibSide side : FriendlySkizzik.RibSide.values()) {
            for (FriendlySkizzik.Ribs rib : FriendlySkizzik.Ribs.values()) {
                if (skizzik.getRibs(side).contains(rib)) {
                    this.getAnimationProcessor().getBone(String.format("%s_rib_%d", side.name().toLowerCase(), rib.ordinal() + 1)).setHidden(false);
                }
                else {
                    this.getAnimationProcessor().getBone(String.format("%s_rib_%d", side.name().toLowerCase(), rib.ordinal() + 1)).setHidden(true);
                }
            }
        }
    }
}
