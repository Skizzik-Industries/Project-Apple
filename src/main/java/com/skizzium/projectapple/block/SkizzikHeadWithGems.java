package com.skizzium.projectapple.block;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockMaterialPredicate;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SkizzikHeadWithGems extends SkullBlock {
    @Nullable
    private static BlockPattern skizzikPatternFull;
    @Nullable
    private static BlockPattern skizzikPatternBase;

    public SkizzikHeadWithGems(SkullBlock.Type skull, BlockBehaviour.Properties properties) {
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

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        super.setPlacedBy(world, pos, state, entity, stack);
        BlockEntity block = world.getBlockEntity(pos);
        
        if (block instanceof SkullBlockEntity) {
            checkSpawn(world, pos, (SkullBlockEntity)block);
        }
    }
    
    public static void checkSpawn(Level world, BlockPos pos, SkullBlockEntity block) {
        if (!world.isClientSide) {
            BlockState state = block.getBlockState();
            boolean isSkizzikHead = state.is(PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get()) || state.is(PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get()) || state.is(PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get());

            if (isSkizzikHead && pos.getY() >= 0 && world.getDifficulty() != Difficulty.PEACEFUL) {
                BlockPattern pattern = getOrCreateSkizzikFull();
                BlockPattern.BlockPatternMatch patternHelper = pattern.find(world, pos);

                if (patternHelper != null) {
                    for(int i = 0; i < pattern.getWidth(); ++i) {
                        for(int j = 0; j < pattern.getHeight(); ++j) {
                            BlockInWorld cachedBlock = patternHelper.getBlock(i, j, 0);
                            world.setBlock(cachedBlock.getPos(), Blocks.AIR.defaultBlockState(), 2);
                            world.levelEvent(2001, cachedBlock.getPos(), Block.getId(cachedBlock.getState()));
                        }
                    }

                    Skizzik skizzik = PA_Entities.SKIZZIK.get().create(world);
                    BlockPos blockPos = patternHelper.getBlock(1, 2, 0).getPos();

                    skizzik.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.55D, (double)blockPos.getZ() + 0.5D, patternHelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F, 0.0F);
                    skizzik.yBodyRot = patternHelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;

                    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
                    lightning.moveTo(Vec3.atBottomCenterOf(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ())));
                    world.addFreshEntity(lightning);

                    for(ServerPlayer player : world.getEntitiesOfClass(ServerPlayer.class, skizzik.getBoundingBox().inflate(50.0D))) {
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

    public static boolean canSpawnMob(Level world, BlockPos pos, ItemStack itemStack) {
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
                    .where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR)))
                    .where('*', BlockInWorld.hasState(BlockStatePredicate.forBlock(PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get())))
                    .where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get()).or(BlockStatePredicate.forBlock(PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get()))))
                    .where('@', BlockInWorld.hasState(BlockStatePredicate.forBlock(PA_Blocks.COMMAND_BLOCK.get())))
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(ProjectApple.holiday == 1 ? Blocks.SPRUCE_PLANKS : PA_Blocks.SKIZZIK_FLESH_BLOCK.get()))).build();
        }

        return skizzikPatternFull;
    }

    private static BlockPattern getOrCreateSkizzikBase() {
        if (skizzikPatternBase == null) {
            skizzikPatternBase = BlockPatternBuilder.start().aisle("   ", "   ", "#@#", "~#~")
                    .where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR)))
                    .where('@', BlockInWorld.hasState(BlockStatePredicate.forBlock(PA_Blocks.COMMAND_BLOCK.get())))
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(PA_Blocks.SKIZZIK_FLESH_BLOCK.get()))).build();
        }

        return skizzikPatternBase;
    }
}
