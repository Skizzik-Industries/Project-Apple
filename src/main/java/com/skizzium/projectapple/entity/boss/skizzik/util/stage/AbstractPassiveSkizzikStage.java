package com.skizzium.projectapple.entity.boss.skizzik.util.stage;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageManager;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikSleeping;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.network.RPCPacket;
import com.skizzium.projectapple.network.SkizzoConnectionParticlesPacket;
import com.skizzium.projectlib.gui.PL_BossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;

public abstract class AbstractPassiveSkizzikStage extends AbstractSkizzikStage {
    public AbstractPassiveSkizzikStage(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public PL_BossEvent.PL_BossBarColor barColor() {
        return PL_BossEvent.PL_BossBarColor.WHITE;
    }

    @Override
    public PL_BossEvent.PL_BossBarOverlay barOverlay() {
        return PL_BossEvent.PL_BossBarOverlay.PROGRESS;
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
    public boolean playMusic() {
        return false;
    }

    @Override
    public boolean attackStatically() {
        return false;
    }
    
    @Override
    public boolean attackDirectly() {
        return false;
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        skizzik.setHealth(this.maxStageHealth());
        skizzik.setEyeHeight(this.eyeHeight());

        if (!this.skizzik.level.isClientSide) {
            if (!(this instanceof SkizzikSleeping)) {
                PA_PacketRegistry.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.skizzik), new RPCPacket(RPCPacket.RPCAction.RELOAD, this.skizzik.getId()));
            }
        }
        
        if (!(stageManager.getCurrentStage() instanceof SkizzikSleeping)) {
            skizzik.setTransitionsTicks(this.transitionTime());
            skizzik.setTransitioning(true);
        }
    }

    @Override
    public void skizzieSpawning() {}
}
