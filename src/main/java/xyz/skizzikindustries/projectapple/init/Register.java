package xyz.skizzikindustries.projectapple.init;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.block.ModFluids;
import xyz.skizzikindustries.projectapple.init.item.ModItems;
import xyz.skizzikindustries.projectapple.tab.LivingCandyTab;
import xyz.skizzikindustries.projectapple.tab.MainSkizzikTab;

public class Register {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectApple.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectApple.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ProjectApple.MOD_ID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ProjectApple.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ProjectApple.MOD_ID);
    //public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ProjectApple.MOD_ID);
    //public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ProjectApple.MOD_ID);

    public static final ItemGroup MAIN_SKIZZIK_TAB = new MainSkizzikTab("main_skizzik_tab");
    public static final ItemGroup LIVING_CANDY_TAB = new LivingCandyTab("living_candy_tab");

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        EFFECTS.register(modEventBus);
        FLUIDS.register(modEventBus);
        //BIOMES.register(modEventBus);
        //FEATURES.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModSoundEvents.register();
        ModEffects.register();
        ModFluids.register();
        //ModBiomes.register();
        //OreGeneration.register();
    }
}
