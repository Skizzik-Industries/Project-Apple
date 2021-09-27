package com.skizzium.projectapple.util;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.skizzium.projectapple.network.PA_BossEventOperations;
import com.skizzium.projectapple.network.PA_BossEventPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class PA_ServerBossEvent extends PA_BossEvent {
    private final Set<ServerPlayer> players = Sets.newHashSet();
    private final Set<ServerPlayer> unmodifiablePlayers = Collections.unmodifiableSet(this.players);
    private boolean visible = true;

    public PA_ServerBossEvent(Component displayName, PA_BossBarColor color, PA_BossBarOverlay overlay) {
        super(Mth.createInsecureUUID(), displayName, color, overlay);
    }

    public void setProgress(float newProgress) {
        if (newProgress != this.progress) {
            super.setProgress(newProgress);
            this.broadcast(PA_BossEventOperations::createUpdateProgressPacket);
        }

    }

    public void setColor(PA_BossBarColor newColor) {
        if (newColor != this.color) {
            super.setColor(newColor);
            this.broadcast(PA_BossEventOperations::createUpdateStylePacket);
        }

    }

    public void setOverlay(PA_BossBarOverlay newOverlay) {
        if (newOverlay != this.overlay) {
            super.setOverlay(newOverlay);
            this.broadcast(PA_BossEventOperations::createUpdateStylePacket);
        }

    }

    public PA_BossEvent setDarkenScreen(boolean newValue) {
        if (newValue != this.darkenScreen) {
            super.setDarkenScreen(newValue);
            this.broadcast(PA_BossEventOperations::createUpdatePropertiesPacket);
        }

        return this;
    }

    public PA_BossEvent setCreateWorldFog(boolean newValue) {
        if (newValue != this.createWorldFog) {
            super.setCreateWorldFog(newValue);
            this.broadcast(PA_BossEventOperations::createUpdatePropertiesPacket);
        }

        return this;
    }

    public void setName(Component newName) {
        if (!Objects.equal(newName, this.name)) {
            super.setName(newName);
            this.broadcast(PA_BossEventOperations::createUpdateNamePacket);
        }

    }

    private void broadcast(Function<PA_BossEvent, PA_BossEventPacket> function) {
        if (this.visible) {
            PA_BossEventPacket bossEventPacket = function.apply(this);

            for(ServerPlayer serverplayer : this.players) {
                serverplayer.connection.send(bossEventPacket);
            }
        }

    }

    public void addPlayer(ServerPlayer player) {
        if (this.players.add(player) && this.visible) {
            player.connection.send(PA_BossEventOperations.createAddPacket(this));
        }

    }

    public void removePlayer(ServerPlayer player) {
        if (this.players.remove(player) && this.visible) {
            player.connection.send(PA_BossEventOperations.createRemovePacket(this.getId()));
        }

    }

    public void removeAllPlayers() {
        if (!this.players.isEmpty()) {
            for(ServerPlayer serverplayer : Lists.newArrayList(this.players)) {
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

            for(ServerPlayer serverplayer : this.players) {
                serverplayer.connection.send(newValue ? PA_BossEventOperations.createAddPacket(this) : PA_BossEventOperations.createRemovePacket(this.getId()));
            }
        }

    }

    public Collection<ServerPlayer> getPlayers() {
        return this.unmodifiablePlayers;
    }
}