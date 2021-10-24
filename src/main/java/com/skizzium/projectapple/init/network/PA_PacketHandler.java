package com.skizzium.projectapple.init.network;

import com.skizzium.projectapple.network.BossMusicStartPacket;
import com.skizzium.projectapple.network.BossMusicStopPacket;
import com.skizzium.projectapple.sound.BossMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.Random;
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
}
