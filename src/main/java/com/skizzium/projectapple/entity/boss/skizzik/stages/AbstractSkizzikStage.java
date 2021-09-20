package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.init.PA_PacketHandler;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.network.BossMusicStartPacket;
import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
        if (skizzik.level instanceof ServerLevel) {
            PA_PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.skizzik), new BossMusicStartPacket(PA_SoundEvents.MUSIC_SKIZZIK_LAZY.get()));
        }
    }
    
    @Override
    public void end(SkizzikStageManager stageManager) {}
}
