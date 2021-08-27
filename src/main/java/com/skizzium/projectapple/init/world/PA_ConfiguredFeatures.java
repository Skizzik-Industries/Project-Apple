package com.skizzium.projectapple.init.world;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;

public class PA_ConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> PATCH_CANDY_CANE = register("patch_candy_cane", Feature.RANDOM_PATCH.configured(PA_ConfiguredFeatures.Configs.CANDY_CANE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(10));

    public static final ConfiguredFeature<?, ?> LAKE_SYRUP = register("lake_syrup", Feature.LAKE.configured(new BlockStateFeatureConfig(BlockStates.MAPLE_SYRUP)).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CANDY = register("candy", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LOG), new SimpleBlockStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LEAVES), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_CANDY = register("fancy_candy", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LOG), new SimpleBlockStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_LEAVES), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CANDY_BEES_005 = register("candy_bees_005", Feature.TREE.configured(CANDY.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_CANDY_BEES_005 = register("fancy_candy_bees_005", Feature.TREE.configured(FANCY_CANDY.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));

    public static final ConfiguredFeature<?, ?> CANDY_PLAIN_VEGETATION = register("candy_plain_vegetation", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(PA_ConfiguredFeatures.FANCY_CANDY_BEES_005.weighted(0.33333334F)), PA_ConfiguredFeatures.CANDY_BEES_005)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));

    public static final ConfiguredFeature<?, ?> ORE_CANDIANITE = register("ore_candianite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockStates.CANDIANITE_ORE, 9)).range(63)).squared().count(20);
    public static final ConfiguredFeature<?, ?> ORE_RAINBOW = register("ore_rainbow", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockStates.RAINBOW_ORE, 3)).range(15)).squared().count(1);

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(ProjectApple.MOD_ID, name), feature);
    }

    private static class Configs {
        public static final BlockClusterFeatureConfig CANDY_CANE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(PA_ConfiguredFeatures.BlockStates.CANDY_CANE), new ColumnBlockPlacer(2, 2))).tries(20).xspread(4).yspread(0).zspread(4).noProjection().needWater().build();
    }

    private static class BlockStates {
        public static final BlockState CANDY_CANE = PA_Blocks.CANDY_CANE.get().defaultBlockState();

        public static final BlockState MAPLE_SYRUP = Blocks.WATER.defaultBlockState();

        public static final BlockState CANDY_SAPLING = PA_Blocks.CANDY_SAPLING.get().defaultBlockState();
        public static final BlockState CANDY_LEAVES = PA_Blocks.CANDY_LEAVES.get().defaultBlockState();
        public static final BlockState CANDY_LOG = PA_Blocks.CANDY_LOG.get().defaultBlockState();

        public static final BlockState CANDIANITE_ORE = PA_Blocks.CANDIANITE_ORE.get().defaultBlockState();
        public static final BlockState RAINBOW_ORE = PA_Blocks.RAINBOW_ORE.get().defaultBlockState();
    }
}
