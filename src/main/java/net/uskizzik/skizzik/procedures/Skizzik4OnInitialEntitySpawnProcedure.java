package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class Skizzik4OnInitialEntitySpawnProcedure extends SkizzikModElements.ModElement {
	public Skizzik4OnInitialEntitySpawnProcedure(SkizzikModElements instance) {
		super(instance, 181);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure Skizzik4OnInitialEntitySpawn!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof ServerWorld)
			((ServerWorld) world).setDayTime((int) 18000);
	}
}
