package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID)
public class PA_BiomeLoadingEvent {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addFeatures(BiomeLoadingEvent event) {
        Biome.BiomeCategory biomeCategory = event.getCategory();
        BiomeGenerationSettingsBuilder builder = event.getGeneration();

        PA_Features.addOres(builder, biomeCategory);
    }
}
