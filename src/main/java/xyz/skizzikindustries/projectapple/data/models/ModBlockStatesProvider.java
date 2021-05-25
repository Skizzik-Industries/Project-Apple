package xyz.skizzikindustries.projectapple.data.models;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.COMMAND_BLOCK.get());
        simpleBlock(ModBlocks.DEACTIVATED_COMMAND_BLOCK.get());
        simpleBlock(ModBlocks.BROKEN_COMMAND_BLOCK.get());

        simpleBlock(ModBlocks.SKIZZIK_FLESH_BLOCK.get());

        simpleBlock(ModBlocks.WHITE_CHOCOLATE_BLOCK.get());
        simpleBlock(ModBlocks.CHOCOLATE_BLOCK.get());
        simpleBlock(ModBlocks.DARK_CHOCOLATE_BLOCK.get());
        simpleBlock(ModBlocks.WAFFLE_BLOCK.get());

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
