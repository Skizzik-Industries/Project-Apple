package xyz.skizzikindustries.projectapple.tab;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.item.ModItems;

public class LivingCandyTab extends ItemGroup {
    public LivingCandyTab(String label) {
        super(label);
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        ModItems.CANDY_CANE.get().fillItemCategory(this, items);
        ModItems.MAPLE_SYRUP_BUCKET.get().fillItemCategory(this, items);

        ModItems.PANCAKES.get().fillItemCategory(this, items);
        ModItems.SYRUP_PANCAKES.get().fillItemCategory(this, items);

        ModItems.WAFFLE.get().fillItemCategory(this, items);
        ModBlocks.WAFFLE_BLOCK.get().fillItemCategory(this, items);

        ModItems.CHOCOLATE_BAR_WRAP.get().fillItemCategory(this, items);
        ModItems.WHITE_CHOCOLATE_BAR.get().fillItemCategory(this, items);
        ModItems.CHOCOLATE_BAR.get().fillItemCategory(this, items);
        ModItems.DARK_CHOCOLATE_BAR.get().fillItemCategory(this, items);
        ModBlocks.WHITE_CHOCOLATE_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.CHOCOLATE_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.DARK_CHOCOLATE_BLOCK.get().fillItemCategory(this, items);

        ModBlocks.CANDY_BUTTON.get().fillItemCategory(this, items);
        ModBlocks.CANDY_PRESSURE_PLATE.get().fillItemCategory(this, items);

        ModBlocks.CANDY_FENCE.get().fillItemCategory(this, items);
        ModBlocks.CANDY_FENCE_GATE.get().fillItemCategory(this, items);

        ModBlocks.CANDY_TRAPDOOR.get().fillItemCategory(this, items);
        ModBlocks.CANDY_DOOR.get().fillItemCategory(this, items);

        ModBlocks.CANDY_SLAB.get().fillItemCategory(this, items);
        ModBlocks.CANDY_STAIRS.get().fillItemCategory(this, items);
        ModBlocks.CANDY_PLANKS.get().fillItemCategory(this, items);

        ModBlocks.CANDY_LOG.get().fillItemCategory(this, items);
        ModBlocks.STRIPPED_CANDY_LOG.get().fillItemCategory(this, items);
        ModBlocks.CANDY_WOOD.get().fillItemCategory(this, items);
        ModBlocks.STRIPPED_CANDY_WOOD.get().fillItemCategory(this, items);

        ModBlocks.CANDY_LEAVES.get().fillItemCategory(this, items);
        ModBlocks.CANDY_NYLIUM.get().fillItemCategory(this, items);
        ModBlocks.CANDYRACK.get().fillItemCategory(this, items);

        ModItems.CANDIANITE_NUGGET.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_INGOT.get().fillItemCategory(this, items);
        ModBlocks.CANDIANITE_ORE.get().fillItemCategory(this, items);

        ModItems.CANDIANITE_SWORD.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_PICKAXE.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_AXE.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_SHOVEL.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_HOE.get().fillItemCategory(this, items);

        ModItems.CANDIANITE_HORSE_ARMOR.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_HELMET.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_CHESTPLATE.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_LEGGINGS.get().fillItemCategory(this, items);
        ModItems.CANDIANITE_BOOTS.get().fillItemCategory(this, items);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.CANDY_CANE.get());
    }
}
