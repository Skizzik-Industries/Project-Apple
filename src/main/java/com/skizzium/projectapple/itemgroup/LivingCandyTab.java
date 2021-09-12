package com.skizzium.projectapple.itemgroup;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;

public class LivingCandyTab extends CreativeModeTab {
    public LivingCandyTab(String label) {
        super(label);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent(ProjectApple.getThemedDescriptionId("itemGroup.living_candy_tab"));
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        PA_Items.CANDY_CANE.get().fillItemCategory(this, items);
        PA_Items.MAPLE_SYRUP_BUCKET.get().fillItemCategory(this, items);

        PA_Items.PANCAKES.get().fillItemCategory(this, items);
        PA_Items.SYRUP_PANCAKES.get().fillItemCategory(this, items);

        PA_Items.WAFFLE.get().fillItemCategory(this, items);
        PA_Blocks.WAFFLE_BLOCK.get().fillItemCategory(this, items);

        PA_Items.CHOCOLATE_BAR_WRAP.get().fillItemCategory(this, items);
        PA_Items.WHITE_CHOCOLATE_BAR.get().fillItemCategory(this, items);
        PA_Items.CHOCOLATE_BAR.get().fillItemCategory(this, items);
        PA_Items.DARK_CHOCOLATE_BAR.get().fillItemCategory(this, items);
        PA_Blocks.WHITE_CHOCOLATE_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.CHOCOLATE_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.DARK_CHOCOLATE_BLOCK.get().fillItemCategory(this, items);

        PA_Blocks.CANDY_BUTTON.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_PRESSURE_PLATE.get().fillItemCategory(this, items);
        PA_Items.CANDY_SIGN.get().fillItemCategory(this, items);

        PA_Blocks.CANDY_FENCE.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_FENCE_GATE.get().fillItemCategory(this, items);

        PA_Blocks.CANDY_TRAPDOOR.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_DOOR.get().fillItemCategory(this, items);

        PA_Blocks.CANDY_SLAB.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_STAIRS.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_PLANKS.get().fillItemCategory(this, items);

        PA_Blocks.CANDY_LOG.get().fillItemCategory(this, items);
        PA_Blocks.STRIPPED_CANDY_LOG.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_WOOD.get().fillItemCategory(this, items);
        PA_Blocks.STRIPPED_CANDY_WOOD.get().fillItemCategory(this, items);

        PA_Blocks.CANDY_SAPLING.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_LEAVES.get().fillItemCategory(this, items);

        PA_Items.CANDY_PIG_SPAWN_EGG.get().fillItemCategory(this, items);
        PA_Blocks.CANDY_NYLIUM.get().fillItemCategory(this, items);
        PA_Blocks.CANDYRACK.get().fillItemCategory(this, items);

        PA_Items.CANDIANITE_NUGGET.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_INGOT.get().fillItemCategory(this, items);
        PA_Blocks.CANDIANITE_ORE.get().fillItemCategory(this, items);

        PA_Items.CANDIANITE_SWORD.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_PICKAXE.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_AXE.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_SHOVEL.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_HOE.get().fillItemCategory(this, items);

        PA_Items.CANDIANITE_HORSE_ARMOR.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_HELMET.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_CHESTPLATE.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_LEGGINGS.get().fillItemCategory(this, items);
        PA_Items.CANDIANITE_BOOTS.get().fillItemCategory(this, items);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(PA_Items.CANDY_CANE.get());
    }
}
