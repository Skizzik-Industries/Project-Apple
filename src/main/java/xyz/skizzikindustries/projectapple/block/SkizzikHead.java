package xyz.skizzikindustries.projectapple.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import xyz.skizzikindustries.projectapple.init.ModBlocks;

import javax.annotation.Nullable;

public class SkizzikHead extends SkullBlock {
    /* @Nullable
    private static BlockPattern skizzikPatternFull;
    @Nullable
    private static BlockPattern skizzikPatternBase; */

    public SkizzikHead(AbstractBlock.Properties properties) {
        super(ModBlocks.ModSkullTypes.SKIZZIK, properties);
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
