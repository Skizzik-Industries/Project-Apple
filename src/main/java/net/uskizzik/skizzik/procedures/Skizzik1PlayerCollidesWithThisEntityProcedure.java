package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class Skizzik1PlayerCollidesWithThisEntityProcedure extends SkizzikModElements.ModElement {
	public Skizzik1PlayerCollidesWithThisEntityProcedure(SkizzikModElements instance) {
		super(instance, 133);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure Skizzik1PlayerCollidesWithThisEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 15);
	}
}
