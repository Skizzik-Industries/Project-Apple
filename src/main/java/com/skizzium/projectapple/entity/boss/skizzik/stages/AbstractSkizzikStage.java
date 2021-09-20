package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.Skizzo;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.init.PA_PacketHandler;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.network.BossMusicStartPacket;
import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public abstract class AbstractSkizzikStage implements SkizzikStageInterface {
    protected final Skizzik skizzik;
    
    public AbstractSkizzikStage(Skizzik skizzik) {
        this.skizzik = skizzik;
    }

    @Override
    public TextComponent displayName() {
        return new TextComponent(String.format("%s - %s %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.stage").getString(), this.getStage().getId()));
    }

    @Override
    public PA_BossEvent.PA_BossBarColor barColor() {
        return ProjectApple.holiday == 1 ? PA_BossEvent.PA_BossBarColor.ORANGE : PA_BossEvent.PA_BossBarColor.RED;
    }

    @Override
    public PA_BossEvent.PA_BossBarOverlay barOverlay() {
        return PA_BossEvent.PA_BossBarOverlay.NOTCHED_5;
    }

    @Override
    public int armorValue() {
        return 4;
    }

    @Override
    public int maxHealth() {
        return 1020;
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        int id = this.getStage().getId();
        int previousId = stageManager.getPreviousStage().getStage().getId();
        Level world = skizzik.level;
        
        skizzik.killAllSkizzies(world, true);
        
        if (world instanceof ServerLevel) {
            PA_PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.skizzik), new BossMusicStartPacket(PA_SoundEvents.MUSIC_SKIZZIK_LAZY.get()));
        }

        if (!skizzik.getPreview()) {
            if (world instanceof ServerLevel) {
                if (id > 1 && id <= 5) {
                    int difference;

                    if (previousId == 0) {
                        difference = id - previousId - 1;
                    } else if (previousId == 6) {
                        difference = id - previousId + 1;
                    } else {
                        difference = id - previousId;
                    }

                    if (difference > 0) {
                        for (int i = 1; i <= difference; i++) {
                            Skizzo skizzo = (Skizzo) PA_Entities.SKIZZO.get().spawn((ServerLevel) world, null, null, skizzik.blockPosition(), MobSpawnType.MOB_SUMMONED, true, true);
                            skizzo.setOwner(skizzik);
                            //skizzos[i - 1] = skizzo;
                        }
                    }
                    /* else {
                        difference = Math.abs(difference);
                        for (int i = 1; i <= difference; i++) {
                            ((ServerLevel) world).removeEntity(skizzos[i]);
                        }
                    } */
                }
            }
        }
    }
    
    @Override
    public void end(SkizzikStageManager stageManager) {}
}
