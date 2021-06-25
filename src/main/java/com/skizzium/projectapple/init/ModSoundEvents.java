package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;

public class ModSoundEvents {
    public static final Lazy<SoundEvent> FLESH_EQUIP_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "entity.skizzik_flesh.equip")));
    public static final Lazy<SoundEvent> MUSIC_SKIZZIK_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "music.skizzik")));

    public static final RegistryObject<SoundEvent> FLESH_EQUIP = Register.SOUND_EVENTS.register("entity.skizzik_flesh.equip", FLESH_EQUIP_LAZY);
    public static final RegistryObject<SoundEvent> MUSIC_SKIZZIK = Register.SOUND_EVENTS.register("music.skizzik", MUSIC_SKIZZIK_LAZY);

    public static void register() {}
}