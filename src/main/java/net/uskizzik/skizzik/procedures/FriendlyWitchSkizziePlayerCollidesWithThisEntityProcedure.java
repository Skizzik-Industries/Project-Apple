package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class FriendlyWitchSkizziePlayerCollidesWithThisEntityProcedure extends SkizzikModElements.ModElement {
	public FriendlyWitchSkizziePlayerCollidesWithThisEntityProcedure(SkizzikModElements instance) {
		super(instance, 148);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency sourceentity for procedure FriendlyWitchSkizziePlayerCollidesWithThisEntity!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((Math.random() < 0.01)) {
			if ((Math.random() < 0.8)) {
				if (sourceentity instanceof LivingEntity)
					((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 60, (int) 5, (false), (false)));
			} else {
				if (sourceentity instanceof LivingEntity)
					((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, (int) 60, (int) 5, (false), (false)));
			}
		}
	}
}
