package com.skizzium.projectapple.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import java.util.Map;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SmallSkizzikWallHead extends WallSkullBlock {
    private static final Map<Direction, VoxelShape> CUSTOM_AABBS = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.0D, 5.0D, 10.0D, 11.0D, 11.0D, 16.0D),
                                                                                                    Direction.SOUTH, Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 6.0D),
                                                                                                    Direction.EAST, Block.box(0.0D, 5.0D, 5.0D, 6.0D, 11.0D, 11.0D),
                                                                                                    Direction.WEST, Block.box(10.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D)));

    public SmallSkizzikWallHead(SkullBlock.Type skull, Properties properties) {
        super(skull, properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return CUSTOM_AABBS.get(state.getValue(FACING));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_Skull(pos, state);
    }
}
