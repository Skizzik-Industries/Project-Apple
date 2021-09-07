package com.skizzium.projectapple.util;

import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.BossEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class PA_LerpingBossEvent extends LerpingBossEvent {
    private PA_BossEvent.PA_BossBarColor customColor;

    public PA_LerpingBossEvent(UUID uuid, Component displayName, float progressPercentage, PA_BossEvent.PA_BossBarColor color, BossEvent.BossBarOverlay overlay, boolean darkenScreen, boolean fog) {
        super(uuid, displayName, progressPercentage, BossBarColor.WHITE, overlay, darkenScreen, false, fog);
        this.customColor = color;
    }

    public PA_BossEvent.PA_BossBarColor getCustomColor() {
        return this.customColor;
    }

    public void setCustomColor(PA_BossEvent.PA_BossBarColor newColor) {
        this.customColor = newColor;
    }
}
