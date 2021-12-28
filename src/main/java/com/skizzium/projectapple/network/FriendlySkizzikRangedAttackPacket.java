package com.skizzium.projectapple.network;

import com.skizzium.projectapple.entity.boss.friendlyskizzik.util.FriendlySkizzikControlsHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FriendlySkizzikRangedAttackPacket {
    public final int skizzikId;

    public FriendlySkizzikRangedAttackPacket(int skizzikId) {
        this.skizzikId = skizzikId;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.skizzikId);
    }

    public static FriendlySkizzikRangedAttackPacket decode(FriendlyByteBuf buffer) {
        return new FriendlySkizzikRangedAttackPacket(buffer.readInt());
    }

    public static void handle(FriendlySkizzikRangedAttackPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FriendlySkizzikControlsHandler.rangedAttack(packet.skizzikId, context)));
        context.get().setPacketHandled(true);
    }
}
