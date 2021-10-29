package com.skizzium.projectapple.init.data;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.data.client.PA_SoundDefinitionsProvider;
import com.skizzium.projectapple.init.data.client.models.PA_BlockStateProvider;
import com.skizzium.projectapple.init.data.client.models.PA_ItemModelProvider;
import com.skizzium.projectapple.init.data.server.PA_LootTableProvider;
import com.skizzium.projectapple.init.data.server.tags.PA_BlockTagsProvider;
import com.skizzium.projectapple.init.data.server.tags.PA_EntityTypeTagsProvider;
import com.skizzium.projectapple.init.data.server.tags.PA_FluidTagsProvider;
import com.skizzium.projectapple.init.data.server.tags.PA_ItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class PA_DataGenerators {
    private PA_DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        // Client Assets
        generator.addProvider(new PA_SoundDefinitionsProvider(generator, helper));
        
        generator.addProvider(new PA_BlockStateProvider(generator, helper));
        generator.addProvider(new PA_ItemModelProvider(generator, helper));

        // Server Data
        generator.addProvider(new PA_LootTableProvider(generator));
        
        PA_BlockTagsProvider blockTags = new PA_BlockTagsProvider(generator, helper);
        generator.addProvider(blockTags);
        
        generator.addProvider(new PA_EntityTypeTagsProvider(generator, helper));
        generator.addProvider(new PA_FluidTagsProvider(generator, helper));
        generator.addProvider(new PA_ItemTagsProvider(generator, blockTags, helper));
    }
}
