package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class PA_ConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> ORE_CANDIANITE = register("ore_candianite", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockStates.CANDIANITE_ORE, 9)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(63))).squared().count(20);
    public static final ConfiguredFeature<?, ?> ORE_RAINBOW = register("ore_rainbow", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockStates.RAINBOW_ORE, 3)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(15))).squared().count(1);

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ProjectApple.MOD_ID, name), feature);
    }

    public static class BlockStates {
        public static final BlockState CANDIANITE_ORE = PA_Blocks.CANDIANITE_ORE.get().defaultBlockState();
        public static final BlockState RAINBOW_ORE = PA_Blocks.RAINBOW_ORE.get().defaultBlockState();
    }
}
