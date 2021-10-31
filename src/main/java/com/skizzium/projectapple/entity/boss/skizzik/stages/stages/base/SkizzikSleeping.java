package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageManager;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractPassiveSkizzikStage;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
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
    public void tickParts() {
        super.tickParts();
        skizzik.tickPart(skizzik.topLeftHead, -0.062F, 0.76F, 1.063F);
        skizzik.tickPart(skizzik.topRightHead, 0.0F, 0.76F, -1.123F);
        skizzik.tickPart(skizzik.bottomLeftHead, -0.062F, 0.01F, 1.125F);
        skizzik.tickPart(skizzik.bottomRightHead, 0.0F, 0.01F, -1.187F);
        skizzik.tickPart(skizzik.centerHead, 0.0F, 1.072F, -0.063F);
        skizzik.tickPart(skizzik.commandBlockPart, 0.63F, 0.06F, -0.03F);
    }
    
    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.SLEEPING;
    }
}
