package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.gui.HeadStatus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_GUI {
    public static final IIngameOverlay HEAD_STATUS = new HeadStatus();
    
    @SubscribeEvent
    public static void addOverlays(FMLClientSetupEvent event) {
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.POTION_ICONS_ELEMENT, "Friendly Skizzik - Head Status", HEAD_STATUS);
    }
}
