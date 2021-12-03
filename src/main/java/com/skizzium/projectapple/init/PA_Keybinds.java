package com.skizzium.projectapple.init;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class PA_Keybinds {
    public final KeyMapping keyDetachHead = new KeyMapping("key.skizzik.detach_head", 293, "key.categories.skizzik.friendly_skizzik");
    public final KeyMapping keySpawnSkizzie = new KeyMapping("key.skizzik.spawn_skizzie", 82, "key.categories.skizzik.friendly_skizzik");
    
    public void register() {
        net.minecraftforge.client.ClientRegistry.registerKeyBinding(keyDetachHead);
        ClientRegistry.registerKeyBinding(keySpawnSkizzie);
    }
}
