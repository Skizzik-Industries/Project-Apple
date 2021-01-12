package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.entity.InactiveSkizzikEntity;
import net.uskizzik.skizzik.block.SkizzikLeatherBlockBlock;
import net.uskizzik.skizzik.block.SkizzikHeadBlock;
import net.uskizzik.skizzik.block.CommandBlockBlock;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class SkizzikHeadGemsBlockAddedProcedure extends SkizzikModElements.ModElement {
	public SkizzikHeadGemsBlockAddedProcedure(SkizzikModElements instance) {
		super(instance, 162);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure SkizzikHeadGemsBlockAdded!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure SkizzikHeadGemsBlockAdded!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure SkizzikHeadGemsBlockAdded!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure SkizzikHeadGemsBlockAdded!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(world.getDifficulty() == Difficulty.PEACEFUL))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) z))).getBlock() == SkizzikLeatherBlockBlock.block.getDefaultState()
					.getBlock())) {
				if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == SkizzikLeatherBlockBlock.block
						.getDefaultState().getBlock())) {
					if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == SkizzikLeatherBlockBlock.block
							.getDefaultState().getBlock())) {
						if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == CommandBlockBlock.block
								.getDefaultState().getBlock())) {
							if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == SkizzikHeadBlock.block
									.getDefaultState().getBlock())) {
								if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == SkizzikHeadBlock.block
										.getDefaultState().getBlock())) {
									if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1))))
											.getBlock() == SkizzikHeadBlock.block.getDefaultState().getBlock())) {
										if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))))
												.getBlock() == SkizzikHeadBlock.block.getDefaultState().getBlock())) {
											world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y - 2), (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)), Blocks.AIR.getDefaultState(), 3);
											if (world instanceof ServerWorld) {
												Entity entityToSpawn = new InactiveSkizzikEntity.CustomEntity(InactiveSkizzikEntity.entity,
														(World) world);
												entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
												if (entityToSpawn instanceof MobEntity)
													((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
															world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
															(ILivingEntityData) null, (CompoundNBT) null);
												world.addEntity(entityToSpawn);
											}
											if (world instanceof World && !world.isRemote()) {
												((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
														(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																.getValue(new ResourceLocation("entity.wither.spawn")),
														SoundCategory.NEUTRAL, (float) 1, (float) 1);
											} else {
												((World) world).playSound(x, y, z,
														(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																.getValue(new ResourceLocation("entity.wither.spawn")),
														SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
											}
										}
									}
								}
							}
						}
					}
				} else if (((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == SkizzikLeatherBlockBlock.block
						.getDefaultState().getBlock())) {
					if (((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == SkizzikLeatherBlockBlock.block
							.getDefaultState().getBlock())) {
						if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == CommandBlockBlock.block
								.getDefaultState().getBlock())) {
							if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == SkizzikHeadBlock.block
									.getDefaultState().getBlock())) {
								if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == SkizzikHeadBlock.block
										.getDefaultState().getBlock())) {
									if (((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z)))
											.getBlock() == SkizzikHeadBlock.block.getDefaultState().getBlock())) {
										if (((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z)))
												.getBlock() == SkizzikHeadBlock.block.getDefaultState().getBlock())) {
											world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y - 2), (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z), Blocks.AIR.getDefaultState(), 3);
											world.setBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z), Blocks.AIR.getDefaultState(), 3);
											if (world instanceof ServerWorld) {
												Entity entityToSpawn = new InactiveSkizzikEntity.CustomEntity(InactiveSkizzikEntity.entity,
														(World) world);
												entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
												if (entityToSpawn instanceof MobEntity)
													((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
															world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
															(ILivingEntityData) null, (CompoundNBT) null);
												world.addEntity(entityToSpawn);
											}
											if (world instanceof World && !world.isRemote()) {
												((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
														(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																.getValue(new ResourceLocation("entity.wither.spawn")),
														SoundCategory.NEUTRAL, (float) 1, (float) 1);
											} else {
												((World) world).playSound(x, y, z,
														(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
																.getValue(new ResourceLocation("entity.wither.spawn")),
														SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
