package com.skizzium.projectapple.block;

import com.skizzium.projectapple.entity.Skizzik;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SkizzikHeadWithGems extends SkullBlock {
    @Nullable
    private static BlockPattern skizzikPatternFull;
    @Nullable
    private static BlockPattern skizzikPatternBase;

    public SkizzikHeadWithGems(ISkullType skull, Properties properties) {
        super(skull, properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new PA_Skull();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        super.setPlacedBy(world, pos, state, entity, stack);
        TileEntity block = world.getBlockEntity(pos);
        
        if (block instanceof SkullTileEntity) {
            checkSpawn(world, pos, (SkullTileEntity)block);
        }
    }
    
    public static void checkSpawn(World world, BlockPos pos, SkullTileEntity block) {
        if (!world.isClientSide) {
            BlockState state = block.getBlockState();
            boolean isSkizzikHead = state.is(PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get()) || state.is(PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get());

            if (isSkizzikHead && pos.getY() >= 0 && world.getDifficulty() != Difficulty.PEACEFUL) {
                BlockPattern pattern = getOrCreateSkizzikFull();
                BlockPattern.PatternHelper patternHelper = pattern.find(world, pos);

                if (patternHelper != null) {
                    for(int i = 0; i < pattern.getWidth(); ++i) {
                        for(int j = 0; j < pattern.getHeight(); ++j) {
                            CachedBlockInfo cachedBlock = patternHelper.getBlock(i, j, 0);
                            world.setBlock(cachedBlock.getPos(), Blocks.AIR.defaultBlockState(), 2);
                            world.levelEvent(2001, cachedBlock.getPos(), Block.getId(cachedBlock.getState()));
                        }
                    }

                    Skizzik skizzik = PA_Entities.SKIZZIK.create(world);
                    BlockPos blockPos = patternHelper.getBlock(1, 2, 0).getPos();

                    skizzik.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.55D, (double)blockPos.getZ() + 0.5D, patternHelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F, 0.0F);
                    skizzik.yBodyRot = patternHelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;

                    LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                    lightning.moveTo(Vector3d.atBottomCenterOf(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ())));
                    world.addFreshEntity(lightning);

                    for(ServerPlayerEntity player : world.getEntitiesOfClass(ServerPlayerEntity.class, skizzik.getBoundingBox().inflate(50.0D))) {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(player, skizzik);
                    }

                    world.addFreshEntity(skizzik);

                    for(int k = 0; k < pattern.getWidth(); ++k) {
                        for(int l = 0; l < pattern.getHeight(); ++l) {
                            world.blockUpdated(patternHelper.getBlock(k, l, 0).getPos(), Blocks.AIR);
                        }
                    }
                }
            }
        }
    }

    public static boolean canSpawnMob(World world, BlockPos pos, ItemStack itemStack) {
        if ((itemStack.getItem() == PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get() || itemStack.getItem() == PA_Items.SKIZZIK_HEAD_WITH_GEMS.get()) && pos.getY() >= 2 && world.getDifficulty() != Difficulty.PEACEFUL && !world.isClientSide) {
            return getOrCreateSkizzikBase().find(world, pos) != null;
        }
        else {
            return false;
        }
    }

    private static BlockPattern getOrCreateSkizzikFull() {
        if (skizzikPatternFull == null) {
            skizzikPatternFull = BlockPatternBuilder.start().aisle("*~*", "*^*", "#@#", "~#~")
                    .where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
                    .where('*', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get())))
                    .where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get()).or(BlockStateMatcher.forBlock(PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get()))))
                    .where('@', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(PA_Blocks.COMMAND_BLOCK.get())))
                    .where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(PA_Blocks.SKIZZIK_FLESH_BLOCK.get()))).build();
        }

        return skizzikPatternFull;
    }

    private static BlockPattern getOrCreateSkizzikBase() {
        if (skizzikPatternBase == null) {
            skizzikPatternBase = BlockPatternBuilder.start().aisle("   ", "   ", "#@#", "~#~")
                    .where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)))
                    .where('@', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(PA_Blocks.COMMAND_BLOCK.get())))
                    .where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(PA_Blocks.SKIZZIK_FLESH_BLOCK.get()))).build();
        }

        return skizzikPatternBase;
    }
}
