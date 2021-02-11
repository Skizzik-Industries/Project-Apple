package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class Skizzik1ThisEntityKillsAnotherOneProcedure extends SkizzikModElements.ModElement {
	public Skizzik1ThisEntityKillsAnotherOneProcedure(SkizzikModElements instance) {
		super(instance, 136);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency sourceentity for procedure Skizzik1ThisEntityKillsAnotherOne!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (sourceentity instanceof LivingEntity)
			((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, (int) 1, (int) 1));
	}
}
