package xyz.skizzikindustries.projectapple;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.Register;
import xyz.skizzikindustries.projectapple.init.block.ModFluids;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple
{
    public static final String MOD_ID = "skizzik";

    public static final Logger LOGGER = LogManager.getLogger();

    public ProjectApple() {
        Register.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::renderLayers);
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    }

    private void renderLayers(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.SKIZZIK_LOOT_BAG.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.STONE_SKIZZIE.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.CANDY_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModFluids.MAPLE_SYRUP.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModFluids.FLOWING_MAPLE_SYRUP.get(), RenderType.translucent());
    }
}
