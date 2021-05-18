package xyz.skizzikindustries.projectapple.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.ModBlocks;
import xyz.skizzikindustries.projectapple.init.ModTags;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Blocks.LOGS).add(ModBlocks.CANDY_LOG.get());
        tag(ModTags.Blocks.LOGS_THAT_BURN).add(ModBlocks.CANDY_LOG.get());
        tag(ModTags.Blocks.CANDY_LOGS).add(ModBlocks.CANDY_LOG.get());
    }
}
