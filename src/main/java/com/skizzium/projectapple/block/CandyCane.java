package com.skizzium.projectapple.block;

import com.skizzium.projectapple.init.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import com.skizzium.projectapple.init.ModTags;

public class CandyCane extends SugarCaneBlock {
    public CandyCane(Properties properties) {
        super(properties);
    }
    
    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos.below());
        if (blockstate.getBlock() == this) {
            return true;
        }
        else {
            if (blockstate.is(ModBlocks.CANDY_NYLIUM.get()) || blockstate.is(ModBlocks.CANDYRACK.get())) {
                BlockPos blockpos = pos.below();

                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    FluidState fluidstate = world.getFluidState(blockpos.relative(direction));
                    if (fluidstate.is(ModTags.Fluids.CANDY_FLUIDS)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
