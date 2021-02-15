package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.entity.FriendlySkizzieEntity;
import net.uskizzik.skizzik.entity.FriendlyMinigunSkizzieEntity;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.entity.Entity;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class FlyDownOnKeyPressedProcedure extends SkizzikModElements.ModElement {
	public FlyDownOnKeyPressedProcedure(SkizzikModElements instance) {
		super(instance, 286);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure FlyDownOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getRidingEntity()) instanceof FriendlySkizzieEntity.CustomEntity)
				|| ((entity.getRidingEntity()) instanceof FriendlyMinigunSkizzieEntity.CustomEntity))) {
			(entity.getRidingEntity()).setMotion(((entity.getRidingEntity()).getMotion().getX()), (90 * (-0.01)),
					((entity.getRidingEntity()).getMotion().getZ()));
		}
	}
}
