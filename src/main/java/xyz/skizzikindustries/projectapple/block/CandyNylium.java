package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;

import java.util.List;
import java.util.Random;

public class CandyNylium extends Block implements IGrowable {
    public CandyNylium(Properties properties) {
        super(properties);
    }

    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
        return true;
    }

    private static boolean canBeNylium(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeNylium(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.tick(state, world, pos, rand);
        if (!canBeNylium(state, world, pos)) {
            if (!world.isAreaLoaded(pos, 3)) return;
            world.setBlockAndUpdate(pos, ModBlocks.CANDYRACK.get().defaultBlockState());
        } else {
            if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                    if (world.getBlockState(blockpos).is(ModBlocks.CANDYRACK.get()) && canPropagate(blockstate, world, blockpos)) {
                        world.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = Blocks.GRASS.defaultBlockState();

        bonemealing:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!world.getBlockState(blockpos1.below()).is(this) || world.getBlockState(blockpos1).isCollisionShapeFullBlock(world, blockpos1)) {
                    continue bonemealing;
                }
            }

            BlockState blockstate2 = world.getBlockState(blockpos1);
            if (blockstate2.is(blockstate.getBlock()) && rand.nextInt(10) == 0) {
                ((IGrowable)blockstate.getBlock()).performBonemeal(world, rand, blockpos1, blockstate2);
            }

            if (blockstate2.isAir()) {
                BlockState blockstate1;
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = world.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    ConfiguredFeature<?, ?> configuredfeature = list.get(0);
                    FlowersFeature flowersfeature = (FlowersFeature)configuredfeature.feature;
                    blockstate1 = flowersfeature.getRandomFlower(rand, blockpos1, configuredfeature.config());
                } else {
                    blockstate1 = blockstate;
                }

                if (blockstate1.canSurvive(world, blockpos1)) {
                    world.setBlock(blockpos1, blockstate1, 3);
                }
            }
        }
    }
}
