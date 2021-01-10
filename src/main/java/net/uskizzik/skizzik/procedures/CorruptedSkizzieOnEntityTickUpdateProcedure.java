package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.block.CorruptedBlockBlock;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class CorruptedSkizzieOnEntityTickUpdateProcedure extends SkizzikModElements.ModElement {
	public CorruptedSkizzieOnEntityTickUpdateProcedure(SkizzikModElements instance) {
		super(instance, 120);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure CorruptedSkizzieOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure CorruptedSkizzieOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure CorruptedSkizzieOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure CorruptedSkizzieOnEntityTickUpdate!");
			return;
		}
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
																											new BlockPos((int) x, (int) (y - 1),
																													(int) z),
																											CorruptedBlockBlock.block
																													.getDefaultState(),
																											3);
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
