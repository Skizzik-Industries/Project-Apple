package com.skizzium.projectapple.block;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.GrassColor;

public class SkizzieStatue extends FallingBlock implements SimpleWaterloggedBlock {
    public SkizzieStatue(Properties properties) {
        super(properties);
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter world, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(FACING, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case SOUTH :
            default :
                return Shapes.or(box(9.5, 2.1, 10.5, 6.5, 17.1, 7.5),
                        box(12, 17, 14, 4, 25, 6),
                        box(13.5, 13.6, 10, 2.5, 15.6, 8),
                        box(13.5, 11.1, 10, 2.5, 13.1, 8),
                        box(13.5, 8.6, 10, 2.5, 10.6, 8));
            case NORTH :
                return Shapes.or(box(6.5, 2.1, 5.5, 9.5, 17.1, 8.5),
                        box(4, 17, 2, 12, 25, 10),
                        box(2.5, 13.6, 6, 13.5, 15.6, 8),
                        box(2.5, 11.1, 6, 13.5, 13.1, 8),
                        box(2.5, 8.6, 6, 13.5, 10.6, 8));
            case EAST :
                return Shapes.or(box(10.5, 2.1, 6.5, 7.5, 17.1, 9.5),
                        box(14, 17, 4, 6, 25, 12),
                        box(10, 13.6, 2.5, 8, 15.6, 13.5),
                        box(10, 11.1, 2.5, 8, 13.1, 13.5),
                        box(10, 8.6, 2.5, 8, 10.6, 13.5));
            case WEST :
                return Shapes.or(box(5.5, 2.1, 9.5, 8.5, 17.1, 6.5),
                        box(2, 17, 12, 10, 25, 4),
                        box(6, 13.6, 13.5, 8, 15.6, 2.5),
                        box(6, 11.1, 13.5, 8, 13.1, 2.5),
                        box(6, 8.6, 13.5, 8, 10.6, 2.5));
        }

    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }
}
