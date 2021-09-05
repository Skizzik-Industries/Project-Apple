package com.skizzium.projectapple;

import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple {
    public static final String MOD_ID = "skizzik";
    public static final Logger LOGGER = LogManager.getLogger();

    public static boolean isSpooktober;
    public static boolean isNightmareDay;

    public ProjectApple() {
        String currentDay = DateTimeFormatter.ofPattern("dd").format(LocalDateTime.now());
        String currentMonth = DateTimeFormatter.ofPattern("MM").format(LocalDateTime.now());
        isSpooktober = true; //currentMonth.equals("10");
        isNightmareDay = currentDay.equals("31") && currentMonth.equals("10");

        PA_Registry.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(PA_TileEntities::registerSkullHeadLayers);
        modBus.addListener(PA_Blocks::renderLayers);
        modBus.addListener(PA_Blocks::registerOtherStuff);
        modBus.addListener(PA_TileEntities::registerTileEntityRenders);

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    }

    public static String getThemedDescriptionId(String descriptionId) {
        if (isNightmareDay) {
            return "nightmare." + descriptionId;
        }
        else if (isSpooktober) {
            return "spooktober." + descriptionId;
        }

        return descriptionId;
    }
}
