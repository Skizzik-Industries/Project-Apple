package com.skizzium.projectapple.network;

import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class BossMusicStopPacket {
    public void encode(FriendlyByteBuf buffer) {}

    public static BossMusicStopPacket decode(FriendlyByteBuf buffer) {
        return new BossMusicStopPacket();
    }

    public static void handle(BossMusicStopPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleBossMusicStopPacket(packet, context)));
        context.get().setPacketHandled(true);
    }
}
