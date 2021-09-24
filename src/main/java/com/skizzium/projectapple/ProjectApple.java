package com.skizzium.projectapple;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
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

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple {
    public static final String MOD_ID = "skizzik";
    public static final Logger LOGGER = LogManager.getLogger();

    DiscordRPC rpc = DiscordRPC.INSTANCE;
    String applicationId = "";
    String steamId = "";
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    DiscordRichPresence presence = new DiscordRichPresence();

    public static int holiday; // 0 - None, 1 - Spooktober, 2 - Halloween (Nightmare Day in the files to avoid confusion)
    
    private static List<ResourceLocation> corruptionImmuneBlocksList;
    private static List<ResourceLocation> rainbowSwordImmuneBlocksList;

    public ProjectApple() {
        rpc.Discord_Initialize(applicationId, handlers, true, steamId);
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.details = "Testing RPC";
        rpc.Discord_UpdatePresence(presence);
        
        holiday = checkForHolidays();

        PA_Registry.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(PA_TileEntities::registerSkullHeadLayers);
        modBus.addListener(PA_Blocks::renderLayers);
        modBus.addListener(PA_Blocks::registerOtherStuff);
        modBus.addListener(PA_TileEntities::registerTileEntityRenders);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PA_Config.commonSpec);

        MinecraftForge.EVENT_BUS.register(this);
        
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rpc.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "Skizzik & Co. RPC").start();
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
