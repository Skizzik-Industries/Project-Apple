package com.skizzium.projectapple.init.item;

import net.minecraft.world.food.FoodProperties;

public class PA_Foods {
    public static final FoodProperties RAW_SKIZZIK_FLESH = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).meat().build();
    public static final FoodProperties SKIZZIK_FLESH = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8F).meat().build();
    
    public static final FoodProperties PANCAKES = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
    public static final FoodProperties SYRUP_PANCAKES = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.7F).build();
    public static final FoodProperties CHOCOLATE_BAR = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.9F).build();
    public static final FoodProperties WAFFLE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties CANDY_CANE = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.2F).fast().alwaysEat().build();
    public static final FoodProperties MAPLE_SYRUP = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.7F).build();
}
