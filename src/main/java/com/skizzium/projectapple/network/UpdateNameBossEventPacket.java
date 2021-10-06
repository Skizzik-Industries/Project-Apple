package com.skizzium.projectapple.network;

import com.skizzium.projectapple.gui.PA_BossEvent;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class UpdateNameBossEventPacket {
    public final UUID id;
    public final Component name;

    public UpdateNameBossEventPacket(PA_BossEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    public UpdateNameBossEventPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readUUID();
        this.name = buffer.readComponent();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.id);
        buffer.writeComponent(this.name);
    }

    public static UpdateNameBossEventPacket decode(FriendlyByteBuf buffer) {
        return new UpdateNameBossEventPacket(buffer);
    }

    public static void handle(UpdateNameBossEventPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleUpdateNameBossEventPacket(packet)));
        context.get().setPacketHandled(true);
    }
}
