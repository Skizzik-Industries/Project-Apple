package xyz.skizzikindustries.projectapple.world.biome;

import net.minecraft.block.Block;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;

public class ModBiomes {
    public static final RegistryKey<Biome> CANDY_PLAINS = register("candy_plains");

    public static void register() {}

    private static RegistryKey<Biome> register(String biomeid) {
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(biomeid));
    }
}
