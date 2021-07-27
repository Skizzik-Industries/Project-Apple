package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;

public class PA_SoundEvents {
    public static final Lazy<SoundEvent> EQUIP_FLESH_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "item.armor.equip_skizzik")));
    public static final Lazy<SoundEvent> MUSIC_SKIZZIK_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "music.skizzik")));
    public static final Lazy<SoundEvent> FINISH_HIM_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "entity.skizzik.finish_him")));

    public static final RegistryObject<SoundEvent> EQUIP_FLESH = PA_Registry.SOUND_EVENTS.register("item.armor.equip_skizzik", EQUIP_FLESH_LAZY);
    public static final RegistryObject<SoundEvent> MUSIC_SKIZZIK = PA_Registry.SOUND_EVENTS.register("music.skizzik", MUSIC_SKIZZIK_LAZY);
    public static final RegistryObject<SoundEvent> FINISH_HIM = PA_Registry.SOUND_EVENTS.register("entity.skizzik.finish_him", FINISH_HIM_LAZY);

    public static void register() {}
}