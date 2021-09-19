package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.network.chat.TextComponent;

public interface SkizzikStageInterface {
    TextComponent displayName();

    PA_BossEvent.PA_BossBarColor barColor();

    PA_BossEvent.PA_BossBarOverlay barOverlay();
    
    void begin();

    void end();
    
    SkizzikStage<? extends SkizzikStageInterface> getStage();
}
