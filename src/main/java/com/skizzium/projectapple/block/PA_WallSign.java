package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_SignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class PA_WallSign extends WallSignBlock {
    public PA_WallSign(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new PA_SignTileEntity();
    }
}