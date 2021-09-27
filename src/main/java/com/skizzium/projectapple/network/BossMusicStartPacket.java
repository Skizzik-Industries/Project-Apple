package com.skizzium.projectapple.network;

import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import com.skizzium.projectapple.sound.BossMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class BossMusicStartPacket {
    public final SoundEvent musicEvent;
    public static boolean isPlaying = false;
    public static SoundInstance music;

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
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleBossMusicStartPacket(packet, context)));
        context.get().setPacketHandled(true);
    }
}
