package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.Register;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> COMMAND_BLOCK = register("command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65,3_600_000).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> {return 4;} ).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.EPIC, false);
    public static final RegistryObject<Block> DEACTIVATED_COMMAND_BLOCK = register("deactivated_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65,3_600_000).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.RARE, false);
    public static final RegistryObject<Block> BROKEN_COMMAND_BLOCK = register("broken_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(45f,1200).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, false);

    public static final RegistryObject<Block> SKIZZIK_FLESH_BLOCK = register("skizzik_flesh_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0.8f,0.8f).harvestTool(ToolType.HOE).sound(SoundType.SLIME_BLOCK).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, true);

    /* NEEDS LOOT TABLE!!! */ public static final RegistryObject<Block> CANDY_LEAVES = register("candy_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_PINK).strength(0.2f,0.2f).sound(SoundType.SLIME_BLOCK).randomTicks().isViewBlocking(ModBlocks::never).isSuffocating(ModBlocks::never).noOcclusion()), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    /* NEEDS LOOT TABLE!!! */ public static final RegistryObject<Block> CANDY_LOG = register("candy_log", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK).strength(2,2).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDY_NYLIUM = register("candy_nylium", () -> new CandyNylium(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(0.4f,0.4f).harvestLevel(0).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.SLIME_BLOCK).emissiveRendering(ModBlocks::always).randomTicks()), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDYRACK = register("candyrack", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(0.4f,0.4f).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.SLIME_BLOCK).requiresCorrectToolForDrops().emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<Block> RAINBOW_ORE = register("rainbow_ore", () -> new RainbowOre(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(3,3).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> {return 3;} )), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

    public static final RegistryObject<Block> RAINBOW_GEM_BLOCK = register("rainbow_gem_block", () -> new RainbowGemBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(55,1200).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> {return 7;} ).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, true);
    public static final RegistryObject<Block> BLACK_GEM_BLOCK = register("black_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(5,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> BLUE_GEM_BLOCK = register("blue_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).strength(5,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> BROWN_GEM_BLOCK = register("brown_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(5,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(5,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> ORANGE_GEM_BLOCK = register("orange_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(5,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> GREEN_GEM_BLOCK = register("green_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(5,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);
    public static final RegistryObject<Block> PINK_GEM_BLOCK = register("pink_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PINK).strength(5,6).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.COMMON, false);

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

    private static boolean always(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) {
        return true;
    }
    private static boolean never(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) { return false; }
}
