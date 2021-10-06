package com.skizzium.projectapple.gui;

import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class PA_LerpingBossEvent extends LerpingBossEvent {
    private PA_BossEvent.PA_BossBarColor customColor;
    private PA_BossEvent.PA_BossBarOverlay customOverlay;
    private ResourceLocation customTexture;

    public PA_LerpingBossEvent(UUID uuid, Component displayName, float progressPercentage, PA_BossEvent.PA_BossBarColor color, PA_BossEvent.PA_BossBarOverlay overlay, ResourceLocation texture, boolean darkenScreen, boolean fog) {
        super(uuid, displayName, progressPercentage, BossBarColor.WHITE, BossBarOverlay.PROGRESS, darkenScreen, false, fog);
        this.customColor = color;
        this.customOverlay = overlay;
        this.customTexture = texture;
    }

    public PA_BossEvent.PA_BossBarColor getCustomColor() {
        return this.customColor;
    }

    public void setCustomColor(PA_BossEvent.PA_BossBarColor newColor) {
        this.customColor = newColor;
    }

    public PA_BossEvent.PA_BossBarOverlay getCustomOverlay() {
        return this.customOverlay;
    }

    public void setCustomOverlay(PA_BossEvent.PA_BossBarOverlay newOverlay) {
        this.customOverlay = newOverlay;
    }

    public ResourceLocation getCustomTexture() {
        return this.customTexture;
    }

    public void setCustomTexture(ResourceLocation location) {
        this.customTexture = location;
    }
}
