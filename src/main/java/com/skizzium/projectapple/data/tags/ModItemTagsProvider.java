package com.skizzium.projectapple.data.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Tags;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTag, ExistingFileHelper helper) {
        super(generator, blockTag, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        copy(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS, PA_Tags.Items.SKIZZIK_COMMAND_BLOCKS);
        copy(PA_Tags.Blocks.SKIZZIK_CHOCOLATE_BLOCKS, PA_Tags.Items.SKIZZIK_CHOCOLATE_BLOCKS);

        copy(PA_Tags.Blocks.WOODEN_PRESSURE_PLATES, PA_Tags.Items.WOODEN_PRESSURE_PLATES);
        copy(PA_Tags.Blocks.BUTTONS, PA_Tags.Items.BUTTONS);
        copy(PA_Tags.Blocks.WOODEN_BUTTONS, PA_Tags.Items.WOODEN_BUTTONS);

        copy(PA_Tags.Blocks.PLANKS, PA_Tags.Items.PLANKS);

        copy(PA_Tags.Blocks.SLABS, PA_Tags.Items.SLABS);
        copy(PA_Tags.Blocks.WOODEN_SLABS, PA_Tags.Items.WOODEN_SLABS);

        copy(PA_Tags.Blocks.STAIRS, PA_Tags.Items.STAIRS);
        copy(PA_Tags.Blocks.WOODEN_STAIRS, PA_Tags.Items.WOODEN_STAIRS);

        copy(PA_Tags.Blocks.FENCES, PA_Tags.Items.FENCES);
        copy(PA_Tags.Blocks.WOODEN_FENCES, PA_Tags.Items.WOODEN_FENCES);

        copy(PA_Tags.Blocks.TRAPDOORS, PA_Tags.Items.TRAPDOORS);
        copy(PA_Tags.Blocks.WOODEN_TRAPDOORS, PA_Tags.Items.WOODEN_TRAPDOORS);

        copy(PA_Tags.Blocks.DOORS, PA_Tags.Items.DOORS);
        copy(PA_Tags.Blocks.WOODEN_DOORS, PA_Tags.Items.WOODEN_DOORS);

        copy(PA_Tags.Blocks.LEAVES, PA_Tags.Items.LEAVES);
        copy(PA_Tags.Blocks.LOGS, PA_Tags.Items.LOGS);
        copy(PA_Tags.Blocks.LOGS_THAT_BURN, PA_Tags.Items.LOGS_THAT_BURN);

        copy(PA_Tags.Blocks.CANDY_LOGS, PA_Tags.Items.CANDY_LOGS);

        copy(PA_Tags.Blocks.BEACON_BASE_BLOCKS, PA_Tags.Items.BEACON_BASE_BLOCKS);

        //tag(PA_Tags.Items.SKIZZIK_HEADS).add(PA_Items.SMALL_SKIZZIK_HEAD.get(), PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(), PA_Items.SKIZZIK_HEAD.get(), PA_Items.SKIZZIK_HEAD_WITH_GEMS.get());

        tag(PA_Tags.Items.SKIZZIK_PANCAKES).add(PA_Items.PANCAKES.get(), PA_Items.SYRUP_PANCAKES.get());
        tag(PA_Tags.Items.SKIZZIK_CHOCOLATE).add(PA_Items.WHITE_CHOCOLATE_BAR.get(), PA_Items.CHOCOLATE_BAR.get(), PA_Items.DARK_CHOCOLATE_BAR.get());

        //tag(PA_Tags.Items.SIGNS).add(PA_Items.CANDY_SIGN.get());

        tag(PA_Tags.Items.BEACON_PAYMENT_ITEMS).add(PA_Items.CANDIANITE_INGOT.get(), PA_Items.RAINBOW_GEM.get());
        tag(PA_Tags.Items.SKIZZIK_BASE_GEMS).add(PA_Items.BLACK_GEM.get(), PA_Items.BLUE_GEM.get(), PA_Items.BROWN_GEM.get(), PA_Items.YELLOW_GEM.get(), PA_Items.ORANGE_GEM.get(), PA_Items.GREEN_GEM.get(), PA_Items.PINK_GEM.get());
        tag(PA_Tags.Items.SKIZZIK_ALL_GEMS).add(PA_Items.RAINBOW_GEM.get()).addTag(PA_Tags.Items.SKIZZIK_BASE_GEMS);
    }
}
