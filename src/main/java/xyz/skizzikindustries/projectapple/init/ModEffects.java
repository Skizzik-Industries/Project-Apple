package xyz.skizzikindustries.projectapple.init;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.effect.CorruptionPotion;
import xyz.skizzikindustries.projectapple.init.Register;

public class ModEffects {
    public static final RegistryObject<Effect> CORRUPTION = Register.EFFECTS.register("corruption", () -> new CorruptionPotion(EffectType.HARMFUL, -9959813));

    public static void register() {}
}
