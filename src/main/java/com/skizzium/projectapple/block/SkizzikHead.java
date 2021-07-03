package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SkizzikHead extends SkullBlock {
    public SkizzikHead(ISkullType skull, AbstractBlock.Properties properties) {
        super(skull, properties);
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
