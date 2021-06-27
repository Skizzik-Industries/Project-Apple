package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SkizzikWallHead extends WallSkullBlock {
    public SkizzikWallHead(AbstractBlock.Properties properties) {
        super(PA_SkullBlock.CustomTypes.SKIZZIK, properties);
    }

    /* public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        ModBlocks.SKIZZIK_HEAD.get().setPlacedBy(world, pos, state, entity, stack);
    } */

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new PA_Skull();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
