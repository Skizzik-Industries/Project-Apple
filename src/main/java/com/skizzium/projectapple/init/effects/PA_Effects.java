package com.skizzium.projectapple.init.effects;

import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.potion.ConversionEffect;
import com.skizzium.projectapple.potion.CorruptionEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fmllegacy.RegistryObject;

public class PA_Effects {
    public static final RegistryObject<MobEffect> CORRUPTION = PA_Registry.EFFECTS.register("corruption", () -> new CorruptionEffect(MobEffectCategory.HARMFUL, 0x68067B));
    public static final RegistryObject<MobEffect> CONVERSION = PA_Registry.EFFECTS.register("conversion", () -> new ConversionEffect(MobEffectCategory.BENEFICIAL, 0xB40A1A));

    public static void register() {}
}
