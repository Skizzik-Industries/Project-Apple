
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.itemgroup.TemplateTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@SkizzikModElements.ModElement.Tag
public class CandySwordItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:candy_sword")
	public static final Item block = null;
	public CandySwordItem(SkizzikModElements instance) {
		super(instance, 43);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 300;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 5f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 5;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(CandyIngotItem.block, (int) (1)));
			}
		}, 3, -3f, new Item.Properties().group(TemplateTabItemGroup.tab)) {
		}.setRegistryName("candy_sword"));
	}
}
