package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class Skizzik1OnEntityTickUpdateProcedure extends SkizzikModElements.ModElement {
	public Skizzik1OnEntityTickUpdateProcedure(SkizzikModElements instance) {
		super(instance, 187);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure Skizzik1OnEntityTickUpdate!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof ServerWorld)
			((ServerWorld) world).setDayTime((int) 18000);
	}
}
