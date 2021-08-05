package com.skizzium.projectapple.block;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SkizzikWallHeadWithGems extends WallSkullBlock {

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get().setPlacedBy(world, pos, state, entity, stack);
    }

    public SkizzikWallHeadWithGems(SkullBlock.Type skull, BlockBehaviour.Properties properties) {
        super(skull, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_Skull(pos, state);
    }
}
