package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

public class SkizzikWallHead extends WallSkullBlock {
    public SkizzikWallHead(SkullBlock.Type skull, BlockBehaviour.Properties properties) {
        super(skull, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_Skull(pos, state);
    }
}
