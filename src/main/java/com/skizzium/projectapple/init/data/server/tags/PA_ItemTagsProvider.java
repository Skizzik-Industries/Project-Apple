package com.skizzium.projectapple.init.data.server.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PA_ItemTagsProvider extends ItemTagsProvider {
    public PA_ItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTag, ExistingFileHelper helper) {
        super(generator, blockTag, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        copy(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS, PA_Tags.Items.SKIZZIK_COMMAND_BLOCKS);
        copy(PA_Tags.Blocks.BEACON_BASE_BLOCKS, PA_Tags.Items.BEACON_BASE_BLOCKS);

        tag(PA_Tags.Items.CURIOS_BACK).add(PA_Blocks.SKIZZIK_LOOT_BAG.get().asItem());
        
        tag(PA_Tags.Items.SKIZZIK_HEADS).add(PA_Items.SMALL_SKIZZIK_HEAD.get(), PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(), PA_Items.SKIZZIK_HEAD.get(), PA_Items.SKIZZIK_HEAD_WITH_GEMS.get());
        tag(PA_Tags.Items.FRIENDLY_SKIZZIK_RIBS).add(PA_Items.FRIENDLY_SKIZZIK_BOTTOM_RIB.get(), PA_Items.FRIENDLY_SKIZZIK_RIB.get(), PA_Items.FRIENDLY_SKIZZIK_BIG_RIB.get());

        tag(PA_Tags.Items.BEACON_PAYMENT_ITEMS).add(PA_Items.RAINBOW_GEM.get());
        tag(PA_Tags.Items.SKIZZIK_BASE_GEMS).add(PA_Items.BLACK_GEM.get(), PA_Items.BLUE_GEM.get(), PA_Items.BROWN_GEM.get(), PA_Items.YELLOW_GEM.get(), PA_Items.ORANGE_GEM.get(), PA_Items.GREEN_GEM.get(), PA_Items.PINK_GEM.get());
        tag(PA_Tags.Items.SKIZZIK_ALL_GEMS).add(PA_Items.RAINBOW_GEM.get()).addTag(PA_Tags.Items.SKIZZIK_BASE_GEMS);
    }
}
