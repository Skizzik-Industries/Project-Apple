package xyz.skizzikindustries.projectapple.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.init.ModBlocks;
import xyz.skizzikindustries.projectapple.init.ModItems;
import xyz.skizzikindustries.projectapple.init.Register;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTablesProvider extends LootTableProvider {
    public ModLootTablesProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
    }

    public static class ModBlockLootTables extends BlockLootTables {
        private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

        @Override
        protected void addTables() {
            dropSelf(ModBlocks.COMMAND_BLOCK.get());
            dropSelf(ModBlocks.DEACTIVATED_COMMAND_BLOCK.get());
            dropSelf(ModBlocks.BROKEN_COMMAND_BLOCK.get());

            dropSelf(ModBlocks.SKIZZIK_FLESH_BLOCK.get());

            add(ModBlocks.CANDY_LEAVES.get(), (drops) -> createLeavesDrops(drops, Blocks.OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
            dropSelf(ModBlocks.CANDY_LOG.get());
            add(ModBlocks.CANDY_NYLIUM.get(), (drops) -> createSingleItemTableWithSilkTouch(drops, ModBlocks.CANDYRACK.get()));
            dropSelf(ModBlocks.CANDYRACK.get());

            add(ModBlocks.RAINBOW_ORE.get(), (drops) -> createOreDrop(drops, ModItems.RAINBOW_GEM.get()));

            dropSelf(ModBlocks.RAINBOW_GEM_BLOCK.get());
            dropSelf(ModBlocks.BLACK_GEM_BLOCK.get());
            dropSelf(ModBlocks.BLUE_GEM_BLOCK.get());
            dropSelf(ModBlocks.BROWN_GEM_BLOCK.get());
            dropSelf(ModBlocks.YELLOW_GEM_BLOCK.get());
            dropSelf(ModBlocks.ORANGE_GEM_BLOCK.get());
            dropSelf(ModBlocks.GREEN_GEM_BLOCK.get());
            dropSelf(ModBlocks.PINK_GEM_BLOCK.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Register.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}