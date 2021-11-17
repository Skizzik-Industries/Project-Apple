package com.skizzium.projectapple.network;

import com.skizzium.projectapple.entity.boss.skizzik.util.FriendlySkizzikInventoryHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class FriendlySkizzikOpenScreenPacket {
    public final int skizzikId;
    public final int containerId;
    public final int size;

    public FriendlySkizzikOpenScreenPacket(int skizzikId, int containerId, int size) {
        this.skizzikId = skizzikId;
        this.containerId = containerId;
        this.size = size;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.skizzikId);
        buffer.writeInt(this.containerId);
        buffer.writeInt(this.size);
    }

    public static FriendlySkizzikOpenScreenPacket decode(FriendlyByteBuf buffer) {
        return new FriendlySkizzikOpenScreenPacket(buffer.readInt(), buffer.readInt(), buffer.readInt());
    }

    public static void handle(FriendlySkizzikOpenScreenPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FriendlySkizzikInventoryHandler.openScreen(packet)));
        context.get().setPacketHandled(true);
    }
}
