package com.skizzium.projectapple.world.biome;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public interface PA_BiomeInterface {

    default Biome createBiome() {
//        Biome.BiomeBuilder biome = new Biome.BiomeBuilder();
//        biome.precipitation(this.getRainType());
//        biome.biomeCategory(this.getCategory());
//        biome.depth(this.getDepth());
//        biome.scale(this.getScale());
//        biome.temperature(this.getTemperature());
//        biome.downfall(this.getDownfall());
//        biome.specialEffects(this.getBiomeAmbience());
//        biome.mobSpawnSettings(this.getMobSpawnSettings());
//        biome.generationSettings(this.getBiomeGenerationSettings());
//        return biome.build();
        return null;
    }

    Biome.Precipitation getRainType();

    Biome.BiomeCategory getCategory();

    float getDepth();

    float getScale();

    float getTemperature();

    float getDownfall();

    BiomeSpecialEffects getBiomeAmbience();

    MobSpawnSettings getMobSpawnSettings();

    BiomeGenerationSettings getBiomeGenerationSettings();

}