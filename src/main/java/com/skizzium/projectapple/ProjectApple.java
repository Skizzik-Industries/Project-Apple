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

    public static int holiday; // 0 - None, 1 - Spooktober, 2 - Halloween (Nightmare Day in the files to avoid confusion)

    public ProjectApple() {
        holiday = checkForHolidays();

        PA_Registry.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(PA_TileEntities::registerSkullHeadLayers);
        modBus.addListener(PA_Blocks::renderLayers);
        modBus.addListener(PA_Blocks::registerOtherStuff);
        modBus.addListener(PA_TileEntities::registerTileEntityRenders);

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    }

    private static int checkForHolidays() {
        return 1;

        /*String currentDay = DateTimeFormatter.ofPattern("dd").format(LocalDateTime.now());
        String currentMonth = DateTimeFormatter.ofPattern("MM").format(LocalDateTime.now());
        if (currentMonth.equals("10")) {
            if (currentDay.equals("31"))
                return 2;

            return 1;
        }
        return 0;*/
    }

    public static String getThemedDescriptionId(String descriptionId) {
        if (holiday == 1)
            return "spooktober." + descriptionId;
        else if (holiday == 2)
            return "nightmare." + descriptionId;

        return descriptionId;
    }
}
