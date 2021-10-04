package com.skizzium.projectapple.network;

import com.skizzium.projectapple.gui.PA_BossEvent;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class UpdateProgressBossEventPacket {
    public final UUID id;
    public final float progress;

    public UpdateProgressBossEventPacket(PA_BossEvent event) {
        this.id = event.getId();
        this.progress = event.getProgress();
    }

    public UpdateProgressBossEventPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readUUID();
        this.progress = buffer.readFloat();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.id);
        buffer.writeFloat(this.progress);
    }

    public static UpdateProgressBossEventPacket decode(FriendlyByteBuf buffer) {
        return new UpdateProgressBossEventPacket(buffer);
    }

    public static void handle(UpdateProgressBossEventPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleUpdateProgressBossEventPacket(packet)));
        context.get().setPacketHandled(true);
    }
}
