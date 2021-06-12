package com.skizzium.projectapple.init.item;

import net.minecraft.item.Food;

public class ModFoods {
    public static final Food PANCAKES = (new Food.Builder()).nutrition(4).saturationMod(0.6F).build();
    public static final Food SYRUP_PANCAKES = (new Food.Builder()).nutrition(5).saturationMod(0.7F).build();
    public static final Food CHOCOLATE_BAR = (new Food.Builder()).nutrition(2).saturationMod(0.9F).build();
    public static final Food WAFFLE = (new Food.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final Food CANDY_CANE = (new Food.Builder()).nutrition(1).saturationMod(1.2F).fast().alwaysEat().build();
    public static final Food MAPLE_SYRUP = (new Food.Builder()).nutrition(3).saturationMod(0.7F).build();
}
