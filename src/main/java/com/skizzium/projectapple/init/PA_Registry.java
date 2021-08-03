package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.block.PA_Fluids;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.itemgroup.LivingCandyTab;
import com.skizzium.projectapple.itemgroup.MainSkizzikTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PA_Registry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectApple.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectApple.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ProjectApple.MOD_ID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ProjectApple.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ProjectApple.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ProjectApple.MOD_ID);

    public static final CreativeModeTab MAIN_SKIZZIK_TAB = new MainSkizzikTab("main_skizzik_tab");
    public static final CreativeModeTab LIVING_CANDY_TAB = new LivingCandyTab("living_candy_tab");

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        EFFECTS.register(modEventBus);
        FLUIDS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);

        PA_Blocks.register();
        PA_Items.register();
        PA_SoundEvents.register();
        PA_Effects.register();
        PA_Fluids.register();
        PA_Entities.register();
    }
}
