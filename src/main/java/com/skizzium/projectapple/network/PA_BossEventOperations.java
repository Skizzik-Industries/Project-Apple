package com.skizzium.projectapple.network;

import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;

import java.util.UUID;
import java.util.function.Function;

public class PA_BossEventOperations {
    static final Operation REMOVE_OPERATION = new Operation() {
        public OperationType getType() {
            return OperationType.REMOVE;
        }

        public void dispatch(UUID uuid, Handler handler) {
            handler.remove(uuid);
        }

        public void write(FriendlyByteBuf buffer) {
        }
    };

    public static PA_BossEventPacket createAddPacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new AddOperation(event));
    }

    public static PA_BossEventPacket createRemovePacket(UUID uuid) {
        return new PA_BossEventPacket(uuid, REMOVE_OPERATION);
    }

    public static PA_BossEventPacket createUpdateProgressPacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new UpdateProgressOperation(event.getProgress()));
    }

    public static PA_BossEventPacket createUpdateNamePacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new UpdateNameOperation(event.getName()));
    }

    public static PA_BossEventPacket createUpdateStylePacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new UpdateStyleOperation(event.getColor(), event.getOverlay()));
    }

    public static PA_BossEventPacket createUpdatePropertiesPacket(PA_BossEvent event) {
        return new PA_BossEventPacket(event.getId(), new UpdatePropertiesOperation(event.shouldDarkenScreen(), event.shouldCreateWorldFog()));
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

    static enum OperationType {
        ADD(AddOperation::new),
        REMOVE((p_178719_) -> PA_BossEventOperations.REMOVE_OPERATION),
        UPDATE_PROGRESS(PA_BossEventOperations.UpdateProgressOperation::new),
        UPDATE_NAME(PA_BossEventOperations.UpdateNameOperation::new),
        UPDATE_STYLE(PA_BossEventOperations.UpdateStyleOperation::new),
        UPDATE_PROPERTIES(PA_BossEventOperations.UpdatePropertiesOperation::new);

        final Function<FriendlyByteBuf, PA_BossEventOperations.Operation> reader;

        private OperationType(Function<FriendlyByteBuf, PA_BossEventOperations.Operation> reader) {
            this.reader = reader;
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
        PA_BossEventOperations.OperationType getType();

        void dispatch(UUID uuid, PA_BossEventOperations.Handler handler);

        void write(FriendlyByteBuf buffer);
    }

    static class AddOperation implements PA_BossEventOperations.Operation {
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

        public PA_BossEventOperations.OperationType getType() {
            return PA_BossEventOperations.OperationType.ADD;
        }

        public void dispatch(UUID uuid, PA_BossEventOperations.Handler handler) {
            handler.add(uuid, this.name, this.progress, this.color, this.overlay, this.darkenScreen, this.createWorldFog);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeComponent(this.name);
            buffer.writeFloat(this.progress);
            buffer.writeEnum(this.color);
            buffer.writeEnum(this.overlay);
            buffer.writeByte(encodeProperties(this.darkenScreen, this.createWorldFog));
        }
    }

    static class UpdateNameOperation implements PA_BossEventOperations.Operation {
        private final Component name;

        UpdateNameOperation(Component newName) {
            this.name = newName;
        }

        private UpdateNameOperation(FriendlyByteBuf buffer) {
            this.name = buffer.readComponent();
        }

        public PA_BossEventOperations.OperationType getType() {
            return OperationType.UPDATE_NAME;
        }

        public void dispatch(UUID uuid, PA_BossEventOperations.Handler handler) {
            handler.updateName(uuid, this.name);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeComponent(this.name);
        }
    }

    static class UpdateProgressOperation implements PA_BossEventOperations.Operation {
        private final float progress;

        UpdateProgressOperation(float newProgress) {
            this.progress = newProgress;
        }

        private UpdateProgressOperation(FriendlyByteBuf buffer) {
            this.progress = buffer.readFloat();
        }

        public PA_BossEventOperations.OperationType getType() {
            return OperationType.UPDATE_PROGRESS;
        }

        public void dispatch(UUID uuid, PA_BossEventOperations.Handler handler) {
            handler.updateProgress(uuid, this.progress);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeFloat(this.progress);
        }
    }

    static class UpdatePropertiesOperation implements PA_BossEventOperations.Operation {
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

        public PA_BossEventOperations.OperationType getType() {
            return OperationType.UPDATE_PROPERTIES;
        }

        public void dispatch(UUID uuid, PA_BossEventOperations.Handler handler) {
            handler.updateProperties(uuid, this.darkenScreen, this.createWorldFog);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeByte(encodeProperties(this.darkenScreen, this.createWorldFog));
        }
    }

    static class UpdateStyleOperation implements PA_BossEventOperations.Operation {
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

        public PA_BossEventOperations.OperationType getType() {
            return OperationType.UPDATE_STYLE;
        }

        public void dispatch(UUID uuid, PA_BossEventOperations.Handler handler) {
            handler.updateStyle(uuid, this.color, this.overlay);
        }

        public void write(FriendlyByteBuf buffer) {
            buffer.writeEnum(this.color);
            buffer.writeEnum(this.overlay);
        }
    }
}
