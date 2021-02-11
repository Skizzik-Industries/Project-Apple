package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.entity.FriendlySkizzieEntity;
import net.uskizzik.skizzik.entity.FriendlyMinigunSkizzieEntity;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@SkizzikModElements.ModElement.Tag
public class RidableSkizziesProcedure extends SkizzikModElements.ModElement {
	public RidableSkizziesProcedure(SkizzikModElements instance) {
		super(instance, 202);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure RidableSkizzies!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getRidingEntity()) instanceof FriendlySkizzieEntity.CustomEntity)
				|| ((entity.getRidingEntity()) instanceof FriendlyMinigunSkizzieEntity.CustomEntity))) {
			System.out.println((entity.rotationPitch));
			(entity.getRidingEntity()).setMotion(((entity.getRidingEntity()).getMotion().getX()), ((entity.rotationPitch) * (-0.01)),
					((entity.getRidingEntity()).getMotion().getZ()));
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
