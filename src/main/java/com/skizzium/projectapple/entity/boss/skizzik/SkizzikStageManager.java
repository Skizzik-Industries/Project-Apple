package com.skizzium.projectapple.entity.boss.skizzik;

import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStage;

public class SkizzikStageManager {
    private final Skizzik skizzik;
    private final SkizzikStageInterface[] stages = new SkizzikStageInterface[SkizzikStage.getCount()];
    private SkizzikStageInterface currentStage;
    
    public SkizzikStageManager(Skizzik skizzik) {
        this.skizzik = skizzik;
        this.setStage(SkizzikStage.SLEEPING);
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
            this.setStage(SkizzikStage.getById(newStageId));
        }
    }
    
    public void setStage(SkizzikStage<?> stage) {
        if (this.currentStage == null || stage != this.currentStage.getStage()) {
            if (this.currentStage != null) {
                this.currentStage.end();
            }

            this.currentStage = this.getStage(stage);
            if (!this.skizzik.level.isClientSide) {
                this.skizzik.getEntityData().set(Skizzik.DATA_STAGE, stage.getId());
            }
            
            this.currentStage.begin();
        }
    }

    public SkizzikStageInterface getCurrentStage() {
        return this.currentStage;
    }

    public <T extends SkizzikStageInterface> T getStage(SkizzikStage<T> phase) {
        int i = phase.getId();
        if (this.stages[i] == null) {
            this.stages[i] = phase.createInstance(this.skizzik);
        }

        return (T)this.stages[i];
    }
}
