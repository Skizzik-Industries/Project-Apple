package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_MissingMappings {
    @SubscribeEvent
    public static void missingMappingsTE(RegistryEvent.MissingMappings<BlockEntityType<?>> event) {
        for (RegistryEvent.MissingMappings.Mapping<BlockEntityType<?>> map : event.getMappings(ProjectApple.MOD_ID)) {
            String id = map.key.getPath();

            if (id.equals("pa_sign")) {
                map.remap(PA_TileEntities.PA_SIGN.get());
            }
            if (id.equals("pa_skull")) {
                map.remap(PA_TileEntities.PA_SKULL.get());
            }
        }
    }
}
