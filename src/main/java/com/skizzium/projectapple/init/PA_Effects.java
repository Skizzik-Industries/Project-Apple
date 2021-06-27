package com.skizzium.projectapple.init;

import com.skizzium.projectapple.potion.CorruptionPotion;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;

public class PA_Effects {
    public static final RegistryObject<Effect> CORRUPTION = PA_Registry.EFFECTS.register("corruption", () -> new CorruptionPotion(EffectType.HARMFUL, -9959813));

    public static void register() {}
}
