package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.*;
import com.skizzium.projectapple.init.Register;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.*;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    private static WoodType CANDY_WOOD_TYPE = WoodType.create("candy");
    
    public static final RegistryObject<Block> COMMAND_BLOCK = register("command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> 4).emissiveRendering(ModBlocks::always)), Register.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);
    public static final RegistryObject<Block> DEACTIVATED_COMMAND_BLOCK = register("deactivated_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.RARE, false);
    public static final RegistryObject<Block> BROKEN_COMMAND_BLOCK = register("broken_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(45.0F,1200.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);

    public static final RegistryObject<Block> SKIZZIE_STATUE = register("skizzie_statue", () -> new SkizzieStatue(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,6.0F).noOcclusion().sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0).requiresCorrectToolForDrops()), Register.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);
    public static final RegistryObject<FallingBlock> SKIZZIK_LOOT_BAG = register("skizzik_loot_bag", () -> new SkizzikLootBag(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0F,3_600_000.0F).noOcclusion().sound(SoundType.WOOL).emissiveRendering(ModBlocks::always).lightLevel((blockstate) -> 16)), Register.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);

    public static final RegistryObject<Block> SKIZZIK_FLESH_BLOCK = register("skizzik_flesh_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0.8F,0.8F).harvestTool(ToolType.HOE).sound(SoundType.SLIME_BLOCK).emissiveRendering(ModBlocks::always)), Register.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, true);
    public static final RegistryObject<Block> CORRUPTED_BLOCK = register("corrupted_block", () -> new CorruptedBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(1.5f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> 3).emissiveRendering(ModBlocks::always)), Register.MAIN_SKIZZIK_TAB, Rarity.create("CORRUPTED", TextFormatting.OBFUSCATED), false);

    public static final RegistryObject<Block> CANDY_CANE = registerNoItem("candy_cane", () -> new CandyCane(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.CRIMSON_STEM).noCollission().randomTicks().instabreak().sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<FlowingFluidBlock> MAPLE_SYRUP = registerNoItem("maple_syrup", () -> new FlowingFluidBlock(ModFluids.MAPLE_SYRUP, AbstractBlock.Properties.of(Material.WATER, MaterialColor.TERRACOTTA_RED).noCollission().strength(100.0F).noDrops()));
    public static final RegistryObject<Block> WAFFLE_BLOCK = register("waffle_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F,0.2F).harvestTool(ToolType.HOE).sound(SoundType.SNOW)), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> WHITE_CHOCOLATE_BLOCK = register("white_chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CHOCOLATE_BLOCK = register("chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.PODZOL).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> DARK_CHOCOLATE_BLOCK = register("dark_chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_GRAY).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDIANITE_ORE = register("candianite_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F,3.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDY_PLANKS = register("candy_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F,3.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK)), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<WoodButtonBlock> CANDY_BUTTON = register("candy_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.5F).sound(SoundType.SLIME_BLOCK).noCollission()), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<PressurePlateBlock> CANDY_PRESSURE_PLATE = register("candy_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.5F).sound(SoundType.SLIME_BLOCK).noCollission()), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<StandingSignBlock> CANDY_SIGN = registerNoItem("candy_sign", () -> new SkizzikSign(AbstractBlock.Properties.of(Material.WOOD, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.SLIME_BLOCK), CANDY_WOOD_TYPE));
    public static final RegistryObject<WallSignBlock> CANDY_WALL_SIGN = registerNoItem("candy_wall_sign", () -> new SkizzikWallSign(AbstractBlock.Properties.of(Material.WOOD, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.SLIME_BLOCK).dropsLike(CANDY_SIGN.get()), CANDY_WOOD_TYPE));

    public static final RegistryObject<SlabBlock> CANDY_SLAB = register("candy_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<StairsBlock> CANDY_STAIRS = register("candy_stairs", () -> new StairsBlock(CANDY_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(CANDY_PLANKS.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<FenceBlock> CANDY_FENCE = register("candy_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<FenceGateBlock> CANDY_FENCE_GATE = register("candy_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<TrapDoorBlock> CANDY_TRAPDOOR = register("candy_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(3.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK).noOcclusion()), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<DoorBlock> CANDY_DOOR = register("candy_door", () -> new DoorBlock(AbstractBlock.Properties.copy(CANDY_TRAPDOOR.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<RotatedPillarBlock> CANDY_LOG = register("candy_log", () -> new StrippableLog(AbstractBlock.Properties.of(Material.WOOD, (rotation) -> rotation.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? ModBlocks.CANDY_PLANKS.get().defaultMaterialColor() : MaterialColor.CRIMSON_NYLIUM).strength(2.0F,2.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK)), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_LOG = register("stripped_candy_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(CANDY_LOG.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> CANDY_WOOD = register("candy_wood", () -> new StrippableLog(AbstractBlock.Properties.copy(CANDY_LOG.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_WOOD = register("stripped_candy_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(CANDY_LOG.get())), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<LeavesBlock> CANDY_LEAVES = register("candy_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.2F,0.2F).sound(SoundType.SLIME_BLOCK).randomTicks().isViewBlocking(ModBlocks::never).isSuffocating(ModBlocks::never).noOcclusion()), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDY_NYLIUM = register("candy_nylium", () -> new CandyNylium(AbstractBlock.Properties.of(Material.STONE, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.4F,0.4F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.SLIME_BLOCK).randomTicks()), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDYRACK = register("candyrack", () -> new Block(AbstractBlock.Properties.of(Material.STONE, ModBlocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.4F,0.4F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.SLIME_BLOCK).requiresCorrectToolForDrops()), Register.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> RAINBOW_ORE = register("rainbow_ore", () -> new RainbowOre(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F,3.0F).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> {return 3;} )), Register.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);

    public static final RegistryObject<Block> RAINBOW_GEM_BLOCK = register("rainbow_gem_block", () -> new RainbowGemBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(55.0F,1200.0F).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> {return 7;} ).emissiveRendering(ModBlocks::always)), Register.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);
    public static final RegistryObject<Block> BLACK_GEM_BLOCK = register("black_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BLUE_GEM_BLOCK = register("blue_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BROWN_GEM_BLOCK = register("brown_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> ORANGE_GEM_BLOCK = register("orange_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> GREEN_GEM_BLOCK = register("green_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> PINK_GEM_BLOCK = register("pink_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PINK).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), Register.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);

    public static void register() {}

    public static void registerWoodTypes(final FMLClientSetupEvent event) {
        WoodType.register(ModBlocks.CANDY_WOOD_TYPE);

        event.enqueueWork(() -> {
            Atlases.addWoodType(ModBlocks.CANDY_WOOD_TYPE);
        });
    }

    public static void renderLayers(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(SKIZZIK_LOOT_BAG.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(SKIZZIE_STATUE.get(), RenderType.cutoutMipped());

        RenderTypeLookup.setRenderLayer(CANDY_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CANDY_DOOR.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(CANDY_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModFluids.MAPLE_SYRUP.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModFluids.FLOWING_MAPLE_SYRUP.get(), RenderType.translucent());
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Register.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ItemGroup group, Rarity rarity, boolean isItemFireResistent){
        RegistryObject<T> object = registerNoItem(name, block);
        if(isItemFireResistent) {
            Register.ITEMS.register(name, () -> new BlockItem(object.get(), new Item.Properties().tab(group).rarity(rarity).fireResistant()));
        }
        else {
            Register.ITEMS.register(name, () -> new BlockItem(object.get(), new Item.Properties().tab(group).rarity(rarity)));
        }
        return object;
    }

    public enum ModSkullTypes implements SkullBlock.ISkullType {
        SKIZZIK
    }

    private static boolean always(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) {
        return true;
    }
    private static boolean never(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) { return false; }
}
