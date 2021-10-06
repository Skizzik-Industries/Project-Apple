package com.skizzium.projectapple.gui;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.network.bossevent.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.fmllegacy.network.NetworkDirection;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class PA_ServerBossEvent extends PA_BossEvent {
    private final Set<ServerPlayer> players = Sets.newHashSet();
    private final Set<ServerPlayer> unmodifiablePlayers;
    private boolean visible;

    public PA_ServerBossEvent(Component displayName, PA_BossBarColor color, PA_BossBarOverlay overlay) {
        super(Mth.createInsecureUUID(), displayName, color, overlay);
        this.unmodifiablePlayers = Collections.unmodifiableSet(this.players);
        this.visible = true;
    }

    private void broadcastUpdatePacket() {
        if (this.visible) {
            for (ServerPlayer player : this.players) {
                PA_PacketRegistry.INSTANCE.sendTo(new UpdateBossEventPacket(this), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
            }
        }
    }

    public void setName(Component newName) {
        if (!Objects.equal(newName, this.name)) {
            super.setName(newName);
            this.broadcastUpdatePacket();
        }
    }

    public void setColor(PA_BossBarColor color) {
        if (color != this.color) {
            super.setColor(color);
            this.broadcastUpdatePacket();
        }
    }

    public void setOverlay(PA_BossBarOverlay overlay) {
        if (overlay != this.overlay) {
            super.setOverlay(overlay);
            this.broadcastUpdatePacket();
        }
    }

    public void setTexture(ResourceLocation location) {
        if (location != this.customTexture) {
            super.setCustomTexture(location);
            this.broadcastUpdatePacket();
        }
    }

    public PA_BossEvent setDarkenScreen(boolean flag) {
        if (flag != this.darkenScreen) {
            super.setDarkenScreen(flag);
            this.broadcastUpdatePacket();
        }

        return this;
    }

    public PA_BossEvent setCreateWorldFog(boolean flag) {
        if (flag != this.createWorldFog) {
            super.setCreateWorldFog(flag);
            this.broadcastUpdatePacket();
        }

        return this;
    }

    public void setProgress(float f) {
        if (f != this.progress) {
            super.setProgress(f);
            this.broadcastUpdatePacket();
        }

    }

    public void addPlayer(ServerPlayer player) {
        if (this.players.add(player) && this.visible) {
            PA_PacketRegistry.INSTANCE.sendTo(new AddBossEventPacket(this), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }

    }

    public void removePlayer(ServerPlayer player) {
        if (this.players.remove(player) && this.visible) {
            PA_PacketRegistry.INSTANCE.sendTo(new RemoveBossEventPacket(this), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }

    }

    public void removeAllPlayers() {
        if (!this.players.isEmpty()) {
            for (ServerPlayer serverplayer : Lists.newArrayList(this.players)) {
                this.removePlayer(serverplayer);
            }
        }

    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean newValue) {
        if (newValue != this.visible) {
            this.visible = newValue;

            for (ServerPlayer player : this.players) {
                if (newValue) {
                    PA_PacketRegistry.INSTANCE.sendTo(new AddBossEventPacket(this), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
                } else {
                    PA_PacketRegistry.INSTANCE.sendTo(new RemoveBossEventPacket(this), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
                }
            }
        }

    }

    public Collection<ServerPlayer> getPlayers() {
        return this.unmodifiablePlayers;
    }
}

