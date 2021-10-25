package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.gui.HeadStatusOverlay;
import com.skizzium.projectapple.gui.SkizzieSpawningOverlay;
import net.minecraft.client.gui.GuiComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_GUI {
    public static final IIngameOverlay HEAD_STATUS = registerOverlay(ForgeIngameGui.POTION_ICONS_ELEMENT, "Friendly Skizzik - Head Status", new HeadStatusOverlay());
    public static final IIngameOverlay SKIZZIE_SPAWNING = registerOverlay(ForgeIngameGui.POTION_ICONS_ELEMENT, "Friendly Skizzik - Skizzie Spawning", new SkizzieSpawningOverlay());
    
    private static IIngameOverlay registerOverlay(IIngameOverlay overlay, String displayName, IIngameOverlay instance) {
        OverlayRegistry.registerOverlayAbove(overlay, displayName, instance);
        return instance;
    }
}
