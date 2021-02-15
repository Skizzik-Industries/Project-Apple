
package net.uskizzik.skizzik.itemgroup;

import net.uskizzik.skizzik.block.CommandBlockBlock;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@SkizzikModElements.ModElement.Tag
public class PrismarineSecretsTabItemGroup extends SkizzikModElements.ModElement {
	public PrismarineSecretsTabItemGroup(SkizzikModElements instance) {
		super(instance, 289);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabprismarine_secrets_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CommandBlockBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
