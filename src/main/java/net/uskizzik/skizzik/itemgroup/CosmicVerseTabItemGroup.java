
package net.uskizzik.skizzik.itemgroup;

import net.uskizzik.skizzik.block.CosmicNyliumBlock;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@SkizzikModElements.ModElement.Tag
public class CosmicVerseTabItemGroup extends SkizzikModElements.ModElement {
	public CosmicVerseTabItemGroup(SkizzikModElements instance) {
		super(instance, 295);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabcosmic_verse_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CosmicNyliumBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
