package xyz.skizzikindustries.projectapple.init.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.block.*;
import xyz.skizzikindustries.projectapple.init.Register;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> COMMAND_BLOCK = register("command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> 4).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.EPIC, false);
    public static final RegistryObject<Block> DEACTIVATED_COMMAND_BLOCK = register("deactivated_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.RARE, false);
    public static final RegistryObject<Block> BROKEN_COMMAND_BLOCK = register("broken_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(45.0F,1200.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, false);

    public static final RegistryObject<Block> STONE_SKIZZIE = register("stone_skizzie", () -> new StoneSkizzie(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,6.0F).noOcclusion().sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0).requiresCorrectToolForDrops()), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, false);
    public static final RegistryObject<FallingBlock> SKIZZIK_LOOT_BAG = register("skizzik_loot_bag", () -> new SkizzikLootBag(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0F,3_600_000.0F).noOcclusion().sound(SoundType.WOOL).emissiveRendering(ModBlocks::always).lightLevel((blockstate) -> 16)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.EPIC, true);

    //public static final RegistryObject<SkullBlock> SKIZZIK_HEAD = register("skizzik_head", () -> new SkizzikHead(AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, false);
    public static final RegistryObject<Block> SKIZZIK_FLESH_BLOCK = register("skizzik_flesh_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0.8F,0.8F).harvestTool(ToolType.HOE).sound(SoundType.SLIME_BLOCK).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, true);

    public static final RegistryObject<Block> CORRUPTED_BLOCK = register("corrupted_block", () -> new CorruptedBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(1.5f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().sound(SoundType.STONE)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.create("CORRUPTED", TextFormatting.OBFUSCATED), false);

    public static final RegistryObject<Block> CANDY_CANE = registerNoItem("candy_cane", () -> new CandyCane(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.CRIMSON_STEM).noCollission().randomTicks().instabreak().sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<FlowingFluidBlock> MAPLE_SYRUP = registerNoItem("maple_syrup", () -> new FlowingFluidBlock(ModFluids.MAPLE_SYRUP, AbstractBlock.Properties.of(Material.WATER, MaterialColor.TERRACOTTA_RED).noCollission().strength(100.0F).noDrops()));
    public static final RegistryObject<Block> WAFFLE_BLOCK = register("waffle_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F,0.2F).harvestTool(ToolType.HOE).sound(SoundType.SNOW)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<Block> WHITE_CHOCOLATE_BLOCK = register("white_chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> CHOCOLATE_BLOCK = register("chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.PODZOL).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> DARK_CHOCOLATE_BLOCK = register("dark_chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_GRAY).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDIANITE_ORE = register("candianite_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F,3.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDY_PLANKS = register("candy_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F,3.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<SlabBlock> CANDY_SLAB = register("candy_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<StairsBlock> CANDY_STAIRS = register("candy_stairs", () -> new StairsBlock(CANDY_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(CANDY_PLANKS.get())), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<FenceBlock> CANDY_FENCE = register("candy_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<RotatedPillarBlock> CANDY_LOG = register("candy_log", () -> new StrippableLog(AbstractBlock.Properties.of(Material.WOOD, (rotation) -> rotation.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.CRIMSON_STEM : MaterialColor.CRIMSON_STEM).strength(2.0F,2.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_LOG = register("stripped_candy_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(CANDY_LOG.get())), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> CANDY_WOOD = register("candy_wood", () -> new StrippableLog(AbstractBlock.Properties.copy(CANDY_LOG.get())), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_WOOD = register("stripped_candy_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(CANDY_LOG.get())), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<LeavesBlock> CANDY_LEAVES = register("candy_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.CRIMSON_STEM).strength(0.2F,0.2F).sound(SoundType.SLIME_BLOCK).randomTicks().isViewBlocking(ModBlocks::never).isSuffocating(ModBlocks::never).noOcclusion()), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDY_NYLIUM = register("candy_nylium", () -> new CandyNylium(AbstractBlock.Properties.of(Material.STONE, MaterialColor.CRIMSON_STEM).strength(0.4F,0.4F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.SLIME_BLOCK).randomTicks()), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDYRACK = register("candyrack", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.CRIMSON_STEM).strength(0.4F,0.4F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.SLIME_BLOCK).requiresCorrectToolForDrops()), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<Block> RAINBOW_ORE = register("rainbow_ore", () -> new RainbowOre(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F,3.0F).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> {return 3;} )), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.EPIC, false);

    public static final RegistryObject<Block> RAINBOW_GEM_BLOCK = register("rainbow_gem_block", () -> new RainbowGemBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(55.0F,1200.0F).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> {return 7;} ).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.EPIC, true);
    public static final RegistryObject<Block> BLACK_GEM_BLOCK = register("black_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> BLUE_GEM_BLOCK = register("blue_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> BROWN_GEM_BLOCK = register("brown_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> ORANGE_GEM_BLOCK = register("orange_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> GREEN_GEM_BLOCK = register("green_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> PINK_GEM_BLOCK = register("pink_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PINK).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Register.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ItemGroup group, Rarity rarity, boolean isItemFireResistent){
        RegistryObject<T> ret = registerNoItem(name, block);
        if(isItemFireResistent) {
            Register.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(group).rarity(rarity).fireResistant()));
        }
        else {
            Register.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(group).rarity(rarity)));
        }
        return ret;
    }

    public enum ModSkullTypes implements SkullBlock.ISkullType {
        SKIZZIK
    }

    private static boolean always(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) {
        return true;
    }
    private static boolean never(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) { return false; }
}
