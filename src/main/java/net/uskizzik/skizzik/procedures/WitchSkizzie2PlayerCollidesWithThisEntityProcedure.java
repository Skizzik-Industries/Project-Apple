package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class WitchSkizzie2PlayerCollidesWithThisEntityProcedure extends SkizzikModElements.ModElement {
	public WitchSkizzie2PlayerCollidesWithThisEntityProcedure(SkizzikModElements instance) {
		super(instance, 244);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency sourceentity for procedure WitchSkizzie2PlayerCollidesWithThisEntity!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((Math.random() < 0.01)) {
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 1200, (int) 3, (false), (false)));
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int) 1200, (int) 3, (false), (false)));
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 1200, (int) 1, (false), (false)));
		}
	}
}
