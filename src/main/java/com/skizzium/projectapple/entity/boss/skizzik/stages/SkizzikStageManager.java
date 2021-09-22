package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;

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
        float health = skizzik.getHealth();
        int newStageId = health > 1020 ? 0 :
                            health > 820 ? 1 :
                                health > 620 ? 2 :
                                    health > 420 ? 3 :
                                        health > 220 ? 4 :
                                            health > 20 ? 5 : 6;
        
        if (newStageId != this.getCurrentStage().getStage().getId()) {
            this.setStage(SkizzikStages.getById(newStageId));
        }
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

    public SkizzikStageInterface getCurrentStage() {
        return this.currentStage;
    }

    public SkizzikStageInterface getPreviousStage() {
        return this.previousStage;
    }

    public <T extends SkizzikStageInterface> T getStage(SkizzikStages<T> phase) {
        int i = phase.getId();
        if (this.stages[i] == null) {
            this.stages[i] = phase.createInstance(this.skizzik);
        }

        return (T)this.stages[i];
    }
}
