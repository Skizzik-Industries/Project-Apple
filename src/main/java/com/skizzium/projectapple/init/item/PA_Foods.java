package com.skizzium.projectapple.init.item;

import net.minecraft.world.food.FoodProperties;

public class PA_Foods {
    public static final FoodProperties RAW_SKIZZIK_FLESH = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).meat().build();
    public static final FoodProperties SKIZZIK_FLESH = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8F).meat().build();
}
