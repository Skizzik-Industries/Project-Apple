package com.skizzium.projectapple.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.UUID;

public abstract class PA_BossEvent {
    private final UUID id;
    protected Component name;
    protected float progress;
    protected PA_BossBarColor color;
    protected PA_BossBarOverlay overlay;
    protected boolean darkenScreen;
    protected boolean createWorldFog;

    public PA_BossEvent(UUID uuid, Component displayName, PA_BossBarColor assignedColor, PA_BossBarOverlay choosenOverlay) {
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

    public PA_BossBarColor getColor() {
        return this.color;
    }

    public void setColor(PA_BossBarColor newColor) {
        this.color = newColor;
    }

    public PA_BossBarOverlay getOverlay() {
        return this.overlay;
    }

    public void setOverlay(PA_BossBarOverlay newOverlay) {
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

    public static enum PA_BossBarColor {
        RED("red",ChatFormatting.DARK_RED),
        ORANGE("orange", ChatFormatting.GOLD),
        GOLD("gold", ChatFormatting.GOLD),
        YELLOW("yellow", ChatFormatting.YELLOW),
        LIME("lime", ChatFormatting.GREEN),
        GREEN("green", ChatFormatting.DARK_GREEN),
        CYAN("cyan",ChatFormatting.DARK_AQUA),
        AQUA("aqua", ChatFormatting.AQUA),
        BLUE("blue", ChatFormatting.BLUE),
        DARK_BLUE("dark_blue", ChatFormatting.DARK_BLUE),
        PURPLE("purple", ChatFormatting.DARK_PURPLE),
        PINK("pink", ChatFormatting.LIGHT_PURPLE),
        WHITE("white", ChatFormatting.WHITE),
        BLACK("black", ChatFormatting.BLACK);

        private final String name;
        private final ChatFormatting formatting;

        private PA_BossBarColor(String givenName, ChatFormatting color) {
            this.name = givenName;
            this.formatting = color;
        }

        public ChatFormatting getFormatting() {
            return this.formatting;
        }

        public String getName() {
            return this.name;
        }

        public static PA_BossBarColor byName(String nameToSearch) {
            for(PA_BossBarColor colorEnum : values()) {
                if (colorEnum.name.equals(nameToSearch)) {
                    return colorEnum;
                }
            }

            return WHITE;
        }
    }
    
    public static enum PA_BossBarOverlay {
        PROGRESS("progress"),
        NOTCHED_5("notched_5"),
        NOTCHED_6("notched_6"),
        NOTCHED_10("notched_10"),
        NOTCHED_12("notched_12"),
        NOTCHED_20("notched_20");

        private final String name;

        private PA_BossBarOverlay(String givenName) {
            this.name = givenName;
        }

        public String getName() {
            return this.name;
        }

        public static PA_BossEvent.PA_BossBarOverlay byName(String nameToSearch) {
            for(PA_BossEvent.PA_BossBarOverlay overlayEnum : values()) {
                if (overlayEnum.name.equals(nameToSearch)) {
                    return overlayEnum;
                }
            }

            return PROGRESS;
        }
    }
}
