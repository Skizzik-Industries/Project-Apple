package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_SignTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class PA_Sign extends StandingSignBlock {
    public PA_Sign(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_SignTileEntity(pos, state);
    }
}
