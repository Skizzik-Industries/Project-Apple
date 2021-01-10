package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;

import java.util.Random;
import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class RainbowSwordRightClickedOnBlockProcedure extends SkizzikModElements.ModElement {
	public RainbowSwordRightClickedOnBlockProcedure(SkizzikModElements instance) {
		super(instance, 77);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SkizzikMod.LOGGER.warn("Failed to load dependency itemstack for procedure RainbowSwordRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure RainbowSwordRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure RainbowSwordRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure RainbowSwordRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure RainbowSwordRightClickedOnBlock!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.BEDROCK.getDefaultState().getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.getDefaultState().getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
						if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
								.getBlock()))) {
							if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.MOVING_PISTON.getDefaultState()
									.getBlock()))) {
								if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.PISTON_HEAD
										.getDefaultState().getBlock()))) {
									if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.SPAWNER
											.getDefaultState().getBlock()))) {
										if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.COMMAND_BLOCK
												.getDefaultState().getBlock()))) {
											if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
													.getBlock() == Blocks.REPEATING_COMMAND_BLOCK.getDefaultState().getBlock()))) {
												if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
														.getBlock() == Blocks.CHAIN_COMMAND_BLOCK.getDefaultState().getBlock()))) {
													if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
															.getBlock() == Blocks.END_PORTAL.getDefaultState().getBlock()))) {
														if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																.getBlock() == Blocks.VOID_AIR.getDefaultState().getBlock()))) {
															if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																	.getBlock() == Blocks.END_GATEWAY.getDefaultState().getBlock()))) {
																if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																		.getBlock() == Blocks.END_PORTAL_FRAME.getDefaultState().getBlock()))) {
																	if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																			.getBlock() == Blocks.BARRIER.getDefaultState().getBlock()))) {
																		if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																				.getBlock() == Blocks.STRUCTURE_VOID.getDefaultState().getBlock()))) {
																			if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																					.getBlock() == Blocks.STRUCTURE_BLOCK.getDefaultState()
																							.getBlock()))) {
																				if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																						.getBlock() == Blocks.STRUCTURE_BLOCK.getDefaultState()
																								.getBlock()))) {
																					if ((!((world
																							.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																									.getBlock() == Blocks.STRUCTURE_BLOCK
																											.getDefaultState().getBlock()))) {
																						if ((!((world.getBlockState(
																								new BlockPos((int) x, (int) y, (int) z)))
																										.getBlock() == Blocks.STRUCTURE_BLOCK
																												.getDefaultState().getBlock()))) {
																							if ((!((world.getBlockState(
																									new BlockPos((int) x, (int) y, (int) z)))
																											.getBlock() == Blocks.STRUCTURE_BLOCK
																													.getDefaultState().getBlock()))) {
																								if ((!((world.getBlockState(
																										new BlockPos((int) x, (int) y, (int) z)))
																												.getBlock() == Blocks.JIGSAW
																														.getDefaultState()
																														.getBlock()))) {
																									world.setBlockState(
																											new BlockPos((int) x, (int) y, (int) z),
																											Blocks.AIR.getDefaultState(), 3);
																									if (world instanceof ServerWorld) {
																										((ServerWorld) world).spawnParticle(
																												ParticleTypes.PORTAL, x, y, z,
																												(int) 15, 1, 1, 1, 1);
																									}
																									{
																										ItemStack _ist = (itemstack);
																										if (_ist.attemptDamageItem((int) 1,
																												new Random(), null)) {
																											_ist.shrink(1);
																											_ist.setDamage(0);
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
		}
	}
}
