package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.potion.CorruptionPotion;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class CorruptedBlockEntityCollidesInTheBlockProcedure extends SkizzikModElements.ModElement {
	public CorruptedBlockEntityCollidesInTheBlockProcedure(SkizzikModElements instance) {
		super(instance, 129);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure CorruptedBlockEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(CorruptionPotion.potion, (int) 60, (int) 1));
	}
}
