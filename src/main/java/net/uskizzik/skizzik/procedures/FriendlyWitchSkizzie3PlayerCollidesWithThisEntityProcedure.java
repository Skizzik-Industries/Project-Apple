package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class FriendlyWitchSkizzie3PlayerCollidesWithThisEntityProcedure extends SkizzikModElements.ModElement {
	public FriendlyWitchSkizzie3PlayerCollidesWithThisEntityProcedure(SkizzikModElements instance) {
		super(instance, 254);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency sourceentity for procedure FriendlyWitchSkizzie3PlayerCollidesWithThisEntity!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((Math.random() < 0.01)) {
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 60, (int) 5, (false), (false)));
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 60, (int) 5, (false), (false)));
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 60, (int) 5, (false), (false)));
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 60, (int) 5, (false), (false)));
		}
	}
}
