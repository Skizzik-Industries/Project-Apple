package com.skizzium.projectapple.init.world;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;

import java.util.OptionalInt;

public class PA_ConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> LAKE_SYRUP = register("lake_syrup", Feature.LAKE.configured(new BlockStateConfiguration(PA_ConfiguredFeatures.BlockStates.MAPLE_SYRUP)).range(Features.Decorators.FULL_RANGE).squared().rarity(4));

    public static final ConfiguredFeature<TreeConfiguration, ?> CANDY = register("candy", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LOG), new StraightTrunkPlacer(4, 2, 0), new SimpleStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LEAVES), new SimpleStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_SAPLING), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FANCY_CANDY = register("fancy_candy", Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LOG), new FancyTrunkPlacer(3, 11, 0), new SimpleStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LEAVES), new SimpleStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_SAPLING), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> CANDY_BEES_005 = register("candy_bees_005", Feature.TREE.configured(CANDY.config().withDecorators(ImmutableList.of(Features.Decorators.BEEHIVE_005))));
    public static final ConfiguredFeature<TreeConfiguration, ?> FANCY_CANDY_BEES_005 = register("fancy_candy_bees_005", Feature.TREE.configured(FANCY_CANDY.config().withDecorators(ImmutableList.of(Features.Decorators.BEEHIVE_005))));

    public static final ConfiguredFeature<?, ?> CANDY_PLAIN_VEGETATION = register("candy_plain_vegetation", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(PA_ConfiguredFeatures.FANCY_CANDY_BEES_005.weighted(0.33333334F)), PA_ConfiguredFeatures.CANDY_BEES_005)).decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05F, 1))));

    public static final ConfiguredFeature<?, ?> ORE_CANDIANITE = register("ore_candianite", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockStates.CANDIANITE_ORE, 9)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(63))).squared().count(20);
    public static final ConfiguredFeature<?, ?> ORE_RAINBOW = register("ore_rainbow", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockStates.RAINBOW_ORE, 3)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(15))).squared().count(1);

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(ProjectApple.MOD_ID, name), feature);
    }

    public static class BlockStates {
        public static final BlockState MAPLE_SYRUP = Blocks.WATER.defaultBlockState();

        public static final BlockState CANDY_SAPLING = Blocks.OAK_SAPLING.defaultBlockState();
        public static final BlockState CANDY_LEAVES = PA_Blocks.CANDY_LEAVES.get().defaultBlockState();
        public static final BlockState CANDY_LOG = PA_Blocks.CANDY_LOG.get().defaultBlockState();

        public static final BlockState CANDIANITE_ORE = PA_Blocks.CANDIANITE_ORE.get().defaultBlockState();
        public static final BlockState RAINBOW_ORE = PA_Blocks.RAINBOW_ORE.get().defaultBlockState();
    }
}
