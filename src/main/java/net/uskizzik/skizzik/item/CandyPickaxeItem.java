
package net.uskizzik.skizzik.item;

import net.uskizzik.skizzik.itemgroup.TemplateTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@SkizzikModElements.ModElement.Tag
public class CandyPickaxeItem extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:candy_pickaxe")
	public static final Item block = null;
	public CandyPickaxeItem(SkizzikModElements instance) {
		super(instance, 45);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
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
		}, 1, -3f, new Item.Properties().group(TemplateTabItemGroup.tab)) {
		}.setRegistryName("candy_pickaxe"));
	}
}
