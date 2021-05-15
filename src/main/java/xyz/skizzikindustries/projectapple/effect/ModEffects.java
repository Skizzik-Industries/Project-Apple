package xyz.skizzikindustries.projectapple.effect;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.Register;

public class ModEffects {
    public static final RegistryObject<Effect> CORRUPTION = Register.EFFECTS.register("corruption", () -> new CorruptionPotion(EffectType.HARMFUL, -9959813));

    public static void register() {}
}
