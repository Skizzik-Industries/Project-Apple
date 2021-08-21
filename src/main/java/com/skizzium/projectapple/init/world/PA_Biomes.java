package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.world.biome.CandyPlains;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Biomes {
    public static final RegistryObject<Biome> CANDY_PLAINS = PA_Registry.BIOMES.register("candy_plains", new CandyPlains()::createBiome);
    public static final BiomeManager.BiomeEntry CANDY_PLAINS_ENTRY = new BiomeManager.BiomeEntry(PA_BiomeKeys.CANDY_PLAINS, 10);

    @SubscribeEvent
    public static void registerBiomes(FMLCommonSetupEvent event) {
        BiomeDictionary.addTypes(PA_BiomeKeys.CANDY_PLAINS, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);

        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, PA_Biomes.CANDY_PLAINS_ENTRY);
    }

    public static void register() {}
}
