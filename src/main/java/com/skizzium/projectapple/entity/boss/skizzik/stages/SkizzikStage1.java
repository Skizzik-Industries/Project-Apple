package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class SkizzikStage1 extends AbstractSkizzikStage {
    public SkizzikStage1(Skizzik skizzik) {
        super(skizzik);
    }
    
    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.STAGE_1;
    }
}
