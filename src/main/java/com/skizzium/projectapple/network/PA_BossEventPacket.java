package com.skizzium.projectapple.network;

import com.google.common.collect.Maps;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import com.skizzium.projectapple.util.PA_BossEvent;
import com.skizzium.projectapple.util.PA_LerpingBossEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class PA_BossEventPacket implements Packet<ClientGamePacketListener> {
    private final UUID id;
    private final PA_BossEventPacket.Operation operation;
    private static final Map<UUID, PA_LerpingBossEvent> events = Maps.newLinkedHashMap();
    static final PA_BossEventPacket.Operation REMOVE_OPERATION = new PA_BossEventPacket.Operation() {
        public PA_BossEventPacket.OperationType getType() {
            return PA_BossEventPacket.OperationType.REMOVE;
        }

        public void dispatch(UUID uuid, PA_BossEventPacket.Handler handler) {
            handler.remove(uuid);
        }

        public void write(FriendlyByteBuf buffer) {
        }
    };

    private PA_BossEventPacket(UUID uuid, PA_BossEventPacket.Operation performOperation) {
        this.id = uuid;
        this.operation = performOperation;
    }

    public PA_BossEventPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readUUID();
        PA_BossEventPacket.OperationType opearationType = buffer.readEnum(PA_BossEventPacket.OperationType.class);
        this.operation = opearationType.reader.apply(buffer);
    }

    public static Map<UUID, PA_LerpingBossEvent> getEvents() {
        return events;
    }

    public static PA_BossEventPacket createAddPacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new PA_BossEventPacket.AddOperation(event));
    }

    public static PA_BossEventPacket createRemovePacket(UUID uuid) {
        return new PA_BossEventPacket(uuid, REMOVE_OPERATION);
    }

    public static PA_BossEventPacket createUpdateProgressPacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new PA_BossEventPacket.UpdateProgressOperation(event.getProgress()));
    }

    public static PA_BossEventPacket createUpdateNamePacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new PA_BossEventPacket.UpdateNameOperation(event.getName()));
    }

    public static PA_BossEventPacket createUpdateStylePacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new PA_BossEventPacket.UpdateStyleOperation(event.getColor(), event.getOverlay()));
    }

    public static PA_BossEventPacket createUpdatePropertiesPacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new PA_BossEventPacket.UpdatePropertiesOperation(event.shouldDarkenScreen(), event.shouldCreateWorldFog()));
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.id);
        buffer.writeEnum(this.operation.getType());
        this.operation.write(buffer);
    }

    static int encodeProperties(boolean darkenFlag, boolean fogFlag) {
        int i = 0;
        if (darkenFlag) {
            i |= 1;
        }

        if (fogFlag) {
            i |= 2;
        }

        return i;
    }

    public static PA_BossEventPacket decode(FriendlyByteBuf buffer) {
        return new PA_BossEventPacket(buffer);
    }

    public void handle(ClientGamePacketListener listener) {
        PA_PacketHandler.handleBossEventPacket(this);
    }

    public static void handle(PA_BossEventPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleBossEventPacket(packet)));
        context.get().setPacketHandled(true);
    }

    public void dispatch(PA_BossEventPacket.Handler handler) {
        this.operation.dispatch(this.id, handler);
    }

    static class AddOperation implements PA_BossEventPacket.Operation {
        private final Component name;
        private final float progress;
        private final PA_BossEvent.PA_BossBarColor color;
        private final PA_BossEvent.PA_BossBarOverlay overlay;
        private final boolean darkenScreen;
        private final boolean createWorldFog;

        AddOperation(PA_BossEvent event) {
            this.name = event.getName();
            this.progress = event.getProgress();
            this.color = event.getColor();
            this.overlay = event.getOverlay();
            this.darkenScreen = event.shouldDarkenScreen();
            this.createWorldFog = event.shouldCreateWorldFog();
        }

        private AddOperation(FriendlyByteBuf buffer) {
            this.name = buffer.readComponent();
            this.progress = buffer.readFloat();
            this.color = buffer.readEnum(PA_BossEvent.PA_BossBarColor.class);
            this.overlay = buffer.readEnum(PA_BossEvent.PA_BossBarOverlay.class);
            int i = buffer.readUnsignedByte();
            this.darkenScreen = (i & 1) > 0;
            this.createWorldFog = (i & 4) > 0;
        }

        public PA_BossEventPacket.OperationType getType() {
            return PA_BossEventPacket.OperationType.ADD;
        }

        public void dispatch(UUID uuid, PA_BossEventPacket.Handler handler) {
            handler.add(uuid, this.name, this.progress, this.color, this.overlay, this.darkenScreen, this.createWorldFog);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeComponent(this.name);
            buffer.writeFloat(this.progress);
            buffer.writeEnum(this.color);
            buffer.writeEnum(this.overlay);
            buffer.writeByte(PA_BossEventPacket.encodeProperties(this.darkenScreen, this.createWorldFog));
        }
    }

    public interface Handler {
        default void add(UUID uuid, Component name, float progress, PA_BossEvent.PA_BossBarColor color, PA_BossEvent.PA_BossBarOverlay overlay, boolean darkenScreen, boolean fog) {
        }

        default void remove(UUID uuid) {
        }

        default void updateProgress(UUID uuid, float progress) {
        }

        default void updateName(UUID uuid, Component name) {
        }

        default void updateStyle(UUID uuid, PA_BossEvent.PA_BossBarColor color, PA_BossEvent.PA_BossBarOverlay overlay) {
        }

        default void updateProperties(UUID uuid, boolean darkenScreen, boolean fog) {
        }
    }

    interface Operation {
        PA_BossEventPacket.OperationType getType();

        void dispatch(UUID uuid, PA_BossEventPacket.Handler handler);

        void write(FriendlyByteBuf buffer);
    }

    static enum OperationType {
        ADD(PA_BossEventPacket.AddOperation::new),
        REMOVE((p_178719_) -> PA_BossEventPacket.REMOVE_OPERATION),
        UPDATE_PROGRESS(PA_BossEventPacket.UpdateProgressOperation::new),
        UPDATE_NAME(PA_BossEventPacket.UpdateNameOperation::new),
        UPDATE_STYLE(PA_BossEventPacket.UpdateStyleOperation::new),
        UPDATE_PROPERTIES(PA_BossEventPacket.UpdatePropertiesOperation::new);

        final Function<FriendlyByteBuf, PA_BossEventPacket.Operation> reader;

        private OperationType(Function<FriendlyByteBuf, PA_BossEventPacket.Operation> reader) {
            this.reader = reader;
        }
    }

    static class UpdateNameOperation implements PA_BossEventPacket.Operation {
        private final Component name;

        UpdateNameOperation(Component newName) {
            this.name = newName;
        }

        private UpdateNameOperation(FriendlyByteBuf buffer) {
            this.name = buffer.readComponent();
        }

        public PA_BossEventPacket.OperationType getType() {
            return PA_BossEventPacket.OperationType.UPDATE_NAME;
        }

        public void dispatch(UUID uuid, PA_BossEventPacket.Handler handler) {
            handler.updateName(uuid, this.name);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeComponent(this.name);
        }
    }

    static class UpdateProgressOperation implements PA_BossEventPacket.Operation {
        private final float progress;

        UpdateProgressOperation(float newProgress) {
            this.progress = newProgress;
        }

        private UpdateProgressOperation(FriendlyByteBuf buffer) {
            this.progress = buffer.readFloat();
        }

        public PA_BossEventPacket.OperationType getType() {
            return PA_BossEventPacket.OperationType.UPDATE_PROGRESS;
        }

        public void dispatch(UUID uuid, PA_BossEventPacket.Handler handler) {
            handler.updateProgress(uuid, this.progress);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeFloat(this.progress);
        }
    }

    static class UpdatePropertiesOperation implements PA_BossEventPacket.Operation {
        private final boolean darkenScreen;
        private final boolean createWorldFog;

        UpdatePropertiesOperation(boolean darkenScreen, boolean fog) {
            this.darkenScreen = darkenScreen;
            this.createWorldFog = fog;
        }

        private UpdatePropertiesOperation(FriendlyByteBuf buffer) {
            int i = buffer.readUnsignedByte();
            this.darkenScreen = (i & 1) > 0;
            this.createWorldFog = (i & 2) > 0;
        }

        public PA_BossEventPacket.OperationType getType() {
            return PA_BossEventPacket.OperationType.UPDATE_PROPERTIES;
        }

        public void dispatch(UUID uuid, PA_BossEventPacket.Handler handler) {
            handler.updateProperties(uuid, this.darkenScreen, this.createWorldFog);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeByte(PA_BossEventPacket.encodeProperties(this.darkenScreen, this.createWorldFog));
        }
    }

    static class UpdateStyleOperation implements PA_BossEventPacket.Operation {
        private final PA_BossEvent.PA_BossBarColor color;
        private final PA_BossEvent.PA_BossBarOverlay overlay;

        UpdateStyleOperation(PA_BossEvent.PA_BossBarColor newColor, PA_BossEvent.PA_BossBarOverlay newOverlay) {
            this.color = newColor;
            this.overlay = newOverlay;
        }

        private UpdateStyleOperation(FriendlyByteBuf buffer) {
            this.color = buffer.readEnum(PA_BossEvent.PA_BossBarColor.class);
            this.overlay = buffer.readEnum(PA_BossEvent.PA_BossBarOverlay.class);
        }

        public PA_BossEventPacket.OperationType getType() {
            return PA_BossEventPacket.OperationType.UPDATE_STYLE;
        }

        public void dispatch(UUID uuid, PA_BossEventPacket.Handler handler) {
            handler.updateStyle(uuid, this.color, this.overlay);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeEnum(this.color);
            buffer.writeEnum(this.overlay);
        }
    }
}