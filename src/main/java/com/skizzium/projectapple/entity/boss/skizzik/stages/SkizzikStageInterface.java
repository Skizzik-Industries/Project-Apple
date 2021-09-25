package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EntityDimensions;

public interface SkizzikStageInterface {
    TextComponent displayName();

    PA_BossEvent.PA_BossBarColor barColor();

    PA_BossEvent.PA_BossBarOverlay barOverlay();

    String textureLocation();

    String modelLocation();
    
    int armorValue();

    int maxHealth();
    
    int skullLevel();
    
    int skizzieSpawnTicks();
    
    int destroyBlocksTick();
    
    float eyeHeight();
    
    EntityDimensions hitbox();
    
    boolean hostileAI();
    
    void begin(SkizzikStageManager stageManager);
    
    void addGoals();
    
    void skizzieSpawning();
    
    void tick();

    void end(SkizzikStageManager stageManager);
    
    SkizzikStages<? extends SkizzikStageInterface> getStage();
}
