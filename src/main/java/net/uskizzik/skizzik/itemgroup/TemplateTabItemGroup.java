
package net.uskizzik.skizzik.itemgroup;

import net.uskizzik.skizzik.block.SkizzikHeadBlock;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@SkizzikModElements.ModElement.Tag
public class TemplateTabItemGroup extends SkizzikModElements.ModElement {
	public TemplateTabItemGroup(SkizzikModElements instance) {
		super(instance, 93);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabskizzik_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(SkizzikHeadBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
