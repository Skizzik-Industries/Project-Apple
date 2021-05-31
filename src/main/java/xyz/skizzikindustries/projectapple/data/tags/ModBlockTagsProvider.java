package xyz.skizzikindustries.projectapple.data.tags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.ModTags;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Blocks.PLANKS).add(ModBlocks.CANDY_PLANKS.get());

        tag(ModTags.Blocks.FENCES).add(ModBlocks.CANDY_FENCE.get());
        tag(ModTags.Blocks.WOODEN_FENCES).add(ModBlocks.CANDY_FENCE.get());

        tag(ModTags.Blocks.LOGS).add(ModBlocks.CANDY_LOG.get());
        tag(ModTags.Blocks.LOGS_THAT_BURN).add(ModBlocks.CANDY_LOG.get());

        tag(ModTags.Blocks.LOGS).add(ModBlocks.STRIPPED_CANDY_LOG.get());
        tag(ModTags.Blocks.LOGS_THAT_BURN).add(ModBlocks.STRIPPED_CANDY_LOG.get());

        tag(ModTags.Blocks.LOGS).add(ModBlocks.CANDY_WOOD.get());
        tag(ModTags.Blocks.LOGS_THAT_BURN).add(ModBlocks.CANDY_WOOD.get());

        tag(ModTags.Blocks.LOGS).add(ModBlocks.STRIPPED_CANDY_WOOD.get());
        tag(ModTags.Blocks.LOGS_THAT_BURN).add(ModBlocks.STRIPPED_CANDY_WOOD.get());

        tag(ModTags.Blocks.CANDY_LOGS).add(ModBlocks.CANDY_LOG.get());
        tag(ModTags.Blocks.CANDY_LOGS).add(ModBlocks.STRIPPED_CANDY_LOG.get());
        tag(ModTags.Blocks.CANDY_LOGS).add(ModBlocks.CANDY_WOOD.get());
        tag(ModTags.Blocks.CANDY_LOGS).add(ModBlocks.STRIPPED_CANDY_WOOD.get());

        tag(ModTags.Blocks.BEACON_BASE_BLOCKS).add(ModBlocks.RAINBOW_GEM_BLOCK.get());
    }
}
