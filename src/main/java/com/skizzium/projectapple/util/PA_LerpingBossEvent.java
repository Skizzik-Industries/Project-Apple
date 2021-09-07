package com.skizzium.projectapple.util;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class PA_LerpingBossEvent extends PA_BossEvent {
    private static final long LERP_MILLISECONDS = 100L;
    protected float targetPercent;
    protected long setTime;

    public PA_LerpingBossEvent(UUID uuid, Component displayName, float progressPercentage, ChatFormatting color, BossEvent.BossBarOverlay overlay, boolean darkenScreen, boolean fog) {
        super(uuid, displayName, color, overlay);
        this.targetPercent = progressPercentage;
        this.progress = progressPercentage;
        this.setTime = Util.getMillis();
        this.setDarkenScreen(darkenScreen);
        this.setCreateWorldFog(fog);
    }

    public void setProgress(float progress) {
        this.progress = this.getProgress();
        this.targetPercent = progress;
        this.setTime = Util.getMillis();
    }

    public float getProgress() {
        long i = Util.getMillis() - this.setTime;
        float f = Mth.clamp((float)i / 100.0F, 0.0F, 1.0F);
        return Mth.lerp(f, this.progress, this.targetPercent);
    }
}
