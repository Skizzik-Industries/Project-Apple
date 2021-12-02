package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.world.feature.PA_ConfiguredFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PA_Placements {
    public static final PlacedFeature ORE_RAINBOW = PlacementUtils.register("ore_rainbow", PA_ConfiguredFeatures.ORE_RAINBOW.placed(rareOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier1) {
        return List.of(modifier, InSquarePlacement.spread(), modifier1, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int chance, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(chance), modifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), modifier);
    }

    private static PlacedFeature register(String name, PlacedFeature feature) {
        return PlacementUtils.register(new ResourceLocation(ProjectApple.MOD_ID, name).toString(), feature);
    }
}
