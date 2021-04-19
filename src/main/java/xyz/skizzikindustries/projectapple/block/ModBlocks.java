package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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
    public static final RegistryObject<Block> COMMAND_BLOCK = register("command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(1.5f,3_600_000).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).lightLevel((p_235464_0_) -> {return 4;} ).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.EPIC, false);
    public static final RegistryObject<Block> DEACTIVATED_COMMAND_BLOCK = register("deactivated_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(1.5f,3_600_000).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.RARE, false);
    public static final RegistryObject<Block> BROKEN_COMMAND_BLOCK = register("broken_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(1.5f,3_600_000).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, false);
    public static final RegistryObject<Block> SKIZZIK_FLESH_BLOCK = register("skizzik_flesh_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0.8f,0.8f).harvestLevel(2).harvestTool(ToolType.HOE).sound(SoundType.SLIME_BLOCK).emissiveRendering(ModBlocks::always)), ItemGroup.TAB_BUILDING_BLOCKS, Rarity.UNCOMMON, true);

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

    private static boolean always(BlockState p_235426_0_, IBlockReader p_235426_1_, BlockPos p_235426_2_) {
        return true;
    }
}
