package com.skizzium.projectapple.init.data.client.models;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PA_BlockStateProvider extends BlockStateProvider {
    public PA_BlockStateProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }
    
    @Override
    protected void registerStatesAndModels() {
        simpleBlock(PA_Blocks.RAW_SKIZZIK_FLESH_BLOCK.get());
        simpleBlock(PA_Blocks.FRIENDLY_SKIZZIK_FLESH_BLOCK.get());
        simpleBlock(PA_Blocks.SKIZZIK_FLESH_BLOCK.get());

        simpleBlock(PA_Blocks.CORRUPTED_BLOCK.get());

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
