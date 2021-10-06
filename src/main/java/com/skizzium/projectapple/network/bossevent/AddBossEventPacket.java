package com.skizzium.projectapple.network.bossevent;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.gui.PA_BossEvent;
import com.skizzium.projectapple.init.network.PA_PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class AddBossEventPacket {
    public final UUID id;
    public final Component name;
    public final float progress;
    public final PA_BossEvent.PA_BossBarColor color;
    public final PA_BossEvent.PA_BossBarOverlay overlay;
    public final ResourceLocation customTexture;
    public final boolean darkenScreen;
    public final boolean createWorldFog;

    public AddBossEventPacket(PA_BossEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.progress = event.getProgress();
        this.color = event.getColor();
        this.overlay = event.getOverlay();
        this.customTexture = event.getCustomTexture();
        this.darkenScreen = event.shouldDarkenScreen();
        this.createWorldFog = event.shouldCreateWorldFog();
    }

    public AddBossEventPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readUUID();
        this.name = buffer.readComponent();
        this.progress = buffer.readFloat();
        this.color = buffer.readEnum(PA_BossEvent.PA_BossBarColor.class);
        this.overlay = buffer.readEnum(PA_BossEvent.PA_BossBarOverlay.class);
        this.customTexture = buffer.readResourceLocation();
        int i = buffer.readUnsignedByte();
        this.darkenScreen = (i & 1) > 0;
        this.createWorldFog = (i & 2) > 0;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.id);
        buffer.writeComponent(this.name);
        buffer.writeFloat(this.progress);
        buffer.writeEnum(this.color);
        buffer.writeEnum(this.overlay);
        buffer.writeResourceLocation(this.customTexture);
        buffer.writeByte(ProjectApple.encodeBossEventProperties(this.darkenScreen, this.createWorldFog));
    }

    public static AddBossEventPacket decode(FriendlyByteBuf buffer) {
        return new AddBossEventPacket(buffer);
    }

    public static void handle(AddBossEventPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PA_PacketHandler.handleAddBossEventPacket(packet)));
        context.get().setPacketHandled(true);
    }
}
