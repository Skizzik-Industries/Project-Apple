package com.skizzium.projectapple.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import com.skizzium.projectapple.init.block.PA_Blocks;

import javax.annotation.Nullable;

public class PA_StrippableLog extends RotatedPillarBlock {
    public PA_StrippableLog(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolAction toolType) {
        if (toolType == ToolAction.get("axe_strip")) {
            Block block = state.getBlock();
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
