package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
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
    public int maxHealth() {
        return 1021;
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
    public void begin(SkizzikStageManager stageManager) {
        super.begin(stageManager);
        skizzik.goalSelector.removeAllGoals();
    }

    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.SLEEPING;
    }
}
