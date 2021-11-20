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
        return new ResourceLocation(ProjectApple.MOD_ID, "animations/friendly_skizzik.animation.json");
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
//                if (skizzik.getRiddenHeads().contains(head)) {
//                    model.setRotationX((skizzik.getParts()[head.ordinal()].getXRot() * 0.5F) * ((float) Math.PI / 180F) * -1);
//                    model.setRotationY((skizzik.getParts()[head.ordinal()].getYRot() - skizzik.yBodyRot) * ((float) Math.PI / 180F) * -1);
//                } 
//                else {
                    model.setRotationX((skizzik.getHeadXRot(head.ordinal()) * ((float) Math.PI / 180F) * -1));
                    model.setRotationY(((skizzik.getHeadYRot(head.ordinal()) - skizzik.yBodyRot) * ((float) Math.PI / 180F) * -1));
//                }
            }
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
    public void setLivingAnimations(FriendlySkizzik skizzik, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(skizzik, uniqueID, customPredicate);
        for (Object bone : this.getAnimationProcessor().getModelRendererList()) {
            ((IBone) bone).setHidden(false);
        }
        
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
                this.getAnimationProcessor().getBone(String.format("%s_rib_%d", side.name().toLowerCase(), rib.ordinal() + 1)).setHidden(!skizzik.getRibs(side).contains(rib));
            }
        }
        
        if (skizzik.getAddedHeads().size() - skizzik.getDetachedHeads().size() == 3) {
            setPivot(topRightHead, -21.0F, 2.0F, 0.0F);
        }

        if (skizzik.getAddedHeads().size() - skizzik.getDetachedHeads().size() == 2) {
            setPivot(bottomRightHead, 0.0F, 4.0F, 0.0F);
        }

        if (skizzik.getAddedHeads().size() - skizzik.getDetachedHeads().size() == 1) {
            setPivot(bottomRightHead, -8.0F, 23.0F, 0.0F);
        }
        
        if (skizzik.renderHeadOnly != null) {
            for (Object bone : this.getAnimationProcessor().getModelRendererList()) {
                if (!((IBone) bone).getName().equals(skizzik.renderHeadOnly.name().toLowerCase())) {
                    ((IBone) bone).setHidden(true);
                }
                else {
                    ((IBone) bone).setHidden(false);
                }
            }
        }
    }
}
