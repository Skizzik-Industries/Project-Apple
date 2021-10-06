package com.skizzium.projectapple.network.bossevent;

import com.skizzium.projectapple.gui.PA_BossEvent;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class RemoveBossEventPacket {
    public final UUID id;

    public RemoveBossEventPacket(PA_BossEvent event) {
        this.id = event.getId();
    }

    public RemoveBossEventPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readUUID();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.id);
    }

    public static RemoveBossEventPacket decode(FriendlyByteBuf buffer) {
        return new RemoveBossEventPacket(buffer);
    }

    public static void handle(RemoveBossEventPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleRemoveBossEventPacket(packet)));
        context.get().setPacketHandled(true);
    }
}
