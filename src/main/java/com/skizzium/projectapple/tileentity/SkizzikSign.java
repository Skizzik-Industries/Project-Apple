package com.skizzium.projectapple.tileentity;

import com.skizzium.projectapple.init.ModTileEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class SkizzikSign extends SignTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return ModTileEntities.SKIZZIK_SIGN.get();
    }
}
