package com.skizzium.projectapple.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class BossMusicStopPacket {
    public void encode(FriendlyByteBuf buffer) {}

    public static BossMusicStopPacket decode(FriendlyByteBuf buffer) {
        return new BossMusicStopPacket();
    }

    public static void handle(BossMusicStopPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> packet.handlePacket(packet, context)));
        context.get().setPacketHandled(true);
    }

    public void handlePacket(BossMusicStopPacket packet, Supplier<NetworkEvent.Context> context) {
        //Minecraft.getInstance().getMusicManager().stopPlaying();

        Minecraft.getInstance().getSoundManager().stop();
        BossMusicStartPacket.isPlaying = false;

        Music music = Minecraft.getInstance().getSituationalMusic();
        MusicManager musicManager = Minecraft.getInstance().getMusicManager();
        musicManager.nextSongDelay = Math.min(100, Mth.nextInt(new Random(), music.getMinDelay(), music.getMaxDelay()));
    }
}
