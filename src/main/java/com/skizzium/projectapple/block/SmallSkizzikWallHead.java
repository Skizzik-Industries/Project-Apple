package com.skizzium.projectapple.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.Map;

public class SmallSkizzikWallHead extends WallSkullBlock {
    private static final Map<Direction, VoxelShape> CUSTOM_AABBS = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.0D, 5.0D, 10.0D, 11.0D, 11.0D, 16.0D),
                                                                                                    Direction.SOUTH, Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 6.0D),
                                                                                                    Direction.EAST, Block.box(0.0D, 5.0D, 5.0D, 6.0D, 11.0D, 11.0D),
                                                                                                    Direction.WEST, Block.box(10.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D)));

    public SmallSkizzikWallHead(SkullBlock.ISkullType skull, Properties properties) {
        super(skull, properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return CUSTOM_AABBS.get(state.getValue(FACING));
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new PA_Skull();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
