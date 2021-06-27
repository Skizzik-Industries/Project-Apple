package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Sign;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SkizzikSign extends StandingSignBlock {
    public SkizzikSign(Properties properties, WoodType type) {
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
