package com.skizzium.projectapple.data;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.data.models.PA_BlockStatesProvider;
import com.skizzium.projectapple.data.models.PA_ItemModelsProvider;
import com.skizzium.projectapple.data.tags.PA_BlockTagsProvider;
import com.skizzium.projectapple.data.tags.PA_FluidTagsProvider;
import com.skizzium.projectapple.data.tags.PA_ItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(new PA_BlockStatesProvider(generator, helper));
        generator.addProvider(new PA_ItemModelsProvider(generator, helper));

        generator.addProvider(new PA_LootTablesProvider(generator));

        PA_BlockTagsProvider blockTags = new PA_BlockTagsProvider(generator, helper);
        generator.addProvider(blockTags);
        generator.addProvider(new PA_FluidTagsProvider(generator, helper));
        generator.addProvider(new PA_ItemTagsProvider(generator, blockTags, helper));
    }
}
