package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.SkullBlock.Type;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SkizzikHeadWithGems extends SkullBlock {
    /* @Nullable
    private static BlockPattern skizzikPatternFull;
    @Nullable
    private static BlockPattern skizzikPatternBase; */

    public SkizzikHeadWithGems(Type skull, Properties properties) {
        super(skull, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PA_Skull(pos, state);
    }

    /* public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        super.setPlacedBy(world, pos, state, entity, stack);
        TileEntity block = world.getBlockEntity(pos);
        
        if (block instanceof SkullTileEntity) {
            checkSpawn(world, pos, (SkullTileEntity)block);
        }
    }

    public static void checkSpawn(World world, BlockPos pos, SkullTileEntity block) {
        if (!world.isClientSide) {
            BlockState state = block.getBlockState();
            boolean flag = state.is(ModBlocks.SKIZZIK_HEAD.get()) || state.is(ModBlocks.SKIZZIK_WALL_HEAD.get());

            if (flag && pos.getY() >= 0 && world.getDifficulty() != Difficulty.PEACEFUL) {
                BlockPattern pattern = getOrCreateSkizzikFull();
                BlockPattern.PatternHelper pattern$helper = pattern.find(world, pos);

                if (pattern$helper != null) {
                    for(int i = 0; i < pattern.getWidth(); ++i) {
                        for(int j = 0; j < pattern.getHeight(); ++j) {
                            CachedBlockInfo cachedBlock = pattern$helper.getBlock(i, j, 0);
                            world.setBlock(cachedBlock.getPos(), Blocks.AIR.defaultBlockState(), 2);
                            world.levelEvent(2001, cachedBlock.getPos(), Block.getId(cachedBlock.getState()));
                        }
                    }

                    Skizzik skizzik = EntityType.WITHER.create(world);
                    BlockPos blockPos = pattern$helper.getBlock(1, 2, 0).getPos();
                    witherentity.moveTo((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.55D, (double)blockpos.getZ() + 0.5D, blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F, 0.0F);
                    witherentity.yBodyRot = blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;
                    witherentity.makeInvulnerable();

                    for(ServerPlayerEntity serverplayerentity : world.getEntitiesOfClass(ServerPlayerEntity.class, witherentity.getBoundingBox().inflate(50.0D))) {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, witherentity);
                    }

                    world.addFreshEntity(witherentity);

                    for(int k = 0; k < blockpattern.getWidth(); ++k) {
                        for(int l = 0; l < blockpattern.getHeight(); ++l) {
                            world.blockUpdated(blockpattern$patternhelper.getBlock(k, l, 0).getPos(), Blocks.AIR);
                        }
                    }

                }
            }
        }
    }

    public static boolean canSpawnMob(World p_196299_0_, BlockPos p_196299_1_, ItemStack p_196299_2_) {
        if (p_196299_2_.getItem() == Items.WITHER_SKELETON_SKULL && p_196299_1_.getY() >= 2 && p_196299_0_.getDifficulty() != Difficulty.PEACEFUL && !p_196299_0_.isClientSide) {
            return getOrCreateWitherBase().find(p_196299_0_, p_196299_1_) != null;
        } else {
            return false;
        }
    }

    private static BlockPattern getOrCreateWitherFull() {
        if (witherPatternFull == null) {
            witherPatternFull = BlockPatternBuilder.start().aisle("^^^", "###", "~#~").where('#', (p_235639_0_) -> {
                return p_235639_0_.getState().is(BlockTags.WITHER_SUMMON_BASE_BLOCKS);
            }).where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.WITHER_SKELETON_SKULL).or(BlockStateMatcher.forBlock(Blocks.WITHER_SKELETON_WALL_SKULL)))).where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return witherPatternFull;
    }

    private static BlockPattern getOrCreateWitherBase() {
        if (witherPatternBase == null) {
            witherPatternBase = BlockPatternBuilder.start().aisle("   ", "###", "~#~").where('#', (p_235638_0_) -> {
                return p_235638_0_.getState().is(BlockTags.WITHER_SUMMON_BASE_BLOCKS);
            }).where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return witherPatternBase;
    } */
}
