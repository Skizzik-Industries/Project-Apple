package com.skizzium.projectapple.init.world.feature;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class PA_ConfiguredFeatures {
//    public static final ConfiguredFeature<?, ?> PATCH_CANDY_CANE = register("patch_candy_cane", Feature.RANDOM_PATCH.configured(PA_ConfiguredFeatures.Configs.CANDY_CANE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(10));
//    public static final ConfiguredFeature<?, ?> LAKE_SYRUP = register("lake_syrup", Feature.LAKE.configured(new BlockStateConfiguration(PA_ConfiguredFeatures.BlockStates.MAPLE_SYRUP)).range(Features.Decorators.FULL_RANGE).squared().rarity(4));
//    public static final ConfiguredFeature<?, ?> CANDY_PLAIN_VEGETATION = register("candy_plain_vegetation", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(PA_ConfiguredFeatures.FANCY_CANDY_BEES_005.weighted(0.33333334F)), PA_ConfiguredFeatures.CANDY_BEES_005)).decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05F, 1))));
//    public static final ConfiguredFeature<?, ?> ORE_CANDIANITE = register("ore_candianite", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockStates.CANDIANITE_ORE, 9)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(63))).squared().count(20);

    public static final List<OreConfiguration.TargetBlockState> ORE_RAINBOW_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PA_Blocks.RAINBOW_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PA_Blocks.RAINBOW_ORE.get().defaultBlockState()));
    public static final ConfiguredFeature<?, ?> ORE_RAINBOW = FeatureUtils.register("ore_rainbow", Feature.ORE.configured(new OreConfiguration(ORE_RAINBOW_TARGET_LIST, 3, 1.0F)));
    
    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return FeatureUtils.register(new ResourceLocation(ProjectApple.MOD_ID, name).toString(), feature);
    }

//    private static class Configs {
//        public static final RandomPatchConfiguration CANDY_CANE_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_CANE), new ColumnPlacer(BiasedToBottomInt.of(2, 4)))).tries(20).xspread(4).yspread(0).zspread(4).noProjection().needWater().build();
//    }
}
