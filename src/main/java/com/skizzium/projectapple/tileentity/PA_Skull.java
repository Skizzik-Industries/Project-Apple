package com.skizzium.projectapple.tileentity;

import com.skizzium.projectapple.init.PA_TileEntities;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PA_Skull extends SkullTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return PA_TileEntities.PA_SKULL.get();
    }
}
