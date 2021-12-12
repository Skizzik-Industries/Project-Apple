package com.skizzium.projectapple.network;

import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RPCPacket {
    public final RPCAction action;
    public final int bossId;
    
    public RPCPacket(RPCAction action, int bossId) {
        this.action = action;
        this.bossId = bossId;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(this.action);
        buffer.writeInt(this.bossId);
    }

    public static RPCPacket decode(FriendlyByteBuf buffer) {
        return new RPCPacket(buffer.readEnum(RPCAction.class), buffer.readInt());
    }

    public static void handle(RPCPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleRPCPacket(packet)));
        context.get().setPacketHandled(true);
    }
    
    public enum RPCAction {
        CONNECT,
        DISCONNECT,
        INITIALIZE,
        RELOAD
    }
}
