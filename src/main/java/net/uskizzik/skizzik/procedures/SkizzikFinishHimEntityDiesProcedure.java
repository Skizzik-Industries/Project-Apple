package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.block.SkizzikLootbagBlock;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class SkizzikFinishHimEntityDiesProcedure extends SkizzikModElements.ModElement {
	public SkizzikFinishHimEntityDiesProcedure(SkizzikModElements instance) {
		super(instance, 273);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure SkizzikFinishHimEntityDies!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure SkizzikFinishHimEntityDies!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure SkizzikFinishHimEntityDies!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure SkizzikFinishHimEntityDies!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.isRemote()) {
			((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.death")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
		} else {
			((World) world).playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.death")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), SkizzikLootbagBlock.block.getDefaultState(), 3);
	}
}