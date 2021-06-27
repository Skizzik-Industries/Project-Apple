package com.skizzium.projectapple.block;

import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import com.skizzium.projectapple.init.PA_Tags;

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
            if (blockstate.is(PA_Blocks.CANDY_NYLIUM.get()) || blockstate.is(PA_Blocks.CANDYRACK.get())) {
                BlockPos blockpos = pos.below();

                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    FluidState fluidstate = world.getFluidState(blockpos.relative(direction));
                    if (fluidstate.is(PA_Tags.Fluids.CANDY_FLUIDS)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
