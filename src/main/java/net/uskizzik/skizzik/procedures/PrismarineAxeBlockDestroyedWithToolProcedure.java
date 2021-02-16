package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;

import java.util.Random;
import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class PrismarineAxeBlockDestroyedWithToolProcedure extends SkizzikModElements.ModElement {
	public PrismarineAxeBlockDestroyedWithToolProcedure(SkizzikModElements instance) {
		super(instance, 293);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure PrismarineAxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SkizzikMod.LOGGER.warn("Failed to load dependency itemstack for procedure PrismarineAxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure PrismarineAxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure PrismarineAxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure PrismarineAxeBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure PrismarineAxeBlockDestroyedWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayerEntity) {
					return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SPECTATOR;
				} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
					NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
							.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
					return _npi != null && _npi.getGameType() == GameType.SPECTATOR;
				}
				return false;
			}
		}.checkGamemode(entity)) || (new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayerEntity) {
					return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
				} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
					NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
							.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
					return _npi != null && _npi.getGameType() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity))))) {
			if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.BEDROCK.getDefaultState().getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.getDefaultState().getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock()))) {
						if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock()))) {
							if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
									.getBlock()))) {
								if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.MOVING_PISTON
										.getDefaultState().getBlock()))) {
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
																					.getBlock() == Blocks.STRUCTURE_VOID.getDefaultState()
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
																												.getBlock() == Blocks.STRUCTURE_BLOCK
																														.getDefaultState()
																														.getBlock()))) {
																									if ((!((world.getBlockState(
																											new BlockPos((int) x, (int) y, (int) z)))
																													.getBlock() == Blocks.JIGSAW
																															.getDefaultState()
																															.getBlock()))) {
																										world.setBlockState(
																												new BlockPos((int) x, (int) y,
																														(int) z),
																												Blocks.WATER.getDefaultState(), 3);
																										if (world instanceof World)
																											((World) world)
																													.notifyNeighborsOfStateChange(
																															new BlockPos((int) x,
																																	(int) y, (int) z),
																															((World) world)
																																	.getBlockState(
																																			new BlockPos(
																																					(int) x,
																																					(int) y,
																																					(int) z))
																																	.getBlock());
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
}
