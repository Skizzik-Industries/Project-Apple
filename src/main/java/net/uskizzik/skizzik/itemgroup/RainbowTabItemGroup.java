
package net.uskizzik.skizzik.itemgroup;

import net.uskizzik.skizzik.item.RainbowGemItem;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@SkizzikModElements.ModElement.Tag
public class RainbowTabItemGroup extends SkizzikModElements.ModElement {
	public RainbowTabItemGroup(SkizzikModElements instance) {
		super(instance, 272);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabrainbow_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(RainbowGemItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
