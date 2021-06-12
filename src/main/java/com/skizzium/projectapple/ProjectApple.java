package com.skizzium.projectapple;

import com.skizzium.projectapple.init.ModEntities;
import com.skizzium.projectapple.init.Register;
import com.skizzium.projectapple.init.block.ModBlocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple
{
    public static final String MOD_ID = "skizzik";
    public static final Logger LOGGER = LogManager.getLogger();

    public ProjectApple() {
        Register.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(ModBlocks::renderLayers);
        modBus.addListener(ModEntities::registerRenderers);
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    }
}
