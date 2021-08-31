package com.skizzium.projectapple.network;

import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.sound.BossMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class BossMusicStartPacket {
    private final SoundEvent musicEvent;
    public static boolean isPlaying = false;

    public BossMusicStartPacket(SoundEvent musicToPlay) {
        this.musicEvent = musicToPlay;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(PA_SoundEvents.getID(this.musicEvent));
    }

    public static BossMusicStartPacket decode(FriendlyByteBuf buffer) {
        return new BossMusicStartPacket(PA_SoundEvents.getMusic(buffer.readInt()));
    }

    public static void handle(BossMusicStartPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> packet.handlePacket(packet, context)));
        context.get().setPacketHandled(true);
    }

    public void handlePacket(BossMusicStartPacket packet, Supplier<NetworkEvent.Context> context) {
        /*final MusicManager musicManager = Minecraft.getInstance().getMusicManager();
        if (!musicManager.isPlayingMusic(music)) {
            musicManager.startPlaying(music);
        }*/

        Minecraft.getInstance().getMusicManager().stopPlaying();
        if (!isPlaying) {
            Minecraft.getInstance().getSoundManager().play(new BossMusic(musicEvent, Minecraft.getInstance()));
            isPlaying = true;
        }
    }
}
