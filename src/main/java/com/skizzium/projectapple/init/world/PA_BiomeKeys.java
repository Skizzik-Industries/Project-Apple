package com.skizzium.projectapple.init.world;

import com.google.common.collect.Lists;
import com.skizzium.projectapple.ProjectApple;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class PA_BiomeKeys {
    public static final List<RegistryKey<Biome>> PA_BIOMES = Lists.newArrayList();

    public static final RegistryKey<Biome> CANDY_PLAINS = register("candy_plains", 10);

    private static RegistryKey<Biome> register(String name, int id) {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(ProjectApple.MOD_ID, name));
        PA_BIOMES.add(key);
        return key;
    }
}
