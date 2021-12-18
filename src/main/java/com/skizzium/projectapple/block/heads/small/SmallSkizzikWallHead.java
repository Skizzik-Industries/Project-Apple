package com.skizzium.projectapple.block.heads.small;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class SmallSkizzikWallHead extends WallSkullBlock {
    public SmallSkizzikWallHead(SkullBlock.Type type, Properties properties) {
        super(type, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
                Direction.NORTH, Block.box(5.0D, 5.0D, 10.0D, 11.0D, 11.0D, 16.0D), 
                Direction.SOUTH, Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 6.0D), 
                Direction.EAST, Block.box(0.0D, 5.0D, 5.0D, 6.0D, 11.0D, 11.0D), 
                Direction.WEST, Block.box(10.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D)
        ));
        
        return AABBS.get(state.getValue(FACING));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_Skull(pos, state);
    }

    @Override
    public String getDescriptionId() {
        return ProjectApple.getThemedDescriptionId(super.getDescriptionId());
    }
}
