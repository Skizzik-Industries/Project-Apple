package com.skizzium.projectapple.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.BossEvent;

import java.util.UUID;

public abstract class PA_BossEvent {
    private final UUID id;
    protected Component name;
    protected float progress;
    protected ChatFormatting color;
    protected BossEvent.BossBarOverlay overlay;
    protected boolean darkenScreen;
    protected boolean createWorldFog;

    public PA_BossEvent(UUID uuid, Component displayName, ChatFormatting assignedColor, BossEvent.BossBarOverlay choosenOverlay) {
        this.id = uuid;
        this.name = displayName;
        this.color = assignedColor;
        this.overlay = choosenOverlay;
        this.progress = 1.0F;
    }

    public UUID getId() {
        return this.id;
    }

    public Component getName() {
        return this.name;
    }

    public void setName(Component newName) {
        this.name = newName;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float newProgress) {
        this.progress = newProgress;
    }

    public ChatFormatting getColor() {
        return this.color;
    }

    public void setColor(ChatFormatting newColor) {
        this.color = newColor;
    }

    public BossEvent.BossBarOverlay getOverlay() {
        return this.overlay;
    }

    public void setOverlay(BossEvent.BossBarOverlay newOverlay) {
        this.overlay = newOverlay;
    }

    public boolean shouldDarkenScreen() {
        return this.darkenScreen;
    }

    public PA_BossEvent setDarkenScreen(boolean newValue) {
        this.darkenScreen = newValue;
        return this;
    }

    public PA_BossEvent setCreateWorldFog(boolean newValue) {
        this.createWorldFog = newValue;
        return this;
    }

    public boolean shouldCreateWorldFog() {
        return this.createWorldFog;
    }

    public static enum BossBarColor {
        PINK("pink", ChatFormatting.RED),
        BLUE("blue", ChatFormatting.BLUE),
        RED("red", ChatFormatting.DARK_RED),
        GREEN("green", ChatFormatting.GREEN),
        YELLOW("yellow", ChatFormatting.YELLOW),
        PURPLE("purple", ChatFormatting.DARK_BLUE),
        WHITE("white", ChatFormatting.WHITE);

        private final String name;
        private final ChatFormatting formatting;

        private BossBarColor(String p_18881_, ChatFormatting p_18882_) {
            this.name = p_18881_;
            this.formatting = p_18882_;
        }

        public ChatFormatting getFormatting() {
            return this.formatting;
        }

        public String getName() {
            return this.name;
        }

        public static BossEvent.BossBarColor byName(String p_18885_) {
            for(BossEvent.BossBarColor bossevent$bossbarcolor : values()) {
                if (bossevent$bossbarcolor.name.equals(p_18885_)) {
                    return bossevent$bossbarcolor;
                }
            }

            return WHITE;
        }
    }
}
