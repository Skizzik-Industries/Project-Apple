package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_SignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class PA_WallSign extends WallSignBlock {
    public PA_WallSign(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_SignBlockEntity(pos, state);
    }
}