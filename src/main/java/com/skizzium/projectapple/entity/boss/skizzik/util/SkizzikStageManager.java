package com.skizzium.projectapple.entity.boss.skizzik.util;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikFinishHim;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikStage1;

public class SkizzikStageManager {
    private final Skizzik skizzik;
    private final SkizzikStageInterface[] stages = new SkizzikStageInterface[SkizzikStages.getCount()];
    private SkizzikStageInterface currentStage;
    private SkizzikStageInterface previousStage;
    
    public SkizzikStageManager(Skizzik skizzik) {
        this.skizzik = skizzik;
        this.setStage(SkizzikStages.SLEEPING);
    }

    public void updateStage() {
        if (!skizzik.isConverting()) {
            float health = skizzik.getHealth();
            int newStageId = health > 1020 ? 0 :
                    health > 820 ? 1 :
                            health > 620 ? 2 :
                                    health > 420 ? 3 :
                                            health > 220 ? 4 :
                                                    health > 20 ? 5 : 6;

            if (skizzik.getHealth() != 0 && !skizzik.getStageDebug() && !(this.currentStage instanceof SkizzikFinishHim) && skizzik.getHealth() <= this.getNextStage().maxStageHealth()) {
                if (skizzik.isTransitioning()) {
                    if (!(skizzik.stageManager.getCurrentStage() instanceof SkizzikStage1)) {
                        this.setStage(this.getNextStage().getStage());
                    }
                } else {
                    this.setStage(this.getNextStage().getStage());
                }
            }

            if (skizzik.getStageDebug() && newStageId != this.getCurrentStage().getStage().getId()) {
                if (skizzik.isTransitioning()) {
                    if (!(skizzik.stageManager.getCurrentStage() instanceof SkizzikStage1)) {
                        this.setStage(SkizzikStages.getById(newStageId));
                    }
                } else {
                    this.setStage(SkizzikStages.getById(newStageId));
                }
            }
        }
            
        this.currentStage.tick();
    }
    
    public void setStage(SkizzikStages<?> stage) {
        if (this.currentStage == null || stage != this.currentStage.getStage()) {
            if (this.currentStage != null) {
                this.currentStage.end(this);
            }

            this.previousStage = this.currentStage;
            this.currentStage = this.getStage(stage);
            
            if (!this.skizzik.level.isClientSide) {
                this.skizzik.getEntityData().set(Skizzik.DATA_STAGE, stage.getId());
            }
            
            this.currentStage.begin(this);
        }
    }

    public SkizzikStageInterface getNextStage() {
        return this.getStage(SkizzikStages.getById(this.currentStage.getStage().getId() + 1));
    }
    
    public SkizzikStageInterface getCurrentStage() {
        return this.currentStage;
    }

    public SkizzikStageInterface getPreviousStage() {
        return this.previousStage;
    }

    public <T extends SkizzikStageInterface> T getStage(SkizzikStages<T> stage) {
        int i = stage.getId();
        if (this.stages[i] == null) {
            this.stages[i] = stage.createInstance(this.skizzik);
        }

        return (T)this.stages[i];
    }
}
