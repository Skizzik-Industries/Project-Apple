package com.skizzium.projectapple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.skizzie.FriendlySkizzie;
import com.skizzium.projectapple.init.PA_Config;
import com.skizzium.projectapple.init.PA_Registry;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.resource.ResourceListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple {
    public static final String MOD_ID = "skizzik";
    public static final Logger LOGGER = LogManager.getLogger();

    public static int holiday; // 0 - None, 1 - Spooktober, 2 - Halloween (Nightmare Day in the files to avoid confusion)
    public static final Map<Integer, String> holidayNames = Util.make(Maps.newHashMap(), (builder) -> {
        builder.put(1, "spooktober");
        builder.put(2, "nightmare");
    });
    
    private static List<ResourceLocation> corruptionImmuneBlocksList;
    private static List<ResourceLocation> rainbowSwordImmuneBlocksList;

    public ProjectApple() {
        holiday = checkForHolidays();
        
        PA_Registry.register();
        
        GeckoLib.hasInitialized = true;
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ResourceListener::registerReloadListener);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PA_Config.commonSpec);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public static void livingFallEvent(LivingFallEvent event) {
        if (event.getEntity().getVehicle() instanceof Skizzie || event.getEntity().getVehicle() instanceof FriendlySkizzie) {
            event.setCanceled(true);
        }
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
        if (holiday > 0)
            return holidayNames.get(holiday) + "." + descriptionId;

        return descriptionId;
    }

    public static int encodeBossEventProperties(boolean darkenFlag, boolean fogFlag) {
        int i = 0;
        if (darkenFlag) {
            i |= 1;
        }

        if (fogFlag) {
            i |= 2;
        }

        return i;
    }
    
    private void configLoad(ModConfigEvent event) {
        if(event.getConfig().getModId().equals(ProjectApple.MOD_ID)) {
            this.updateLists();
        }
    }

    /*@SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void playerLogin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        this.updateLists();
    }*/

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
