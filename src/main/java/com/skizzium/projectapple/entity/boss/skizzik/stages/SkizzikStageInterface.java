package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.gui.PA_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public interface SkizzikStageInterface {
    TextComponent displayName();

    PA_BossEvent.PA_BossBarColor barColor();

    PA_BossEvent.PA_BossBarOverlay barOverlay();

    int transitionTime();
    
    String textureLocation();
    
    int maxStageHealth();
    
    int armorValue();

    int maxHealth();
    
    int skullLevel();
    
    int skizzieSpawnTicks();
    
    int destroyBlocksTick();
    
    float eyeHeight();
    
    EntityDimensions hitbox();
    
    boolean playMusic();
    
    boolean attackStatically();
    
    boolean attackDirectly();
    
    void begin(SkizzikStageManager stageManager);
    
    void addGoals();
    
    void skizzieSpawning();
    
    double getHeadX(int head);

    double getHeadY(int head);

    double getHeadZ(int head);
    
    void tickParts();
    
    void tick();

    void end(SkizzikStageManager stageManager);
    
    SkizzikStages<? extends SkizzikStageInterface> getStage();
}
