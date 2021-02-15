package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class NeverGonnaGiveYouUpRightClickedInAirProcedure extends SkizzikModElements.ModElement {
	public NeverGonnaGiveYouUpRightClickedInAirProcedure(SkizzikModElements instance) {
		super(instance, 250);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure NeverGonnaGiveYouUpRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).attackEntityFrom(new DamageSource("was killed by Rick Astley").setDamageBypassesArmor(), (float) 1e+25);
		}
	}
}
