package com.skizzium.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import com.skizzium.projectapple.init.block.PA_Blocks;

import javax.annotation.Nullable;

public class PA_StrippableLog extends RotatedPillarBlock {
    public PA_StrippableLog(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (toolType == ToolType.AXE) {
            Block block = state.getBlock();
            BlockState blockstate = world.getBlockState(pos);
            if (block == PA_Blocks.CANDY_LOG.get()) {
                return PA_Blocks.STRIPPED_CANDY_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            }
            else if (block == PA_Blocks.CANDY_WOOD.get()) {
                return PA_Blocks.STRIPPED_CANDY_WOOD.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
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
