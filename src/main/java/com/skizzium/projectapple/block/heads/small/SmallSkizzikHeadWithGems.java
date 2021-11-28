package com.skizzium.projectapple.block.heads.small;

import com.skizzium.projectapple.block.heads.SkizzikHeadWithGems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SmallSkizzikHeadWithGems extends SmallSkizzikHead {
    public SmallSkizzikHeadWithGems(Type skull, Properties properties) {
        super(skull, properties);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        
        BlockEntity block = level.getBlockEntity(pos);
        if (block instanceof SkullBlockEntity) {
            SkizzikHeadWithGems.checkSpawn(level, pos, (SkullBlockEntity)block);
        }
    }
}
