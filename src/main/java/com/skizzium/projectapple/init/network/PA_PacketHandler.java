package com.skizzium.projectapple.init.network;

import com.skizzium.projectapple.gui.PA_LerpingBossEvent;
import com.skizzium.projectapple.network.*;
import com.skizzium.projectapple.network.bossevent.*;
import com.skizzium.projectapple.sound.BossMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.sounds.MusicManager;
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

    public static void handleAddBossEventPacket(AddBossEventPacket packet) {
        Map<UUID, LerpingBossEvent> vanillaEvents = Minecraft.getInstance().gui.getBossOverlay().events;
        vanillaEvents.put(packet.id, new PA_LerpingBossEvent(packet.id, packet.name, packet.progress, packet.color, packet.overlay, packet.customTexture, packet.darkenScreen, packet.createWorldFog));
    }

    public static void handleRemoveBossEventPacket(RemoveBossEventPacket packet) {
        Map<UUID, LerpingBossEvent> vanillaEvents = Minecraft.getInstance().gui.getBossOverlay().events;
        vanillaEvents.remove(packet.id);
    }

    public static void handleUpdateBossEventPacket(UpdateBossEventPacket packet) {
        Map<UUID, LerpingBossEvent> vanillaEvents = Minecraft.getInstance().gui.getBossOverlay().events;
        PA_LerpingBossEvent lerpingBossEvent = (PA_LerpingBossEvent)vanillaEvents.get(packet.id);

        vanillaEvents.get(packet.id).setName(packet.name);
        vanillaEvents.get(packet.id).setProgress(packet.progress);
        
        lerpingBossEvent.setCustomColor(packet.color);
        lerpingBossEvent.setCustomOverlay(packet.overlay);
        lerpingBossEvent.setCustomTexture(packet.customTexture);

        lerpingBossEvent.setDarkenScreen(packet.darkenScreen);
        lerpingBossEvent.setCreateWorldFog(packet.createWorldFog);
    }
}
