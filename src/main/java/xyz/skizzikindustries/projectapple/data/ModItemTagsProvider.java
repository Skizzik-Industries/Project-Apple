package xyz.skizzikindustries.projectapple.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.ModItems;
import xyz.skizzikindustries.projectapple.init.ModTags;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTag, ExistingFileHelper helper) {
        super(generator, blockTag, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Items.ALL_GEMS).add(ModItems.RAINBOW_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.BLACK_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.BLUE_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.BROWN_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.YELLOW_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.ORANGE_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.GREEN_GEM.get());
        tag(ModTags.Items.ALL_GEMS).add(ModItems.PINK_GEM.get());

        /* copy(ModTags.Blocks.ORES_SILVER, ModTags.Items.ORES_SILVER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_SILVER, ModTags.Items.STORAGE_BLOCKS_SILVER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        tag(ModTags.Items.INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_SILVER); */
    }
}
