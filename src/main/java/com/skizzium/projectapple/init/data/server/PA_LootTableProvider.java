package com.skizzium.projectapple.init.data.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

public class PA_LootTableProvider extends LootTableProvider {
    public PA_LootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
            Pair.of(PA_BlockLootTables::new, LootContextParamSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTables.validate(validationtracker, p_218436_2_, p_218436_3_));
    }

    public static class PA_BlockLootTables extends BlockLoot {
        private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

        @Override
        protected void addTables() {
            dropSelf(PA_Blocks.COMMAND_BLOCK.get());
            dropSelf(PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get());
            dropSelf(PA_Blocks.BROKEN_COMMAND_BLOCK.get());

            dropSelf(PA_Blocks.SMALL_FRIENDLY_SKIZZIK_HEAD.get());
            dropSelf(PA_Blocks.SMALL_FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get());
            dropSelf(PA_Blocks.FRIENDLY_SKIZZIK_HEAD.get());
            dropSelf(PA_Blocks.FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get());
            
            dropSelf(PA_Blocks.SMALL_SKIZZIK_HEAD.get());
            dropSelf(PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get());
            dropSelf(PA_Blocks.SKIZZIK_HEAD.get());
            dropSelf(PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get());
            
            dropSelf(PA_Blocks.FRIENDLY_SKIZZIK_FLESH_BLOCK.get());
            dropSelf(PA_Blocks.SKIZZIK_FLESH_BLOCK.get());

            dropSelf(PA_Blocks.CORRUPTED_BLOCK.get());
            dropSelf(PA_Blocks.SKIZZIE_STATUE.get());
            dropSelf(PA_Blocks.SPOOKZIE_STATUE.get());

            add(PA_Blocks.SKIZZIK_LOOT_BAG.get(), (loot) -> LootTable.lootTable().withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(ProjectApple.holiday == 1 ? PA_Items.SPOOKZIK_SEAL.get() : PA_Items.SKIZZIK_SEAL.get()))
                                                                                           .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                                                                                   .withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(ProjectApple.holiday == 1 ? PA_Items.MUSIC_DISC_SPOOKZIK.get() : PA_Items.MUSIC_DISC_SKIZZIK.get()))
                                                                                           .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                                                                                   .withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get()))
                                                                                           .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,4))))
                                                                                   .withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(PA_Items.SKIZZIK_HEAD_WITH_GEMS.get()))
                                                                                           .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                                                                                   .withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(Items.NETHER_STAR))
                                                                                           .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
                                                                                   .withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(PA_Blocks.BROKEN_COMMAND_BLOCK.get()))
                                                                                           .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                                                                                   .withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(ProjectApple.holiday == 1 ? Blocks.AIR.asItem() : PA_Items.SKIZZIK_BONE.get()))
                                                                                           .apply(SetItemCountFunction.setCount(UniformGenerator.between(5,15))))
                                                                                   .withPool(LootPool.lootPool()
                                                                                           .setRolls(ConstantValue.exactly(1))
                                                                                           .add(LootItem.lootTableItem(ProjectApple.holiday == 1 ? Blocks.SPRUCE_PLANKS.asItem() : PA_Items.SKIZZIK_FLESH.get()))
                                                                                           .apply(SetItemCountFunction.setCount(ProjectApple.holiday == 1 ? UniformGenerator.between(2, 3) : UniformGenerator.between(5, 15)))));
            
            add(PA_Blocks.RAINBOW_ORE.get(), (drops) -> createOreDrop(drops, PA_Items.RAINBOW_GEM.get()));

            dropSelf(PA_Blocks.RAINBOW_GEM_BLOCK.get());
            dropSelf(PA_Blocks.BLACK_GEM_BLOCK.get());
            dropSelf(PA_Blocks.BLUE_GEM_BLOCK.get());
            dropSelf(PA_Blocks.BROWN_GEM_BLOCK.get());
            dropSelf(PA_Blocks.YELLOW_GEM_BLOCK.get());
            dropSelf(PA_Blocks.ORANGE_GEM_BLOCK.get());
            dropSelf(PA_Blocks.GREEN_GEM_BLOCK.get());
            dropSelf(PA_Blocks.PINK_GEM_BLOCK.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return PA_Registry.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }
    }
}
