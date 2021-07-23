package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SkizzikWallHeadWithGems extends WallSkullBlock {
    public SkizzikWallHeadWithGems(SkullBlock.Type skull, Properties properties) {
        super(skull, properties);
    }

    /* @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        PA_Blocks.SKIZZIK_HEAD.get().setPlacedBy(world, pos, state, entity, stack);
    } */

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_Skull(pos, state);
    }
}
