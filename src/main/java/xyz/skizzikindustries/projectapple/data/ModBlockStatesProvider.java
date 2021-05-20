package xyz.skizzikindustries.projectapple.data;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.ModBlocks;

import java.util.Arrays;
import java.util.List;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.COMMAND_BLOCK.get());
        simpleBlock(ModBlocks.DEACTIVATED_COMMAND_BLOCK.get());
        simpleBlock(ModBlocks.BROKEN_COMMAND_BLOCK.get());

        axisBlock((RotatedPillarBlock) ModBlocks.SKIZZIK_HEAD.get());
        simpleBlock(ModBlocks.SKIZZIK_WALL_HEAD.get());

        simpleBlock(ModBlocks.SKIZZIK_FLESH_BLOCK.get());

        simpleBlock(ModBlocks.CANDIANITE_ORE.get());

        simpleBlock(ModBlocks.CANDY_LEAVES.get());
        logBlock((RotatedPillarBlock) ModBlocks.CANDY_LOG.get());
        simpleBlock(ModBlocks.CANDYRACK.get());

        simpleBlock(ModBlocks.RAINBOW_ORE.get());

        simpleBlock(ModBlocks.RAINBOW_GEM_BLOCK.get());
        simpleBlock(ModBlocks.BLACK_GEM_BLOCK.get());
        simpleBlock(ModBlocks.BLUE_GEM_BLOCK.get());
        simpleBlock(ModBlocks.BROWN_GEM_BLOCK.get());
        simpleBlock(ModBlocks.YELLOW_GEM_BLOCK.get());
        simpleBlock(ModBlocks.ORANGE_GEM_BLOCK.get());
        simpleBlock(ModBlocks.GREEN_GEM_BLOCK.get());
        simpleBlock(ModBlocks.PINK_GEM_BLOCK.get());
    }
}
