package com.skizzium.projectapple.network;

import com.skizzium.projectapple.entity.boss.friendlyskizzik.util.FriendlySkizzikControlsHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FriendlySkizzikHeadAttachmentPacket {
    public final int headId;
    public final int skizzikId;

    public FriendlySkizzikHeadAttachmentPacket(int headId, int skizzikId) {
        this.headId = headId;
        this.skizzikId = skizzikId;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.headId);
        buffer.writeInt(this.skizzikId);
    }

    public static FriendlySkizzikHeadAttachmentPacket decode(FriendlyByteBuf buffer) {
        return new FriendlySkizzikHeadAttachmentPacket(buffer.readInt(), buffer.readInt());
    }

    public static void handle(FriendlySkizzikHeadAttachmentPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FriendlySkizzikControlsHandler.headAttachment(packet, context)));
        context.get().setPacketHandled(true);
    }
}
