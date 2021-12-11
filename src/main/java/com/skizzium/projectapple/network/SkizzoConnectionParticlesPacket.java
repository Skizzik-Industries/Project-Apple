package com.skizzium.projectapple.network;

import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SkizzoConnectionParticlesPacket {
    public final int skizzikId;
    public final int skizzoId;

    public SkizzoConnectionParticlesPacket(int skizzikId, int skizzoId) {
        this.skizzikId = skizzikId;
        this.skizzoId = skizzoId;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.skizzikId);
        buffer.writeInt(this.skizzoId);
    }

    public static SkizzoConnectionParticlesPacket decode(FriendlyByteBuf buffer) {
        return new SkizzoConnectionParticlesPacket(buffer.readInt(), buffer.readInt());
    }

    public static void handle(SkizzoConnectionParticlesPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleSkizzoParticles(packet)));
        context.get().setPacketHandled(true);
    }
}
