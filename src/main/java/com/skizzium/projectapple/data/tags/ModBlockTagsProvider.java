package com.skizzium.projectapple.data.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Tags;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS).add(PA_Blocks.COMMAND_BLOCK.get(), PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get(), PA_Blocks.BROKEN_COMMAND_BLOCK.get());

        //tag(PA_Tags.Blocks.SKIZZIK_HEADS).add(PA_Blocks.SMALL_SKIZZIK_HEAD.get(), PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get());
        //tag(PA_Tags.Blocks.SKIZZIK_HEADS).add(PA_Blocks.SMALL_SKIZZIK_WALL_HEAD.get());
        //tag(PA_Tags.Blocks.SKIZZIK_HEADS).add(PA_Blocks.SMALL_SKIZZIK_WALL_HEAD_WITH_GEMS.get());
        //tag(PA_Tags.Blocks.SKIZZIK_HEADS).add(PA_Blocks.SKIZZIK_HEAD.get(), PA_Blocks.SKIZZIK_WALL_HEAD.get(), PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get());

        tag(PA_Tags.Blocks.DRAGON_IMMUNE).add(PA_Blocks.SKIZZIK_LOOT_BAG.get()).addTag(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS);;
        tag(PA_Tags.Blocks.WITHER_IMMUNE).add(PA_Blocks.SKIZZIK_FLESH_BLOCK.get()).addTag(PA_Tags.Blocks.SKIZZIK_COMMAND_BLOCKS);

        tag(PA_Tags.Blocks.RAINBOW_SWORD_IMMUNE).add(Blocks.SPAWNER, Blocks.STRUCTURE_VOID, Blocks.DRAGON_EGG, PA_Blocks.RAINBOW_GEM_BLOCK.get(), PA_Blocks.RAINBOW_ORE.get()/*, PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get()*/).addTag(PA_Tags.Blocks.WITHER_IMMUNE);
        tag(PA_Tags.Blocks.CORRUPTION_IMMUNE).add(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR, PA_Blocks.CORRUPTED_BLOCK.get()).addTag(PA_Tags.Blocks.RAINBOW_SWORD_IMMUNE);

        tag(PA_Tags.Blocks.SKIZZIK_CHOCOLATE_BLOCKS).add(PA_Blocks.WHITE_CHOCOLATE_BLOCK.get(), PA_Blocks.CHOCOLATE_BLOCK.get(), PA_Blocks.DARK_CHOCOLATE_BLOCK.get());

        tag(PA_Tags.Blocks.PRESSURE_PLATES).add(PA_Blocks.CANDY_PRESSURE_PLATE.get());
        tag(PA_Tags.Blocks.WOODEN_PRESSURE_PLATES).add(PA_Blocks.CANDY_PRESSURE_PLATE.get());

        tag(PA_Tags.Blocks.BUTTONS).add(PA_Blocks.CANDY_BUTTON.get());
        tag(PA_Tags.Blocks.WOODEN_BUTTONS).add(PA_Blocks.CANDY_BUTTON.get());

        tag(PA_Tags.Blocks.STANDING_SIGNS).add(PA_Blocks.CANDY_SIGN.get());
        tag(PA_Tags.Blocks.WALL_SIGNS).add(PA_Blocks.CANDY_WALL_SIGN.get());
        tag(PA_Tags.Blocks.SIGNS).add(PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get());

        tag(PA_Tags.Blocks.PLANKS).add(PA_Blocks.CANDY_PLANKS.get());

        tag(PA_Tags.Blocks.SLABS).add(PA_Blocks.CANDY_SLAB.get());
        tag(PA_Tags.Blocks.WOODEN_SLABS).add(PA_Blocks.CANDY_SLAB.get());

        tag(PA_Tags.Blocks.STAIRS).add(PA_Blocks.CANDY_STAIRS.get());
        tag(PA_Tags.Blocks.WOODEN_STAIRS).add(PA_Blocks.CANDY_STAIRS.get());

        tag(PA_Tags.Blocks.FENCES).add(PA_Blocks.CANDY_FENCE.get());
        tag(PA_Tags.Blocks.WOODEN_FENCES).add(PA_Blocks.CANDY_FENCE.get());
        tag(PA_Tags.Blocks.FENCE_GATES).add(PA_Blocks.CANDY_FENCE_GATE.get());
        tag(PA_Tags.Blocks.UNSTABLE_BOTTOM_CENTER).add(PA_Blocks.CANDY_FENCE_GATE.get());

        tag(PA_Tags.Blocks.TRAPDOORS).add(PA_Blocks.CANDY_TRAPDOOR.get());
        tag(PA_Tags.Blocks.WOODEN_TRAPDOORS).add(PA_Blocks.CANDY_TRAPDOOR.get());

        tag(PA_Tags.Blocks.DOORS).add(PA_Blocks.CANDY_DOOR.get());
        tag(PA_Tags.Blocks.WOODEN_DOORS).add(PA_Blocks.CANDY_DOOR.get());

        tag(PA_Tags.Blocks.LEAVES).add(PA_Blocks.CANDY_LEAVES.get());
        tag(PA_Tags.Blocks.ENDERMAN_HOLDABLE).add(PA_Blocks.CANDY_NYLIUM.get(), PA_Blocks.CANDYRACK.get());
        tag(PA_Tags.Blocks.VALID_SPAWN).add(PA_Blocks.CANDY_NYLIUM.get());

        tag(PA_Tags.Blocks.CANDY_LOGS).add(PA_Blocks.CANDY_LOG.get(), PA_Blocks.STRIPPED_CANDY_LOG.get(), PA_Blocks.CANDY_WOOD.get(), PA_Blocks.STRIPPED_CANDY_WOOD.get());
        tag(PA_Tags.Blocks.LOGS).add(PA_Blocks.CANDY_LOG.get(), PA_Blocks.STRIPPED_CANDY_LOG.get(), PA_Blocks.CANDY_WOOD.get(), PA_Blocks.STRIPPED_CANDY_WOOD.get());
        tag(PA_Tags.Blocks.LOGS_THAT_BURN).add(PA_Blocks.CANDY_LOG.get(), PA_Blocks.STRIPPED_CANDY_LOG.get(), PA_Blocks.CANDY_WOOD.get(), PA_Blocks.STRIPPED_CANDY_WOOD.get());

        tag(PA_Tags.Blocks.BEACON_BASE_BLOCKS).add(PA_Blocks.RAINBOW_GEM_BLOCK.get());

        tag(PA_Tags.Blocks.SKIZZIK_BASE_GEM_BLOCKS).add(PA_Blocks.BLACK_GEM_BLOCK.get(), PA_Blocks.BLUE_GEM_BLOCK.get(), PA_Blocks.BROWN_GEM_BLOCK.get(), PA_Blocks.YELLOW_GEM_BLOCK.get(), PA_Blocks.ORANGE_GEM_BLOCK.get(), PA_Blocks.GREEN_GEM_BLOCK.get(), PA_Blocks.PINK_GEM_BLOCK.get());
        tag(PA_Tags.Blocks.SKIZZIK_ALL_GEM_BLOCKS).add(PA_Blocks.RAINBOW_GEM_BLOCK.get()).addTag(PA_Tags.Blocks.SKIZZIK_BASE_GEM_BLOCKS);

        tag(PA_Tags.Blocks.MINEABLE_WITH_PICKAXE).add(PA_Blocks.COMMAND_BLOCK.get(), PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get(), PA_Blocks.BROKEN_COMMAND_BLOCK.get(), PA_Blocks.CORRUPTED_BLOCK.get(), PA_Blocks.SKIZZIE_STATUE.get(), PA_Blocks.WHITE_CHOCOLATE_BLOCK.get(), PA_Blocks.CHOCOLATE_BLOCK.get(), PA_Blocks.DARK_CHOCOLATE_BLOCK.get(), PA_Blocks.CANDIANITE_ORE.get(), PA_Blocks.CANDY_NYLIUM.get(), PA_Blocks.CANDYRACK.get(), PA_Blocks.RAINBOW_ORE.get()).addTag(PA_Tags.Blocks.SKIZZIK_ALL_GEM_BLOCKS);
        tag(PA_Tags.Blocks.MINEABLE_WITH_HOE).add(PA_Blocks.SKIZZIK_FLESH_BLOCK.get(), PA_Blocks.WAFFLE_BLOCK.get());

        tag(PA_Tags.Blocks.BEACON_BASE_BLOCKS).add(PA_Blocks.RAINBOW_GEM_BLOCK.get());
        tag(PA_Tags.Blocks.WALL_POST_OVERRIDE).add(PA_Blocks.CANDY_PRESSURE_PLATE.get(), PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get());
    }
}
