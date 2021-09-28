package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectlib.gui.PL_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EntityDimensions;

public interface SkizzikStageInterface {
    TextComponent displayName();

    PL_BossEvent.PL_BossBarColor barColor();

    PL_BossEvent.PL_BossBarOverlay barOverlay();

    int transitionTime();
    
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
