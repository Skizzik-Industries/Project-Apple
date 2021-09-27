package com.skizzium.projectapple.init.network;

import com.skizzium.projectapple.network.BossMusicStartPacket;
import com.skizzium.projectapple.network.BossMusicStopPacket;
import com.skizzium.projectapple.network.PA_BossEventPacket;
import com.skizzium.projectapple.sound.BossMusic;
import com.skizzium.projectapple.util.PA_BossEvent;
import com.skizzium.projectapple.util.PA_LerpingBossEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class PA_PacketHandler {
    public static void handleBossMusicStartPacket(BossMusicStartPacket packet, Supplier<NetworkEvent.Context> context) {
        Minecraft.getInstance().getMusicManager().stopPlaying();
        if (!BossMusicStartPacket.isPlaying) {
            BossMusicStartPacket.music = new BossMusic(packet.musicEvent, Minecraft.getInstance());
            Minecraft.getInstance().getSoundManager().play(BossMusicStartPacket.music);
            BossMusicStartPacket.isPlaying = true;
        }
    }

    public static void handleBossMusicStopPacket(BossMusicStopPacket packet, Supplier<NetworkEvent.Context> context) {
        //Minecraft.getInstance().getMusicManager().stopPlaying();

        Minecraft.getInstance().getSoundManager().stop(BossMusicStartPacket.music);
        BossMusicStartPacket.isPlaying = false;

        Music music = Minecraft.getInstance().getSituationalMusic();
        MusicManager musicManager = Minecraft.getInstance().getMusicManager();
        musicManager.nextSongDelay = Math.min(100, Mth.nextInt(new Random(), music.getMinDelay(), music.getMaxDelay()));
    }

    public static void handleBossEventPacket(PA_BossEventPacket packet) {
        packet.dispatch(new PA_BossEventPacket.Handler() {
            final Map<UUID, LerpingBossEvent> vanillaEvents = Minecraft.getInstance().gui.getBossOverlay().events;
            public void add(UUID uuid, Component displayName, float progress, PA_BossEvent.PA_BossBarColor color, PA_BossEvent.PA_BossBarOverlay overlay, boolean darkenScreen, boolean fog) {
                vanillaEvents.put(uuid, new PA_LerpingBossEvent(uuid, displayName, progress, color, overlay, darkenScreen, fog));
            }

            public void remove(UUID uuid) {
                vanillaEvents.remove(uuid);
            }

            public void updateProgress(UUID uuid, float progress) {
                vanillaEvents.get(uuid).setProgress(progress);
            }

            public void updateName(UUID uuid, Component displayName) {
                vanillaEvents.get(uuid).setName(displayName);
            }

            public void updateStyle(UUID uuid, PA_BossEvent.PA_BossBarColor color, PA_BossEvent.PA_BossBarOverlay overlay) {
                PA_LerpingBossEvent lerpingBossEvent = (PA_LerpingBossEvent) vanillaEvents.get(uuid);
                lerpingBossEvent.setCustomColor(color);
                lerpingBossEvent.setCustomOverlay(overlay);
            }

            public void updateProperties(UUID uuid, boolean darkenScreen, boolean fog) {
                PA_LerpingBossEvent lerpingBossEvent = (PA_LerpingBossEvent) vanillaEvents.get(uuid);
                lerpingBossEvent.setDarkenScreen(darkenScreen);
                lerpingBossEvent.setCreateWorldFog(fog);
            }
        });
    }
}
