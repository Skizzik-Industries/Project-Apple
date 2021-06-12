package com.skizzium.projectapple.init;

import com.skizzium.projectapple.effect.CorruptionPotion;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;

public class ModEffects {
    public static final RegistryObject<Effect> CORRUPTION = Register.EFFECTS.register("corruption", () -> new CorruptionPotion(EffectType.HARMFUL, -9959813));

    public static void register() {}
}
