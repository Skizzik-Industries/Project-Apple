package com.skizzium.projectapple.itemgroup;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;

public class MainSkizzikTab extends CreativeModeTab {
    public MainSkizzikTab(String label) {
        super(label);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent(ProjectApple.getThemedDescriptionId("itemGroup.main_skizzik_tab"));
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items) {
        PA_Blocks.COMMAND_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.BROKEN_COMMAND_BLOCK.get().fillItemCategory(this, items);

        PA_Items.SKIZZIK_FLESH_CAP.get().fillItemCategory(this, items);
        PA_Items.SKIZZIK_FLESH_TUNIC.get().fillItemCategory(this, items);
        PA_Items.SKIZZIK_FLESH_PANTS.get().fillItemCategory(this, items);
        PA_Items.SKIZZIK_FLESH_BOOTS.get().fillItemCategory(this, items);

        PA_Items.SMALL_SKIZZIK_HEAD.get().fillItemCategory(this, items);
        PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get().fillItemCategory(this, items);
        PA_Items.SKIZZIK_HEAD.get().fillItemCategory(this, items);
        PA_Items.SKIZZIK_HEAD_WITH_GEMS.get().fillItemCategory(this, items);

        PA_Items.MUSIC_DISC_SKIZZIK.get().fillItemCategory(this, items);
        PA_Items.MUSIC_DISC_SPOOKZIK.get().fillItemCategory(this, items);

        PA_Items.SKIZZIK_BONE.get().fillItemCategory(this, items);
        PA_Items.RAW_SKIZZIK_FLESH.get().fillItemCategory(this, items);
        PA_Items.SKIZZIK_FLESH.get().fillItemCategory(this, items);
        PA_Blocks.SKIZZIK_FLESH_BLOCK.get().fillItemCategory(this, items);

        PA_Items.FRIENDLY_SKIZZIE_SPAWN_EGG.get().fillItemCategory(this, items);
        PA_Items.FRIENDLY_WITCH_SKIZZIE_SPAWN_EGG.get().fillItemCategory(this, items);

        PA_Items.SKIZZIE_SPAWN_EGG.get().fillItemCategory(this, items);
        PA_Items.KABOOM_SKIZZIE_SPAWN_EGG.get().fillItemCategory(this, items);
        PA_Items.WITCH_SKIZZIE_SPAWN_EGG.get().fillItemCategory(this, items);
        PA_Items.CORRUPTED_SKIZZIE_SPAWN_EGG.get().fillItemCategory(this, items);

        PA_Items.SKIZZO_SPAWN_EGG.get().fillItemCategory(this, items);

        PA_Blocks.SKIZZIE_STATUE.get().fillItemCategory(this, items);
        PA_Blocks.SPOOKZIE_STATUE.get().fillItemCategory(this, items);
        PA_Blocks.SKIZZIK_LOOT_BAG.get().fillItemCategory(this, items);

        PA_Items.PLATINUM_NUGGET.get().fillItemCategory(this, items);
        PA_Items.PLATINUM_INGOT.get().fillItemCategory(this, items);
        PA_Blocks.CORRUPTED_BLOCK.get().fillItemCategory(this, items);

        PA_Items.RAINBOW_SWORD.get().fillItemCategory(this, items);

        PA_Items.RAINBOW_GEM.get().fillItemCategory(this, items);
        PA_Items.BLACK_GEM.get().fillItemCategory(this, items);
        PA_Items.BLUE_GEM.get().fillItemCategory(this, items);
        PA_Items.BROWN_GEM.get().fillItemCategory(this, items);
        PA_Items.YELLOW_GEM.get().fillItemCategory(this, items);
        PA_Items.ORANGE_GEM.get().fillItemCategory(this, items);
        PA_Items.GREEN_GEM.get().fillItemCategory(this, items);
        PA_Items.PINK_GEM.get().fillItemCategory(this, items);

        PA_Blocks.RAINBOW_ORE.get().fillItemCategory(this, items);

        PA_Blocks.RAINBOW_GEM_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.BLACK_GEM_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.BLUE_GEM_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.BROWN_GEM_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.YELLOW_GEM_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.ORANGE_GEM_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.GREEN_GEM_BLOCK.get().fillItemCategory(this, items);
        PA_Blocks.PINK_GEM_BLOCK.get().fillItemCategory(this, items);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(PA_Blocks.SKIZZIK_HEAD.get());
    }
}
