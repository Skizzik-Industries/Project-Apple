package xyz.skizzikindustries.projectapple;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skizzikindustries.projectapple.block.ModBlocks;
import xyz.skizzikindustries.projectapple.effect.ModEffects;
import xyz.skizzikindustries.projectapple.item.ModItems;

public class Register {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectApple.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectApple.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ProjectApple.MOD_ID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ProjectApple.MOD_ID);
    //public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ProjectApple.MOD_ID);
    //public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ProjectApple.MOD_ID);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        EFFECTS.register(modEventBus);
        //BIOMES.register(modEventBus);
        //FEATURES.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModSoundEvents.register();
        ModEffects.register();
        //ModBiomes.register();
        //OreGeneration.register();
    }
}
