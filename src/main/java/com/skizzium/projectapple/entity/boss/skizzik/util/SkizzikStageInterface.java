package com.skizzium.projectapple.entity.boss.skizzik.util;

import com.skizzium.projectlib.gui.PL_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public interface SkizzikStageInterface {
    TextComponent displayName();

    PL_BossEvent.PL_BossBarColor barColor();

    PL_BossEvent.PL_BossBarOverlay barOverlay();

    String rpcStageDetails();
    
    String rpcImageKey();
    
    String rpcImageText();
    
    int transitionTime();
    
    String textureLocation();
    
    int maxStageHealth();
    
    int armorValue();

    int maxHealth();
    
    int skullLevel();

    int safeLightningTicks();
    
    int lightningTicks();
    
    int skizzieSpawnTicks();
    
    int destroyBlocksTicks();
    
    float eyeHeight();
    
    float floatY();
    
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
