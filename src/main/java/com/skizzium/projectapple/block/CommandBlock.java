package com.skizzium.projectapple.block;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_BlockStates;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;

public class CommandBlock extends Block {
    public static final IntegerProperty HOLIDAY = PA_BlockStates.HOLIDAY;
    private final PushReaction pushReaction;

    public CommandBlock(PushReaction reaction, Properties properties) {
        super(properties);
        this.pushReaction = reaction;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return this.pushReaction;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HOLIDAY, ProjectApple.holiday);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(HOLIDAY);
    }
}
