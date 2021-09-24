package com.skizzium.projectapple.entity.boss.skizzik.stages.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageManager;
import com.skizzium.projectapple.init.PA_PacketHandler;
import com.skizzium.projectapple.network.BossMusicStopPacket;
import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public abstract class AbstractPassiveSkizzikStage extends AbstractSkizzikStage {
    public AbstractPassiveSkizzikStage(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public PA_BossEvent.PA_BossBarColor barColor() {
        return PA_BossEvent.PA_BossBarColor.WHITE;
    }

    @Override
    public PA_BossEvent.PA_BossBarOverlay barOverlay() {
        return PA_BossEvent.PA_BossBarOverlay.PROGRESS;
    }

    @Override
    public int armorValue() {
        return 0;
    }
    
    @Override
    public int skullLevel() {
        return 0;
    }

    @Override
    public int skizzieSpawnTicks() {
        return 0;
    }

    @Override
    public boolean hostileAI() {
        return false;
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        skizzik.setEyeHeight(this.eyeHeight());
        if (skizzik.level instanceof ServerLevel) {
            PA_PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.skizzik), new BossMusicStopPacket());
        }
    }

    @Override
    public void skizzieSpawning() {
        
    }
}
