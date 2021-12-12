package com.skizzium.projectapple.network;

import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SaveMaxPlayers {
    public final int maxPlayers;
    
    public SaveMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.maxPlayers);
    }

    public static SaveMaxPlayers decode(FriendlyByteBuf buffer) {
        return new SaveMaxPlayers(buffer.readInt());
    }

    public static void handle(SaveMaxPlayers packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.saveMaxPlayersPacket(packet)));
        context.get().setPacketHandled(true);
    }
}
