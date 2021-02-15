
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.itemgroup.CandyTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@SkizzikModElements.ModElement.Tag
public class ChocolateBarWrapItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:chocolate_bar_wrap")
	public static final Item block = null;
	public ChocolateBarWrapItem(SkizzikModElements instance) {
		super(instance, 51);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(CandyTabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("chocolate_bar_wrap");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
