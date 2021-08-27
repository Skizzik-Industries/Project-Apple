package com.skizzium.projectapple.tileentity;

import com.skizzium.projectapple.init.entity.PA_TileEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PA_SignTileEntity extends SignTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return PA_TileEntities.PA_SIGN.get();
    }
}
