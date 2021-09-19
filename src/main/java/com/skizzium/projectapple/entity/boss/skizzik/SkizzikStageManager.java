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

    public void setStage(SkizzikStage<?> phase) {
        if (this.currentStage == null || phase != this.currentStage.getStage()) {
            if (this.currentStage != null) {
                this.currentStage.end();
            }

            this.currentStage = this.getStage(phase);
            if (!this.skizzik.level.isClientSide) {
                this.skizzik.getEntityData().set(Skizzik.DATA_STAGE, phase.getId());
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
