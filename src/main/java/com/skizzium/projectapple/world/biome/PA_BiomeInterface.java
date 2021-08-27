package com.skizzium.projectapple.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public interface PA_BiomeInterface {

    default Biome createBiome() {
        Biome.Builder biome = new Biome.Builder();
        biome.precipitation(this.getRainType());
        biome.biomeCategory(this.getCategory());
        biome.depth(this.getDepth());
        biome.scale(this.getScale());
        biome.temperature(this.getTemperature());
        biome.downfall(this.getDownfall());
        biome.specialEffects(this.getBiomeAmbience());
        biome.mobSpawnSettings(this.getMobSpawnSettings());
        biome.generationSettings(this.getBiomeGenerationSettings());
        return biome.build();
    }

    Biome.RainType getRainType();

    Biome.Category getCategory();

    float getDepth();

    float getScale();

    float getTemperature();

    float getDownfall();

    BiomeAmbience getBiomeAmbience();

    MobSpawnInfo getMobSpawnSettings();

    BiomeGenerationSettings getBiomeGenerationSettings();

}