package xyz.skizzikindustries.projectapple.tab;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.item.ModItems;

public class MainSkizzikTab extends ItemGroup {
    public MainSkizzikTab(String label) {
        super(label);
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        ModBlocks.COMMAND_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.DEACTIVATED_COMMAND_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.BROKEN_COMMAND_BLOCK.get().fillItemCategory(this, items);

        ModItems.SKIZZIK_FLESH_CAP.get().fillItemCategory(this, items);
        ModItems.SKIZZIK_FLESH_TUNIC.get().fillItemCategory(this, items);
        ModItems.SKIZZIK_FLESH_PANTS.get().fillItemCategory(this, items);
        ModItems.SKIZZIK_FLESH_BOOTS.get().fillItemCategory(this, items);

        ModItems.SKIZZIK_BONE.get().fillItemCategory(this, items);
        ModItems.RAW_SKIZZIK_FLESH.get().fillItemCategory(this, items);
        ModItems.SKIZZIK_FLESH.get().fillItemCategory(this, items);
        ModBlocks.SKIZZIK_FLESH_BLOCK.get().fillItemCategory(this, items);

        ModItems.PLATINUM_NUGGET.get().fillItemCategory(this, items);
        ModItems.PLATINUM_INGOT.get().fillItemCategory(this, items);

        ModBlocks.SKIZZIK_LOOT_BAG.get().fillItemCategory(this, items);
        ModBlocks.SKIZZIE_STATUE.get().fillItemCategory(this, items);
        ModBlocks.CORRUPTED_BLOCK.get().fillItemCategory(this, items);

        Blocks.LIGHT_GRAY_STAINED_GLASS_PANE.fillItemCategory(this, items);
        Blocks.LIGHT_GRAY_STAINED_GLASS_PANE.fillItemCategory(this, items);

        ModItems.RAINBOW_GEM.get().fillItemCategory(this, items);
        ModItems.BLACK_GEM.get().fillItemCategory(this, items);
        ModItems.BLUE_GEM.get().fillItemCategory(this, items);
        ModItems.BROWN_GEM.get().fillItemCategory(this, items);
        ModItems.YELLOW_GEM.get().fillItemCategory(this, items);
        ModItems.ORANGE_GEM.get().fillItemCategory(this, items);
        ModItems.GREEN_GEM.get().fillItemCategory(this, items);
        ModItems.PINK_GEM.get().fillItemCategory(this, items);

        ModBlocks.RAINBOW_ORE.get().fillItemCategory(this, items);

        ModBlocks.RAINBOW_GEM_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.BLACK_GEM_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.BLUE_GEM_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.BROWN_GEM_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.YELLOW_GEM_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.ORANGE_GEM_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.GREEN_GEM_BLOCK.get().fillItemCategory(this, items);
        ModBlocks.PINK_GEM_BLOCK.get().fillItemCategory(this, items);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.COMMAND_BLOCK.get());
    }
}
