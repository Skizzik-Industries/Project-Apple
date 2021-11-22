package com.skizzium.projectapple.entity.boss.friendlyskizzik.util;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import com.skizzium.projectapple.gui.FriendlySkizzikMenu;
import com.skizzium.projectapple.gui.FriendlySkizzikScreen;
import com.skizzium.projectapple.init.PA_PacketRegistry;
import com.skizzium.projectapple.network.FriendlySkizzikOpenMenuPacket;
import com.skizzium.projectapple.network.FriendlySkizzikOpenScreenPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FriendlySkizzikInventoryHandler {
    public static void openMenu(int skizzikId, Supplier<NetworkEvent.Context> context) {
        if (context.get().getSender() != null) {
            Level level = context.get().getSender().level;
            if (level instanceof ServerLevel && level.getEntity(skizzikId) instanceof FriendlySkizzik skizzik) {
                skizzik.openInventory(context.get().getSender());
            }
        }
    }

    public static void openScreen(FriendlySkizzikOpenScreenPacket packet) {
        if (Minecraft.getInstance().player.getVehicle() != null && Minecraft.getInstance().player.getVehicle() instanceof FriendlySkizzik skizzik) {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;
            SimpleContainer container = new SimpleContainer(packet.size);
            FriendlySkizzikMenu menu = new FriendlySkizzikMenu(packet.containerId, player.getInventory(), container, skizzik);

            player.containerMenu = menu;
            minecraft.setScreen(new FriendlySkizzikScreen(menu, player.getInventory(), skizzik));
        }
    }
    
    @SubscribeEvent
    public static void changeInventory(GuiOpenEvent event) {
        if (event.getGui() instanceof InventoryScreen && Minecraft.getInstance().player.getVehicle() != null && Minecraft.getInstance().player.getVehicle() instanceof FriendlySkizzik skizzik) {
            event.setCanceled(true);
            Player player = Minecraft.getInstance().player;
            PA_PacketRegistry.INSTANCE.sendToServer(new FriendlySkizzikOpenMenuPacket(player.getVehicle().getId()));
        }
    }
}
