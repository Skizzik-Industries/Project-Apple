package com.skizzium.projectapple.network;

import com.skizzium.projectapple.gui.PA_BossEvent;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class UpdateStyleBossEventPacket {
    public final UUID id;
    public final PA_BossEvent.PA_BossBarColor color;
    public final PA_BossEvent.PA_BossBarOverlay overlay;

    public UpdateStyleBossEventPacket(PA_BossEvent event) {
        this.id = event.getId();
        this.color = event.getColor();
        this.overlay = event.getOverlay();
    }

    public UpdateStyleBossEventPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readUUID();
        this.color = buffer.readEnum(PA_BossEvent.PA_BossBarColor.class);
        this.overlay = buffer.readEnum(PA_BossEvent.PA_BossBarOverlay.class);
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.id);
        buffer.writeEnum(this.color);
        buffer.writeEnum(this.overlay);
    }

    public static UpdateStyleBossEventPacket decode(FriendlyByteBuf buffer) {
        return new UpdateStyleBossEventPacket(buffer);
    }

    public static void handle(UpdateStyleBossEventPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleUpdateStyleBossEventPacket(packet)));
        context.get().setPacketHandled(true);
    }
}
