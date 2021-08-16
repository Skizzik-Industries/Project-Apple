package com.skizzium.projectapple.init.world;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class PA_Features {
    public static void addOres(BiomeGenerationSettings.Builder builder, Biome.BiomeCategory biomeCategory) {
        int stageOre = GenerationStep.Decoration.UNDERGROUND_ORES.ordinal();
        if (biomeCategory == Biome.BiomeCategory.PLAINS) {
            builder.addFeature(stageOre, () -> PA_ConfiguredFeatures.ORE_CANDIANITE);
        }
        builder.addFeature(stageOre, () -> PA_ConfiguredFeatures.ORE_RAINBOW);
    }
}
