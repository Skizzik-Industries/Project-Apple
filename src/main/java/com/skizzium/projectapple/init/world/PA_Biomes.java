package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.world.biome.CandyPlains;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fmllegacy.RegistryObject;

public class PA_Biomes {
    public static final RegistryObject<Biome> CANDY_PLAINS = PA_Registry.BIOMES.register("candy_plains", new CandyPlains()::createBiome);

    public static void register() {}
}
