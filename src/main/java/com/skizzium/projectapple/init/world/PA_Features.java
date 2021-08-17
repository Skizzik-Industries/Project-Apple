package com.skizzium.projectapple.init.world;

import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class PA_Features {
    public static void addCandyPlainVegetation(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PA_ConfiguredFeatures.CANDY_PLAIN_VEGETATION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FLOWER_PLAIN_DECORATED);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN);
    }

    public static void addCandyPlainsLakes(BiomeGenerationSettings.Builder p_126799_) {
        p_126799_.addFeature(GenerationStep.Decoration.LAKES, PA_ConfiguredFeatures.LAKE_SYRUP);
        p_126799_.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_LAVA);
    }

    public static void addOres(BiomeGenerationSettings.Builder builder, Biome.BiomeCategory biomeCategory) {
        int stageOre = GenerationStep.Decoration.UNDERGROUND_ORES.ordinal();
        if (biomeCategory == Biome.BiomeCategory.PLAINS) {
            builder.addFeature(stageOre, () -> PA_ConfiguredFeatures.ORE_CANDIANITE);
        }
        builder.addFeature(stageOre, () -> PA_ConfiguredFeatures.ORE_RAINBOW);
    }
}
