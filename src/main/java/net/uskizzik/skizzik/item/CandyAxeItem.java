
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.procedures.CandyLogOnBlockRightClickedProcedure;
import net.uskizzik.skizzik.itemgroup.CandyTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;
import java.util.HashMap;

@SkizzikModElements.ModElement.Tag
public class CandyAxeItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:candy_axe")
	public static final Item block = null;
	public CandyAxeItem(SkizzikModElements instance) {
		super(instance, 60);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 300;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 5;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(CandyIngotItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(CandyTabItemGroup.tab)) {
			@Override
			public ActionResultType onItemUse(ItemUseContext context) {
				ActionResultType retval = super.onItemUse(context);
				World world = context.getWorld();
				BlockPos pos = context.getPos();
				PlayerEntity entity = context.getPlayer();
				Direction direction = context.getFace();
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				ItemStack itemstack = context.getItem();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					CandyLogOnBlockRightClickedProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}
		}.setRegistryName("candy_axe"));
	}
}
