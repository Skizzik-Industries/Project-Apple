package xyz.skizzikindustries.projectapple.data.tags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.item.ModItems;
import xyz.skizzikindustries.projectapple.init.ModTags;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTag, ExistingFileHelper helper) {
        super(generator, blockTag, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.SKIZZIK_COMMAND_BLOCKS, ModTags.Items.SKIZZIK_COMMAND_BLOCKS);

        tag(ModTags.Items.SKIZZIK_PANCAKES).add(ModItems.PANCAKES.get());
        tag(ModTags.Items.SKIZZIK_PANCAKES).add(ModItems.SYRUP_PANCAKES.get());

        copy(ModTags.Blocks.SKIZZIK_CHOCOLATE_BLOCKS, ModTags.Items.SKIZZIK_CHOCOLATE_BLOCKS);
        tag(ModTags.Items.SKIZZIK_CHOCOLATE).add(ModItems.WHITE_CHOCOLATE_BAR.get());
        tag(ModTags.Items.SKIZZIK_CHOCOLATE).add(ModItems.CHOCOLATE_BAR.get());
        tag(ModTags.Items.SKIZZIK_CHOCOLATE).add(ModItems.DARK_CHOCOLATE_BAR.get());

        copy(ModTags.Blocks.WOODEN_PRESSURE_PLATES, ModTags.Items.WOODEN_PRESSURE_PLATES);
        copy(ModTags.Blocks.BUTTONS, ModTags.Items.BUTTONS);
        copy(ModTags.Blocks.WOODEN_BUTTONS, ModTags.Items.WOODEN_BUTTONS);

        copy(ModTags.Blocks.PLANKS, ModTags.Items.PLANKS);
        copy(ModTags.Blocks.SLABS, ModTags.Items.SLABS);
        copy(ModTags.Blocks.WOODEN_SLABS, ModTags.Items.WOODEN_SLABS);
        copy(ModTags.Blocks.STAIRS, ModTags.Items.STAIRS);
        copy(ModTags.Blocks.WOODEN_STAIRS, ModTags.Items.WOODEN_STAIRS);

        copy(ModTags.Blocks.FENCES, ModTags.Items.FENCES);
        copy(ModTags.Blocks.WOODEN_FENCES, ModTags.Items.WOODEN_FENCES);

        copy(ModTags.Blocks.TRAPDOORS, ModTags.Items.TRAPDOORS);
        copy(ModTags.Blocks.WOODEN_TRAPDOORS, ModTags.Items.WOODEN_TRAPDOORS);
        copy(ModTags.Blocks.DOORS, ModTags.Items.DOORS);
        copy(ModTags.Blocks.WOODEN_DOORS, ModTags.Items.WOODEN_DOORS);

        copy(ModTags.Blocks.LEAVES, ModTags.Items.LEAVES);
        copy(ModTags.Blocks.LOGS, ModTags.Items.LOGS);
        copy(ModTags.Blocks.LOGS_THAT_BURN, ModTags.Items.LOGS_THAT_BURN);

        copy(ModTags.Blocks.CANDY_LOGS, ModTags.Items.CANDY_LOGS);

        copy(ModTags.Blocks.BEACON_BASE_BLOCKS, ModTags.Items.BEACON_BASE_BLOCKS);
        tag(ModTags.Items.BEACON_PAYMENT_ITEMS).add(ModItems.RAINBOW_GEM.get());
        tag(ModTags.Items.BEACON_PAYMENT_ITEMS).add(ModItems.CANDIANITE_INGOT.get());

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
