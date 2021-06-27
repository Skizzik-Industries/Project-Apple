package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Sign;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SkizzikWallSign extends WallSignBlock {
    public SkizzikWallSign(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new PA_Sign();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}