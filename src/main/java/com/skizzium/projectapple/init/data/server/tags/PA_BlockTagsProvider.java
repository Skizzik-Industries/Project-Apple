package com.skizzium.projectapple.init.data.server.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PA_BlockTagsProvider extends BlockTagsProvider {
    public PA_BlockTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS).add(PA_Blocks.COMMAND_BLOCK.get(), PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get(), PA_Blocks.BROKEN_COMMAND_BLOCK.get());

        tag(PA_Tags.Blocks.SKIZZIK_HEADS).add(PA_Blocks.SMALL_SKIZZIK_HEAD.get(), PA_Blocks.SMALL_SKIZZIK_WALL_HEAD.get(), PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SMALL_SKIZZIK_WALL_HEAD_WITH_GEMS.get(), PA_Blocks.SKIZZIK_HEAD.get(), PA_Blocks.SKIZZIK_WALL_HEAD.get(), PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get());
        tag(PA_Tags.Blocks.FRIENDLY_SKIZZIK_HEADS).add(PA_Blocks.SMALL_FRIENDLY_SKIZZIK_HEAD.get(), PA_Blocks.SMALL_FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.FRIENDLY_SKIZZIK_HEAD.get(), PA_Blocks.FRIENDLY_SKIZZIK_WALL_HEAD.get(), PA_Blocks.FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.FRIENDLY_SKIZZIK_WALL_HEAD_WITH_GEMS.get());

        tag(PA_Tags.Blocks.DRAGON_IMMUNE).add(PA_Blocks.SKIZZIK_LOOT_BAG.get()).addTag(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS);;
        tag(PA_Tags.Blocks.WITHER_IMMUNE).add(PA_Blocks.SKIZZIK_FLESH_BLOCK.get()).addTag(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS);

        tag(PA_Tags.Blocks.RAINBOW_SWORD_IMMUNE).add(Blocks.SPAWNER, Blocks.STRUCTURE_VOID, Blocks.DRAGON_EGG, PA_Blocks.RAINBOW_GEM_BLOCK.get(), PA_Blocks.SKIZZIK_LOOT_BAG.get(), PA_Blocks.RAINBOW_ORE.get()).addTag(PA_Tags.Blocks.WITHER_IMMUNE);
        tag(PA_Tags.Blocks.CORRUPTION_IMMUNE).add(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR, PA_Blocks.CORRUPTED_BLOCK.get(), PA_Blocks.SKIZZIK_LOOT_BAG.get()).addTag(PA_Tags.Blocks.RAINBOW_SWORD_IMMUNE);

        tag(PA_Tags.Blocks.BEACON_BASE_BLOCKS).add(PA_Blocks.RAINBOW_GEM_BLOCK.get());

        tag(PA_Tags.Blocks.SKIZZIK_BASE_GEM_BLOCKS).add(PA_Blocks.BLACK_GEM_BLOCK.get(), PA_Blocks.BLUE_GEM_BLOCK.get(), PA_Blocks.BROWN_GEM_BLOCK.get(), PA_Blocks.YELLOW_GEM_BLOCK.get(), PA_Blocks.ORANGE_GEM_BLOCK.get(), PA_Blocks.GREEN_GEM_BLOCK.get(), PA_Blocks.PINK_GEM_BLOCK.get());
        tag(PA_Tags.Blocks.SKIZZIK_ALL_GEM_BLOCKS).add(PA_Blocks.RAINBOW_GEM_BLOCK.get()).addTag(PA_Tags.Blocks.SKIZZIK_BASE_GEM_BLOCKS);

        tag(PA_Tags.Blocks.MINEABLE_WITH_PICKAXE).add(PA_Blocks.COMMAND_BLOCK.get(), PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get(), PA_Blocks.BROKEN_COMMAND_BLOCK.get(), PA_Blocks.CORRUPTED_BLOCK.get(), PA_Blocks.SKIZZIE_STATUE.get(), PA_Blocks.RAINBOW_ORE.get()).addTag(PA_Tags.Blocks.SKIZZIK_ALL_GEM_BLOCKS);
        tag(PA_Tags.Blocks.MINEABLE_WITH_HOE).add(PA_Blocks.SKIZZIK_FLESH_BLOCK.get());

        tag(PA_Tags.Blocks.NEEDS_IRON_TOOL).add(PA_Blocks.BROKEN_COMMAND_BLOCK.get()).addTag(PA_Tags.Blocks.SKIZZIK_BASE_GEM_BLOCKS);
        tag(PA_Tags.Blocks.NEEDS_DIAMOND_TOOL).add(PA_Blocks.COMMAND_BLOCK.get(), PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get(), PA_Blocks.RAINBOW_ORE.get(), PA_Blocks.RAINBOW_GEM_BLOCK.get());

        tag(PA_Tags.Blocks.BEACON_BASE_BLOCKS).add(PA_Blocks.RAINBOW_GEM_BLOCK.get());
    }
}
