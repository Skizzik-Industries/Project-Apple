package com.skizzium.projectapple.entity.boss.skizzik.stages;

import net.minecraft.network.chat.TextComponent;

public interface SkizzikStageInterface {
    TextComponent displayName();
    
    void begin();

    void end();
    
    SkizzikStage<? extends SkizzikStageInterface> getStage();
}
