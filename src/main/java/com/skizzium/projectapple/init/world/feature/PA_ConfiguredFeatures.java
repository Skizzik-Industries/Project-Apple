package com.skizzium.projectapple.init.world.feature;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class PA_ConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> ORE_RAINBOW_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PA_Blocks.RAINBOW_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PA_Blocks.RAINBOW_ORE.get().defaultBlockState()));
    public static final ConfiguredFeature<?, ?> ORE_RAINBOW = FeatureUtils.register("ore_rainbow", Feature.ORE.configured(new OreConfiguration(ORE_RAINBOW_TARGET_LIST, 3, 1.0F)));
    
    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return FeatureUtils.register(new ResourceLocation(ProjectApple.MOD_ID, name).toString(), feature);
    }
}
