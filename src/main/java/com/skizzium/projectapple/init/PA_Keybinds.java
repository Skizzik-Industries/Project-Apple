package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Keybinds {
    public static final KeyMapping keyDetachHead = new KeyMapping("key.skizzik.detach_head", 293, "key.categories.skizzik.friendly_skizzik");
    public static final KeyMapping keySpawnSkizzie = new KeyMapping("key.skizzik.spawn_skizzie", 82, "key.categories.skizzik.friendly_skizzik");
    
    @SubscribeEvent
    public static void registerKeybinds(FMLClientSetupEvent event) {
        net.minecraftforge.client.ClientRegistry.registerKeyBinding(keyDetachHead);
        ClientRegistry.registerKeyBinding(keySpawnSkizzie);
    }
}
