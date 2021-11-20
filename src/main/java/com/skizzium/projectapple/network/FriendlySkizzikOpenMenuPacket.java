package com.skizzium.projectapple.network;

import com.skizzium.projectapple.entity.boss.skizzik.util.FriendlySkizzikInventoryHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class FriendlySkizzikOpenMenuPacket {
    public final int skizzikId;
    
    public FriendlySkizzikOpenMenuPacket(int skizzikId) {
        this.skizzikId = skizzikId;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.skizzikId);
    }

    public static FriendlySkizzikOpenMenuPacket decode(FriendlyByteBuf buffer) {
        return new FriendlySkizzikOpenMenuPacket(buffer.readInt());
    }

    public static void handle(FriendlySkizzikOpenMenuPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FriendlySkizzikInventoryHandler.openMenu(packet.skizzikId, context)));
        context.get().setPacketHandled(true);
    }
}
