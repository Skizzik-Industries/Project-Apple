package com.skizzium.projectapple.block.grower;

import com.skizzium.projectapple.init.world.PA_ConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class CandyTreeGrower extends Tree {
    public CandyTreeGrower() {}

    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random p_60038_, boolean p_60039_) {
        if (p_60038_.nextInt(10) == 0) {
            return p_60039_ ? PA_ConfiguredFeatures.FANCY_CANDY_BEES_005 : PA_ConfiguredFeatures.FANCY_CANDY;
        } else {
            return p_60039_ ? PA_ConfiguredFeatures.CANDY_BEES_005 : PA_ConfiguredFeatures.CANDY;
        }
    }
}