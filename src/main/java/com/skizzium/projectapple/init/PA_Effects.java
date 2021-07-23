package com.skizzium.projectapple.init;

import com.skizzium.projectapple.potion.CorruptionPotion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fmllegacy.RegistryObject;

public class PA_Effects {
    public static final RegistryObject<MobEffect> CORRUPTION = PA_Registry.EFFECTS.register("corruption", () -> new CorruptionPotion(MobEffectCategory.HARMFUL, -9959813));

    public static void register() {}
}
