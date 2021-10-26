package com.skizzium.projectapple.init.data.models;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PA_BlockStatesProvider extends BlockStateProvider {
    public PA_BlockStatesProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }
    
    public ModelFile cross(Block block) {
        return models().cross(block.getRegistryName().getPath(), blockTexture(block));
    }
    
    public ModelFile grass(Block block, String bottom) {
        return this.grassBlockModel(block.getRegistryName().getPath(), 
                new ResourceLocation(ProjectApple.MOD_ID, ModelProvider.BLOCK_FOLDER + "/" + bottom),
                new ResourceLocation(ProjectApple.MOD_ID,ModelProvider.BLOCK_FOLDER + "/" + block.getRegistryName().getPath()),
                new ResourceLocation(ProjectApple.MOD_ID,ModelProvider.BLOCK_FOLDER + "/" + block.getRegistryName().getPath() + "_side"));
    }

    public ModelFile grassBlockModel(String name, ResourceLocation down, ResourceLocation up, ResourceLocation side) {
        return models().withExistingParent(name, "cube")
                .texture("down", down)
                .texture("up", up)
                .texture("north", side)
                .texture("south", side)
                .texture("east", side)
                .texture("west", side)
                .texture("particle", down);
    }
    
    public void crossBlock(Block block) {
        simpleBlock(block, cross(block));
    }
    
    public void grassBlock(Block block, String bottom) {
        simpleBlock(block, grass(block, bottom));
    }
    
    @Override
    protected void registerStatesAndModels() {
        ResourceLocation candyPlanks = new ResourceLocation(ProjectApple.MOD_ID, "block/candy_planks");
        
        simpleBlock(PA_Blocks.SKIZZIK_FLESH_BLOCK.get());

        simpleBlock(PA_Blocks.CORRUPTED_BLOCK.get());

        crossBlock(PA_Blocks.CANDY_CANE.get());
        simpleBlock(PA_Blocks.WAFFLE_BLOCK.get());
        
        simpleBlock(PA_Blocks.WHITE_CHOCOLATE_BLOCK.get());
        simpleBlock(PA_Blocks.CHOCOLATE_BLOCK.get());
        simpleBlock(PA_Blocks.DARK_CHOCOLATE_BLOCK.get());

        simpleBlock(PA_Blocks.CANDIANITE_ORE.get());

        simpleBlock(PA_Blocks.CANDY_PLANKS.get());
        
        buttonBlock(PA_Blocks.CANDY_BUTTON.get(), candyPlanks);
        pressurePlateBlock(PA_Blocks.CANDY_PRESSURE_PLATE.get(), candyPlanks);
        
        signBlock(PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get(), candyPlanks);
        
        slabBlock(PA_Blocks.CANDY_SLAB.get(), candyPlanks, candyPlanks);
        stairsBlock(PA_Blocks.CANDY_STAIRS.get(), candyPlanks);

        fenceBlock(PA_Blocks.CANDY_FENCE.get(), candyPlanks);
        fenceGateBlock(PA_Blocks.CANDY_FENCE_GATE.get(), candyPlanks);

        trapdoorBlock(PA_Blocks.CANDY_TRAPDOOR.get(), new ResourceLocation("skizzik:block/candy_trapdoor"), false);
        doorBlock(PA_Blocks.CANDY_DOOR.get(), new ResourceLocation("skizzik:block/candy_door_bottom"), new ResourceLocation("skizzik:block/candy_door_top"));

        crossBlock(PA_Blocks.CANDY_SAPLING.get());
        
        logBlock(PA_Blocks.CANDY_LOG.get());
        logBlock(PA_Blocks.STRIPPED_CANDY_LOG.get());

        simpleBlock(PA_Blocks.CANDY_LEAVES.get());
        grassBlock(PA_Blocks.CANDY_NYLIUM.get(), PA_Blocks.CANDYRACK.get().getRegistryName().getPath());
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
