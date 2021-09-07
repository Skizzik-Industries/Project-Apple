package com.skizzium.projectapple.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.BossEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class PA_LerpingBossEvent extends LerpingBossEvent {
    private ChatFormatting customColor;

    public PA_LerpingBossEvent(UUID uuid, Component displayName, float progressPercentage, ChatFormatting color, BossEvent.BossBarOverlay overlay, boolean darkenScreen, boolean fog) {
        super(uuid, displayName, progressPercentage, BossBarColor.WHITE, overlay, darkenScreen, false, fog);
        this.customColor = color;
    }

    public ChatFormatting getCustomColor() {
        return this.customColor;
    }

    public void setCustomColor(ChatFormatting newColor) {
        this.customColor = newColor;
    }
}
