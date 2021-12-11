package com.skizzium.projectapple.network;

import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleRPC {
    public final boolean status;

    public ToggleRPC(boolean status) {
        this.status = status;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.status);
    }

    public static ToggleRPC decode(FriendlyByteBuf buffer) {
        return new ToggleRPC(buffer.readBoolean());
    }

    public static void handle(ToggleRPC packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleToggleRPC(packet)));
        context.get().setPacketHandled(true);
    }
}
