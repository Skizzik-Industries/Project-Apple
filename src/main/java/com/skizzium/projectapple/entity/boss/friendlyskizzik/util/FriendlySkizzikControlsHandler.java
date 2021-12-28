package com.skizzium.projectapple.entity.boss.friendlyskizzik.util;

import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import com.skizzium.projectapple.network.FriendlySkizzikHeadAttachmentPacket;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FriendlySkizzikControlsHandler {
    public static void rangedAttack(int skizzikId, Supplier<NetworkEvent.Context> context) {
        if (context.get().getSender() != null) {
            Level level = context.get().getSender().level;
            if (level.getEntity(skizzikId) instanceof FriendlySkizzik skizzik) {
                skizzik.performRangedAttack(context.get().getSender());
            }
        }
    }

    public static void headAttachment(FriendlySkizzikHeadAttachmentPacket packet, Supplier<NetworkEvent.Context> context) {
        if (context.get().getSender() != null) {
            Level level = context.get().getSender().level;
            if (level.getEntity(packet.skizzikId) instanceof FriendlySkizzik skizzik) {
                skizzik.changeHeadAttachment(packet.headId);
            }
        }
    }
}
