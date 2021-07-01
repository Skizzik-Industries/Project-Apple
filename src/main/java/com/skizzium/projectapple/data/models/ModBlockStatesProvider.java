package com.skizzium.projectapple.data.models;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.data.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(PA_Blocks.COMMAND_BLOCK.get());
        simpleBlock(PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get());
        simpleBlock(PA_Blocks.BROKEN_COMMAND_BLOCK.get());

        //directionalBlock(ModBlocks.SKIZZIK_HEAD.get(), new ModelFile.ExistingModelFile(new ResourceLocation("minecraft:block/skull"), this.models().existingFileHelper));

        simpleBlock(PA_Blocks.SKIZZIK_FLESH_BLOCK.get());

        simpleBlock(PA_Blocks.CORRUPTED_BLOCK.get());

        simpleBlock(PA_Blocks.WHITE_CHOCOLATE_BLOCK.get());
        simpleBlock(PA_Blocks.CHOCOLATE_BLOCK.get());
        simpleBlock(PA_Blocks.DARK_CHOCOLATE_BLOCK.get());
        simpleBlock(PA_Blocks.WAFFLE_BLOCK.get());

        simpleBlock(PA_Blocks.CANDIANITE_ORE.get());

        simpleBlock(PA_Blocks.CANDY_PLANKS.get());
        slabBlock(PA_Blocks.CANDY_SLAB.get(), new ResourceLocation("skizzik:block/candy_planks"), new ResourceLocation("skizzik:block/candy_planks"));
        stairsBlock(PA_Blocks.CANDY_STAIRS.get(), new ResourceLocation("skizzik:block/candy_planks"));

        fenceBlock(PA_Blocks.CANDY_FENCE.get(), new ResourceLocation("skizzik:block/candy_planks"));
        fenceGateBlock(PA_Blocks.CANDY_FENCE_GATE.get(), new ResourceLocation("skizzik:block/candy_planks"));

        trapdoorBlock(PA_Blocks.CANDY_TRAPDOOR.get(), new ResourceLocation("skizzik:block/candy_trapdoor"), false);
        doorBlock(PA_Blocks.CANDY_DOOR.get(), new ResourceLocation("skizzik:block/candy_door_bottom"), new ResourceLocation("skizzik:block/candy_door_top"));

        logBlock(PA_Blocks.CANDY_LOG.get());
        logBlock(PA_Blocks.STRIPPED_CANDY_LOG.get());

        simpleBlock(PA_Blocks.CANDY_LEAVES.get());
        simpleBlock(PA_Blocks.CANDYRACK.get());

        simpleBlock(PA_Blocks.RAINBOW_ORE.get());

        simpleBlock(PA_Blocks.RAINBOW_GEM_BLOCK.get());
        simpleBlock(PA_Blocks.BLACK_GEM_BLOCK.get());
        simpleBlock(PA_Blocks.BLUE_GEM_BLOCK.get());
        simpleBlock(PA_Blocks.BROWN_GEM_BLOCK.get());
        simpleBlock(PA_Blocks.YELLOW_GEM_BLOCK.get());
        simpleBlock(PA_Blocks.ORANGE_GEM_BLOCK.get());
        simpleBlock(PA_Blocks.GREEN_GEM_BLOCK.get());
        simpleBlock(PA_Blocks.PINK_GEM_BLOCK.get());
    }
}
