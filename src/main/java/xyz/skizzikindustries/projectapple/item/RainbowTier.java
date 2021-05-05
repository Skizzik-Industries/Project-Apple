package xyz.skizzikindustries.projectapple.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

public class RainbowTier implements IItemTier {
    @Override
    public int getUses() {
        return 2100;
    }

    @Override
    public float getSpeed() {
        return 18;
    }

    @Override
    public float getAttackDamageBonus() {
        return 5.5F;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(new IItemProvider[]{ModItems.RAINBOW_GEM.get()});
    }
}
