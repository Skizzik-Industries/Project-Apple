
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.itemgroup.RainbowTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@SkizzikModElements.ModElement.Tag
public class YellowGemItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:yellow_gem")
	public static final Item block = null;
	public YellowGemItem(SkizzikModElements instance) {
		super(instance, 37);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(RainbowTabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("yellow_gem");
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
