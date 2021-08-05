package com.skizzium.projectapple.tileentity;

import com.skizzium.projectapple.init.block.PA_TileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PA_Skull extends SkullBlockEntity {
    public PA_Skull(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return PA_TileEntities.PA_SKULL.get();
    }
}
