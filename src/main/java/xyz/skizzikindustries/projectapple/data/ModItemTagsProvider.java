package xyz.skizzikindustries.projectapple.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.ModBlocks;
import xyz.skizzikindustries.projectapple.init.ModItems;
import xyz.skizzikindustries.projectapple.init.ModTags;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTag, ExistingFileHelper helper) {
        super(generator, blockTag, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.LOGS, ModTags.Items.LOGS);
        copy(ModTags.Blocks.LOGS_THAT_BURN, ModTags.Items.LOGS_THAT_BURN);

        copy(ModTags.Blocks.CANDY_LOGS, ModTags.Items.CANDY_LOGS);

        copy(ModTags.Blocks.BEACON_BASE_BLOCKS, ModTags.Items.BEACON_BASE_BLOCKS);
        tag(ModTags.Items.BEACON_PAYMENT_ITEMS).add(ModItems.RAINBOW_GEM.get());

        tag(ModTags.Items.BASE_GEMS).add(ModItems.BLACK_GEM.get());
        tag(ModTags.Items.BASE_GEMS).add(ModItems.BLUE_GEM.get());
        tag(ModTags.Items.BASE_GEMS).add(ModItems.BROWN_GEM.get());
        tag(ModTags.Items.BASE_GEMS).add(ModItems.YELLOW_GEM.get());
        tag(ModTags.Items.BASE_GEMS).add(ModItems.ORANGE_GEM.get());
        tag(ModTags.Items.BASE_GEMS).add(ModItems.GREEN_GEM.get());
        tag(ModTags.Items.BASE_GEMS).add(ModItems.PINK_GEM.get());

        tag(ModTags.Items.ALL_GEMS).add(ModItems.RAINBOW_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.BLACK_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.BLUE_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.BROWN_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.YELLOW_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.ORANGE_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.GREEN_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.PINK_GEM.get());
    }
}
