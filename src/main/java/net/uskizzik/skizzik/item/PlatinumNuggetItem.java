
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.itemgroup.TemplateTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@SkizzikModElements.ModElement.Tag
public class PlatinumNuggetItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:platinum_nugget")
	public static final Item block = null;
	public PlatinumNuggetItem(SkizzikModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(TemplateTabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("platinum_nugget");
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
