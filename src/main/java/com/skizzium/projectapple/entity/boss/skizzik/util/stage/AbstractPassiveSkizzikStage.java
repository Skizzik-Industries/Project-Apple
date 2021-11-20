package com.skizzium.projectapple.entity.boss.skizzik.util.stage;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageManager;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikSleeping;
import com.skizzium.projectlib.gui.PL_BossEvent;

public abstract class AbstractPassiveSkizzikStage extends AbstractSkizzikStage {
    public AbstractPassiveSkizzikStage(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public PL_BossEvent.PL_BossBarColor barColor() {
        return PL_BossEvent.PL_BossBarColor.WHITE;
    }

    @Override
    public PL_BossEvent.PL_BossBarOverlay barOverlay() {
        return PL_BossEvent.PL_BossBarOverlay.PROGRESS;
    }

    @Override
    public int armorValue() {
        return 0;
    }
    
    @Override
    public int skullLevel() {
        return 0;
    }

    @Override
    public int skizzieSpawnTicks() {
        return 0;
    }

    @Override
    public boolean playMusic() {
        return false;
    }

    @Override
    public boolean attackStatically() {
        return false;
    }
    
    @Override
    public boolean attackDirectly() {
        return false;
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        skizzik.setHealth(this.maxStageHealth());
        skizzik.setEyeHeight(this.eyeHeight());
        
        if (!(stageManager.getCurrentStage() instanceof SkizzikSleeping)) {
            skizzik.setTransitionsTicks(this.transitionTime());
            skizzik.setTransitioning(true);
        }
    }

    @Override
    public void skizzieSpawning() {}
}
