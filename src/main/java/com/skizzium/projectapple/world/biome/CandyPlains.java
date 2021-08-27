package com.skizzium.projectapple.world.biome;

import com.skizzium.projectapple.init.world.PA_Features;
import com.skizzium.projectapple.init.world.PA_SurfaceBuilders;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;

public class CandyPlains implements PA_BiomeInterface {

    @Override
    public Biome.RainType getRainType() {
        return Biome.RainType.RAIN;
    }

    @Override
    public Biome.Category getCategory() {
        return Biome.Category.PLAINS;
    }

    @Override
    public float getDepth() {
        return 0.125F;
    }

    @Override
    public float getScale() {
        return 0.05F;
    }

    @Override
    public float getTemperature() {
        return 0.8F;
    }

    @Override
    public float getDownfall() {
        return 0.4F;
    }

    @Override
    public BiomeAmbience getBiomeAmbience() {
        BiomeAmbience.Builder ambience = new BiomeAmbience.Builder();
        ambience.waterColor(-786388);
        ambience.waterFogColor(-786388);
        ambience.fogColor(12638463);
        ambience.skyColor(12638463);
        ambience.foliageColorOverride(-786388);
        ambience.grassColorOverride(-786388);
        ambience.ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);
        return ambience.build();
    }

    @Override
    public MobSpawnInfo getMobSpawnSettings() {
        MobSpawnInfo.Builder mobSpawns = new MobSpawnInfo.Builder();
        PA_Features.candyPlainsSpawns(mobSpawns);
        mobSpawns.setPlayerCanSpawn();
        return mobSpawns.build();
    }

    @Override
    public BiomeGenerationSettings getBiomeGenerationSettings() {
        BiomeGenerationSettings.Builder settings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(PA_SurfaceBuilders.CANDY);
        //settings.addStructureStart(StructureFeatures.VILLAGE_PLAINS).addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        settings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);

        PA_Features.addCandyPlainsLakes(settings);
        PA_Features.addCandyPlainVegetation(settings); // CUSTOM FOLIAGE
        PA_Features.addCandyPlainsExtraVegetation(settings);

        DefaultBiomeFeatures.addDefaultOverworldLandStructures(settings);
        DefaultBiomeFeatures.addDefaultCarvers(settings);
        DefaultBiomeFeatures.addDefaultMonsterRoom(settings);
        DefaultBiomeFeatures.addPlainGrass(settings); // CUSTOM FOLIAGE
        DefaultBiomeFeatures.addDefaultUndergroundVariety(settings);
        DefaultBiomeFeatures.addDefaultOres(settings);
        DefaultBiomeFeatures.addDefaultSoftDisks(settings);
        DefaultBiomeFeatures.addDefaultMushrooms(settings);
        DefaultBiomeFeatures.addDefaultSprings(settings);
        DefaultBiomeFeatures.addSurfaceFreezing(settings);

        return settings.build();
    }
}