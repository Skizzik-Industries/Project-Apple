package com.skizzium.projectapple;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.init.PA_Config;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple {
    public static final String MOD_ID = "skizzik";
    public static final Logger LOGGER = LogManager.getLogger();

    public static int holiday; // 0 - None, 1 - Spooktober, 2 - Halloween (Nightmare Day in the files to avoid confusion)
    
    private static List<ResourceLocation> corruptionImmuneBlocksList;
    private static List<ResourceLocation> rainbowSwordImmuneBlocksList;

    public ProjectApple() {
        holiday = checkForHolidays();
        GeckoLib.initialize();
        
        PA_Registry.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(PA_TileEntities::registerSkullHeadLayers);
        modBus.addListener(PA_Blocks::renderLayers);
        modBus.addListener(PA_Blocks::registerOtherStuff);
        modBus.addListener(PA_TileEntities::registerTileEntityRenders);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PA_Config.commonSpec);

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    }

    private static int checkForHolidays() {
        String currentDay = DateTimeFormatter.ofPattern("dd").format(LocalDateTime.now());
        String currentMonth = DateTimeFormatter.ofPattern("MM").format(LocalDateTime.now());
        if (currentMonth.equals("10")) {
//            if (currentDay.equals("31"))
//                return 2;

            return 1;
        }
        return 0;
    }

    public static String getThemedDescriptionId(String descriptionId) {
        if (holiday == 1)
            return "spooktober." + descriptionId;
        else if (holiday == 2)
            return "nightmare." + descriptionId;

        return descriptionId;
    }
    
    private void configLoad(ModConfigEvent event) {
        if(event.getConfig().getModId().equals(ProjectApple.MOD_ID)) {
            this.updateLists();
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void playerLogin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        this.updateLists();
    }

    private void updateLists() {
        corruptionImmuneBlocksList = ImmutableList.copyOf(PA_Config.commonInstance.blocks.corruptionImmuneBlocks.get().stream().map(ResourceLocation::new).collect(Collectors.toList()));
        rainbowSwordImmuneBlocksList = ImmutableList.copyOf(PA_Config.commonInstance.blocks.rainbowSwordImmuneBlocks.get().stream().map(ResourceLocation::new).collect(Collectors.toList()));
    }

    public static List<ResourceLocation> getCorruptionImmuneBlocksList() {
        return corruptionImmuneBlocksList;
    }

    public static List<ResourceLocation> getRainbowSwordImmuneBlocksList() {
        return rainbowSwordImmuneBlocksList;
    }
}
