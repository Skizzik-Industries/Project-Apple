package com.skizzium.projectapple.network;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.gui.PA_BossEvent;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class UpdatePropertiesBossEventPacket {
    public final UUID id;
    public final boolean darkenScreen;
    public final boolean createWorldFog;

    public UpdatePropertiesBossEventPacket(PA_BossEvent event) {
        this.id = event.getId();
        this.darkenScreen = event.shouldDarkenScreen();
        this.createWorldFog = event.shouldCreateWorldFog();
    }

    public UpdatePropertiesBossEventPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readUUID();
        int i = buffer.readUnsignedByte();
        this.darkenScreen = (i & 1) > 0;
        this.createWorldFog = (i & 1) > 0;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.id);
        buffer.writeByte(ProjectApple.encodeBossEventProperties(this.darkenScreen, this.createWorldFog));
    }

    public static UpdatePropertiesBossEventPacket decode(FriendlyByteBuf buffer) {
        return new UpdatePropertiesBossEventPacket(buffer);
    }

    public static void handle(UpdatePropertiesBossEventPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleUpdatePropertiesBossEventPacket(packet)));
        context.get().setPacketHandled(true);
    }
}
