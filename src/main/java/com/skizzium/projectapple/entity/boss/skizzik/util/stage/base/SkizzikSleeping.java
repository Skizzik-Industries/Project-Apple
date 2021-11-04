package com.skizzium.projectapple.entity.boss.skizzik.util.stage.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.AbstractPassiveSkizzikStage;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;

public class SkizzikSleeping extends AbstractPassiveSkizzikStage {
    public SkizzikSleeping(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public TextComponent displayName() {
        return new TextComponent(String.format("%s - %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.sleeping").getString()));
    }

    @Override
    public int transitionTime() {
        return 0;
    }

    @Override
    public String textureLocation() {
        return String.format("%s/%s_sleeping", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase());
    }

    @Override
    public int maxStageHealth() {
        return 1021;
    }

    @Override
    public int maxHealth() {
        return this.maxStageHealth();
    }

    @Override
    public float eyeHeight() {
        return 1.5F;
    }

    @Override
    public EntityDimensions hitbox() {
        return new EntityDimensions(2.5F, 2.1F, true);
    }

    @Override
    public void addGoals() {
        skizzik.goalSelector.removeAllGoals();
        skizzik.targetSelector.removeAllGoals();
    }

    @Override
    public double getHeadX(int head) {
        if (head <= 0) {
            return skizzik.getX();
        }
        else {
            float f = (skizzik.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);

            return skizzik.getX() + (double)f1;
        }
    }

    @Override
    public double getHeadY(int head) {
        return head == 0 ? skizzik.getY() + 1.072D :
               head <= 2 ? skizzik.getY() + 0.01D :
               skizzik.getY() + 0.76D;
    }

    @Override
    public double getHeadZ(int head) {
        float f = (skizzik.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
        float f1 = Mth.sin(f);
        return head == 1 ? skizzik.getZ() + (double)f1 * 1.187D :
               head == 2 ? skizzik.getZ() + (double)f1 * 1.063D :
               head == 3 ? skizzik.getZ() + (double)f1 * 1.123D :
               head == 4 ? skizzik.getZ() + (double)f1 :
               skizzik.getZ();
    }

    @Override
    public void tickParts() {
        super.tickParts();
        float f = skizzik.yBodyRot * ((float)Math.PI / 180F);
        float f1 = Mth.cos(f);
        float f2 = Mth.sin(f);
        
        skizzik.tickPart(skizzik.topLeftHead, this.getHeadX(4), this.getHeadY(4), this.getHeadZ(4));
        skizzik.tickPart(skizzik.topRightHead, this.getHeadX(3), this.getHeadY(3), this.getHeadZ(3));
        skizzik.tickPart(skizzik.bottomLeftHead, this.getHeadX(2), this.getHeadY(2), this.getHeadZ(2));
        skizzik.tickPart(skizzik.bottomRightHead, this.getHeadX(1), this.getHeadY(1), this.getHeadZ(1));
        skizzik.tickPart(skizzik.centerHead, this.getHeadX(0), this.getHeadY(0), this.getHeadZ(0));
        skizzik.tickPartOffset(skizzik.commandBlockPart, -f2 * 0.63F, 0.06F, f1);
    }
    
    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.SLEEPING;
    }
}
