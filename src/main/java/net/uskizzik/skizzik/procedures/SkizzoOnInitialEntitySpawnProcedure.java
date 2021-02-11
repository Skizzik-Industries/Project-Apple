package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.block.Blocks;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class SkizzoOnInitialEntitySpawnProcedure extends SkizzikModElements.ModElement {
	public SkizzoOnInitialEntitySpawnProcedure(SkizzikModElements instance) {
		super(instance, 147);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure SkizzoOnInitialEntitySpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure SkizzoOnInitialEntitySpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure SkizzoOnInitialEntitySpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure SkizzoOnInitialEntitySpawn!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof ServerWorld) {
			LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
			_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
			_ent.setEffectOnly(false);
			((World) world).addEntity(_ent);
		}
		if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.VOID_AIR.getDefaultState()
					.getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
						.getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.FIRE.getDefaultState()
							.getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.FIRE.getDefaultState(), 3);
					}
				}
			}
		}
		if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == Blocks.VOID_AIR.getDefaultState()
					.getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
						.getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == Blocks.FIRE.getDefaultState()
							.getBlock()))) {
						world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.FIRE.getDefaultState(), 3);
					}
				}
			}
		}
		if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == Blocks.VOID_AIR.getDefaultState()
					.getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
						.getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == Blocks.FIRE.getDefaultState()
							.getBlock()))) {
						world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), Blocks.FIRE.getDefaultState(), 3);
					}
				}
			}
		}
		if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.VOID_AIR.getDefaultState()
					.getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
						.getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == Blocks.FIRE.getDefaultState()
							.getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), Blocks.FIRE.getDefaultState(), 3);
					}
				}
			}
		}
		if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.VOID_AIR.getDefaultState()
					.getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.CAVE_AIR.getDefaultState()
						.getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == Blocks.FIRE.getDefaultState()
							.getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.FIRE.getDefaultState(), 3);
					}
				}
			}
		}
	}
}
