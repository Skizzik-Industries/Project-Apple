package com.skizzium.projectapple.init.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import com.skizzium.projectapple.init.PA_Tags;

import java.util.function.Supplier;

public enum PA_Tiers implements IItemTier {
    CANDIANITE(2, 350, 7.0F, 2.5F, 15, () -> {
        return Ingredient.of(PA_Items.CANDIANITE_INGOT.get());
    }),
    RAINBOW(5, 2100, 18.0F, 5.5F, 22, () -> {
        return Ingredient.of(PA_Tags.Items.ALL_GEMS);
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    private PA_Tiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
