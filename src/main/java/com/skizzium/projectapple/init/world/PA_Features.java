package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;

public class PA_Features {
    public static void candyPlainsSpawns(MobSpawnInfo.Builder builder) {
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(PA_Entities.CANDY_PIG, 10, 4, 4));
        DefaultBiomeFeatures.commonSpawns(builder);
    }

    public static void addCandyPlainVegetation(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, PA_ConfiguredFeatures.CANDY_PLAIN_VEGETATION);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FLOWER_PLAIN_DECORATED);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN);
    }

    public static void addCandyPlainsExtraVegetation(BiomeGenerationSettings.Builder p_126746_) {
        p_126746_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, PA_ConfiguredFeatures.PATCH_CANDY_CANE);
        p_126746_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
    }

    public static void addCandyPlainsLakes(BiomeGenerationSettings.Builder p_126799_) {
        p_126799_.addFeature(GenerationStage.Decoration.LAKES, PA_ConfiguredFeatures.LAKE_SYRUP);
        p_126799_.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_LAVA);
    }

    public static void addOres(BiomeGenerationSettings.Builder builder, Biome.Category biomeCategory) {
        int stageOre = GenerationStage.Decoration.UNDERGROUND_ORES.ordinal();
        if (biomeCategory == Biome.Category.PLAINS) {
            builder.addFeature(stageOre, () -> PA_ConfiguredFeatures.ORE_CANDIANITE);
        }
        builder.addFeature(stageOre, () -> PA_ConfiguredFeatures.ORE_RAINBOW);
    }
}
