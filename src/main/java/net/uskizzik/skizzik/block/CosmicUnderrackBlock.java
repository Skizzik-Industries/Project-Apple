
package net.uskizzik.skizzik.block;

import net.uskizzik.skizzik.itemgroup.CosmicVerseTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@SkizzikModElements.ModElement.Tag
public class CosmicUnderrackBlock extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:cosmic_underrack")
	public static final Block block = null;
	public CosmicUnderrackBlock(SkizzikModElements instance) {
		super(instance, 296);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(CosmicVerseTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.EARTH).sound(SoundType.NETHERRACK).hardnessAndResistance(0.8f, 0.8f).setLightLevel(s -> 0)
					.harvestLevel(0).harvestTool(ToolType.PICKAXE));
			setRegistryName("cosmic_underrack");
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.PURPLE;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
