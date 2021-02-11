package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.item.CandyCaneItem;
import net.uskizzik.skizzik.block.CandyGrassBlock;
import net.uskizzik.skizzik.block.CandyCanePlantBlock;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@SkizzikModElements.ModElement.Tag
public class CandyCaneRightClickedInAirProcedure extends SkizzikModElements.ModElement {
	public CandyCaneRightClickedInAirProcedure(SkizzikModElements instance) {
		super(instance, 223);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure CandyCaneRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure CandyCaneRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure CandyCaneRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure CandyCaneRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure CandyCaneRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(CandyCaneItem.block, (int) (1)).getItem())
				&& (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CandyGrassBlock.block.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CandyCanePlantBlock.block.getDefaultState()
								.getBlock())))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), CandyCanePlantBlock.block.getDefaultState(), 3);
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand()) {
			return;
		}
		double i = event.getPos().getX();
		double j = event.getPos().getY();
		double k = event.getPos().getZ();
		IWorld world = event.getWorld();
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
