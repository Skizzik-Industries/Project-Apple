package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EntityDimensions;

public interface SkizzikStageInterface {
    TextComponent displayName();

    PA_BossEvent.PA_BossBarColor barColor();

    PA_BossEvent.PA_BossBarOverlay barOverlay();
    
    int armorValue();

    int maxHealth();
    
    int skullLevel();
    
    int skizzieSpawnTicks();
    
    int destroyBlocksTick();
    
    float eyeHeight();
    
    EntityDimensions hitbox();
    
    void begin(SkizzikStageManager stageManager);
    
    void skizzieSpawning();

    void end(SkizzikStageManager stageManager);
    
    SkizzikStages<? extends SkizzikStageInterface> getStage();
}
