
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.itemgroup.PrismarineSecretsTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@SkizzikModElements.ModElement.Tag
public class PrismarineStickItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:prismarine_stick")
	public static final Item block = null;
	public PrismarineStickItem(SkizzikModElements instance) {
		super(instance, 290);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(PrismarineSecretsTabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("prismarine_stick");
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