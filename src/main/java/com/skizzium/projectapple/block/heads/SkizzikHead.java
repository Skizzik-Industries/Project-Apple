package com.skizzium.projectapple.block.heads;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SkizzikHead extends SkullBlock {
    public SkizzikHead(Type skull, BlockBehaviour.Properties properties) {
        super(skull, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_Skull(pos, state);
    }

    @Override
    public String getDescriptionId() {
        return ProjectApple.getThemedDescriptionId(super.getDescriptionId());
    }
}
