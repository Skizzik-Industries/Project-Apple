package com.skizzium.projectapple.init;

import com.google.common.collect.Maps;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.sound.BossMusic;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.Map;

public class PA_SoundEvents {
    private static int ID = 0;

    public static final Lazy<SoundEvent> FLESH_EQUIP_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "entity.skizzik_flesh.equip")));
    public static final Lazy<SoundEvent> MUSIC_SKIZZIK_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "music.skizzik")));
    public static final Lazy<SoundEvent> FINISH_HIM_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "entity.skizzik.finish_him")));
    public static final Lazy<SoundEvent> DABABY_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ProjectApple.MOD_ID, "entity.dababy")));

    public static final RegistryObject<SoundEvent> FLESH_EQUIP = PA_Registry.SOUND_EVENTS.register("entity.skizzik_flesh.equip", FLESH_EQUIP_LAZY);
    public static final RegistryObject<SoundEvent> MUSIC_SKIZZIK = PA_Registry.SOUND_EVENTS.register("music.skizzik", MUSIC_SKIZZIK_LAZY);
    public static final RegistryObject<SoundEvent> FINISH_HIM = PA_Registry.SOUND_EVENTS.register("entity.skizzik.finish_him", FINISH_HIM_LAZY);
    public static final RegistryObject<SoundEvent> DABABY = PA_Registry.SOUND_EVENTS.register("entity.dababy", DABABY_LAZY);

    private static final Map<Integer, SoundEvent> BOSS_THEMES = Util.make(Maps.newHashMap(), (builder) -> {
        builder.put(ID++, MUSIC_SKIZZIK_LAZY.get());
    });

    public static int getID(SoundEvent music) {
        for (Integer key: BOSS_THEMES.keySet()) {
            if (music.equals(BOSS_THEMES.get(key))) {
                return key;
            }
        }
        return 0;
    }

    public static SoundEvent getMusic(int id) {
        return BOSS_THEMES.get(id);
    }

    public static void register() {}
}