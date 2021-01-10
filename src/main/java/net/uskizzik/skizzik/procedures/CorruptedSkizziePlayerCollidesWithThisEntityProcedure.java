package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.potion.CorruptionPotion;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class CorruptedSkizziePlayerCollidesWithThisEntityProcedure extends SkizzikModElements.ModElement {
	public CorruptedSkizziePlayerCollidesWithThisEntityProcedure(SkizzikModElements instance) {
		super(instance, 121);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency sourceentity for procedure CorruptedSkizziePlayerCollidesWithThisEntity!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (sourceentity instanceof LivingEntity)
			((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(CorruptionPotion.potion, (int) 120, (int) 1));
	}
}
