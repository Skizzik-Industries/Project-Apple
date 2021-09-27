package com.skizzium.projectapple.network;

import com.google.common.collect.Maps;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import com.skizzium.projectapple.util.PA_LerpingBossEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class PA_BossEventPacket implements Packet<ClientGamePacketListener> {
    //private final UUID id;
    //private final PA_BossEventOperations.Operation operation;
    //private static final Map<UUID, PA_LerpingBossEvent> events = Maps.newLinkedHashMap();

    public PA_BossEventPacket(UUID uuid, PA_BossEventOperations.Operation performOperation) {
        //this.id = uuid;
        //this.operation = performOperation;
    }

    public PA_BossEventPacket(FriendlyByteBuf buffer) {
        //this.id = buffer.readUUID();
        //PA_BossEventOperations.OperationType opearationType = buffer.readEnum(PA_BossEventOperations.OperationType.class);
        //this.operation = opearationType.reader.apply(buffer);
    }

//    public static Map<UUID, PA_LerpingBossEvent> getEvents() {
//        return events;
//    }

    public void write(FriendlyByteBuf buffer) {
        //buffer.writeUUID(this.id);
        //buffer.writeEnum(this.operation.getType());
        //this.operation.write(buffer);
    }

    public static PA_BossEventPacket decode(FriendlyByteBuf buffer) {
        return new PA_BossEventPacket(buffer);
    }

    public void handle(ClientGamePacketListener listener) {
        //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleBossEventPacket(this));
    }

    public static void handle(PA_BossEventPacket packet, Supplier<NetworkEvent.Context> context) {
//        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleBossEventPacket(packet)));
//        context.get().setPacketHandled(true);
    }

//    public void dispatch(PA_BossEventOperations.Handler handler) {
//        this.operation.dispatch(this.id, handler);
//    }

}