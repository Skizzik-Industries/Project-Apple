package com.skizzium.projectapple.init.world;

import com.google.common.collect.Lists;
import com.skizzium.projectapple.ProjectApple;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class PA_BiomeKeys {
    public static final List<ResourceKey<Biome>> PA_BIOMES = Lists.newArrayList();

    public static final ResourceKey<Biome> CANDY_PLAINS = register("candy_plains", 10);

    private static ResourceKey<Biome> register(String name, int id) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(ProjectApple.MOD_ID, name));
        PA_BIOMES.add(key);
        return key;
    }
}
