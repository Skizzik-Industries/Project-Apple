
package net.uskizzik.skizzik.itemgroup;

import net.uskizzik.skizzik.item.CandyIngotItem;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@SkizzikModElements.ModElement.Tag
public class CandyTabItemGroup extends SkizzikModElements.ModElement {
	public CandyTabItemGroup(SkizzikModElements instance) {
		super(instance, 265);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabcandy_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CandyIngotItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
