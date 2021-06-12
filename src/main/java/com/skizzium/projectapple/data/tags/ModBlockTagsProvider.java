package com.skizzium.projectapple.data.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.ModTags;
import com.skizzium.projectapple.init.block.ModBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Blocks.SKIZZIK_COMMAND_BLOCKS).add(ModBlocks.COMMAND_BLOCK.get());
        tag(ModTags.Blocks.DRAGON_IMMUNE).add(ModBlocks.COMMAND_BLOCK.get());
        tag(ModTags.Blocks.WITHER_IMMUNE).add(ModBlocks.COMMAND_BLOCK.get());

        tag(ModTags.Blocks.SKIZZIK_COMMAND_BLOCKS).add(ModBlocks.DEACTIVATED_COMMAND_BLOCK.get());
        tag(ModTags.Blocks.DRAGON_IMMUNE).add(ModBlocks.DEACTIVATED_COMMAND_BLOCK.get());
        tag(ModTags.Blocks.WITHER_IMMUNE).add(ModBlocks.DEACTIVATED_COMMAND_BLOCK.get());

        tag(ModTags.Blocks.SKIZZIK_COMMAND_BLOCKS).add(ModBlocks.BROKEN_COMMAND_BLOCK.get());
        tag(ModTags.Blocks.DRAGON_IMMUNE).add(ModBlocks.BROKEN_COMMAND_BLOCK.get());
        tag(ModTags.Blocks.WITHER_IMMUNE).add(ModBlocks.BROKEN_COMMAND_BLOCK.get());

        tag(ModTags.Blocks.DRAGON_IMMUNE).add(ModBlocks.SKIZZIK_LOOT_BAG.get());
        tag(ModTags.Blocks.WITHER_IMMUNE).add(ModBlocks.SKIZZIK_FLESH_BLOCK.get());

        tag(ModTags.Blocks.SKIZZIK_CHOCOLATE_BLOCKS).add(ModBlocks.WHITE_CHOCOLATE_BLOCK.get());
        tag(ModTags.Blocks.SKIZZIK_CHOCOLATE_BLOCKS).add(ModBlocks.CHOCOLATE_BLOCK.get());
        tag(ModTags.Blocks.SKIZZIK_CHOCOLATE_BLOCKS).add(ModBlocks.DARK_CHOCOLATE_BLOCK.get());

        tag(ModTags.Blocks.PRESSURE_PLATES).add(ModBlocks.CANDY_PRESSURE_PLATE.get());
        tag(ModTags.Blocks.WALL_POST_OVERRIDE).add(ModBlocks.CANDY_PRESSURE_PLATE.get());
        tag(ModTags.Blocks.WOODEN_PRESSURE_PLATES).add(ModBlocks.CANDY_PRESSURE_PLATE.get());

        tag(ModTags.Blocks.BUTTONS).add(ModBlocks.CANDY_BUTTON.get());
        tag(ModTags.Blocks.WOODEN_BUTTONS).add(ModBlocks.CANDY_BUTTON.get());

        tag(ModTags.Blocks.PLANKS).add(ModBlocks.CANDY_PLANKS.get());
        tag(ModTags.Blocks.SLABS).add(ModBlocks.CANDY_SLAB.get());
        tag(ModTags.Blocks.WOODEN_SLABS).add(ModBlocks.CANDY_SLAB.get());
        tag(ModTags.Blocks.STAIRS).add(ModBlocks.CANDY_STAIRS.get());
        tag(ModTags.Blocks.WOODEN_STAIRS).add(ModBlocks.CANDY_STAIRS.get());

        tag(ModTags.Blocks.FENCES).add(ModBlocks.CANDY_FENCE.get());
        tag(ModTags.Blocks.WOODEN_FENCES).add(ModBlocks.CANDY_FENCE.get());
        tag(ModTags.Blocks.FENCE_GATES).add(ModBlocks.CANDY_FENCE_GATE.get());
        tag(ModTags.Blocks.UNSTABLE_BOTTOM_CENTER).add(ModBlocks.CANDY_FENCE_GATE.get());

        tag(ModTags.Blocks.TRAPDOORS).add(ModBlocks.CANDY_TRAPDOOR.get());
        tag(ModTags.Blocks.WOODEN_TRAPDOORS).add(ModBlocks.CANDY_TRAPDOOR.get());
        tag(ModTags.Blocks.DOORS).add(ModBlocks.CANDY_DOOR.get());
        tag(ModTags.Blocks.WOODEN_DOORS).add(ModBlocks.CANDY_DOOR.get());

        tag(ModTags.Blocks.LEAVES).add(ModBlocks.CANDY_LEAVES.get());
        tag(ModTags.Blocks.ENDERMAN_HOLDABLE).add(ModBlocks.CANDY_NYLIUM.get());
        tag(ModTags.Blocks.ENDERMAN_HOLDABLE).add(ModBlocks.CANDYRACK.get());
        tag(ModTags.Blocks.VALID_SPAWN).add(ModBlocks.CANDY_NYLIUM.get());

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
