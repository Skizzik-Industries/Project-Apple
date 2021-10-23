package com.skizzium.projectapple.init;

import net.minecraft.client.KeyMapping;
import org.apache.commons.lang3.ArrayUtils;

public class PA_Keybinds {
    public final KeyMapping keyDetachHead = new KeyMapping("key.skizzik.detach_head", 293, "key.categories.skizzik.friendly_skizzik");
    
    public void register() {
        PA_ClientHelper.getClient().options.keyMappings = ArrayUtils.addAll(PA_ClientHelper.getClient().options.keyMappings, this.keyDetachHead);
    }
}
