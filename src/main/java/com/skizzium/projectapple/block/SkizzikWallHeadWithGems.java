package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SkizzikWallHeadWithGems extends WallSkullBlock {
    public SkizzikWallHeadWithGems(SkullBlock.ISkullType skull, Properties properties) {
        super(skull, properties);
    }

    /* @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        PA_Blocks.SKIZZIK_HEAD.get().setPlacedBy(world, pos, state, entity, stack);
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
