
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.itemgroup.CandyTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

@SkizzikModElements.ModElement.Tag
public class CandyCaneItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:candy_cane")
	public static final Item block = null;
	public CandyCaneItem(SkizzikModElements instance) {
		super(instance, 55);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(CandyTabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(1).saturation(2.5f).setAlwaysEdible().build()));
			setRegistryName("candy_cane");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 15;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}
