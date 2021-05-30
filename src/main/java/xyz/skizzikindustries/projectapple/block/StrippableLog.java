package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class StrippableLog extends RotatedPillarBlock {
    public StrippableLog(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (toolType == ToolType.AXE) {
            Block block = state.getBlock();
            BlockState blockstate = world.getBlockState(pos);
            if (block == ModBlocks.CANDY_LOG.get()) {
                return ModBlocks.STRIPPED_CANDY_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            }
            else if (block == ModBlocks.CANDY_WOOD.get()) {
                return ModBlocks.STRIPPED_CANDY_WOOD.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            }
            else {
                return super.getToolModifiedState(state, world, pos, player, stack, toolType);
            }
        }
        else {
            return super.getToolModifiedState(state, world, pos, player, stack, toolType);
        }
    }
}
