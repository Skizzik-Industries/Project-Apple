package com.skizzium.projectapple.init.world.feature;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.OptionalInt;

public class PA_TreeFeatures {
    private static final BeehiveDecorator BEEHIVE_0002 = new BeehiveDecorator(0.002F);
    private static final BeehiveDecorator BEEHIVE_002 = new BeehiveDecorator(0.02F);
    private static final BeehiveDecorator BEEHIVE_005 = new BeehiveDecorator(0.05F);

    public static final ConfiguredFeature<TreeConfiguration, ?> CANDY = PA_ConfiguredFeatures.register("candy", Feature.TREE.configured(createCandy().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FANCY_CANDY = PA_ConfiguredFeatures.register("fancy_candy", Feature.TREE.configured(createFancyCandy().build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> CANDY_BEES_0002 = PA_ConfiguredFeatures.register("candy_bees_0002", Feature.TREE.configured(createCandy().decorators(ImmutableList.of(BEEHIVE_0002)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> CANDY_BEES_002 = PA_ConfiguredFeatures.register("candy_bees_002", Feature.TREE.configured(createCandy().decorators(ImmutableList.of(BEEHIVE_002)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> CANDY_BEES_005 = PA_ConfiguredFeatures.register("candy_bees_005", Feature.TREE.configured(createCandy().decorators(ImmutableList.of(BEEHIVE_005)).build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> FANCY_CANDY_BEES_0002 = PA_ConfiguredFeatures.register("fancy_candy_bees_0002", Feature.TREE.configured(createFancyCandy().decorators(ImmutableList.of(BEEHIVE_0002)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FANCY_CANDY_BEES_002 = PA_ConfiguredFeatures.register("fancy_candy_bees_002", Feature.TREE.configured(createFancyCandy().decorators(ImmutableList.of(BEEHIVE_002)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FANCY_CANDY_BEES_005 = PA_ConfiguredFeatures.register("fancy_candy_bees_005", Feature.TREE.configured(createFancyCandy().decorators(ImmutableList.of(BEEHIVE_005)).build()));

    private static TreeConfiguration.TreeConfigurationBuilder createCandy() {
        return createStraightBlobTree(PA_Blocks.CANDY_LOG.get(), PA_Blocks.CANDY_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyCandy() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(PA_Blocks.CANDY_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(PA_Blocks.CANDY_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }
    
    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves, int baseHeight, int heightRandA, int heightRandB, int leaveHeight) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(leaveHeight), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }
}
