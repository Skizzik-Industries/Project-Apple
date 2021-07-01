package com.skizzium.projectapple;

import com.skizzium.projectapple.init.PA_Entities;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_ItemRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple
{
    public static final String MOD_ID = "skizzik";
    public static final Logger LOGGER = LogManager.getLogger();

    public ProjectApple() {
        PA_Registry.register();

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(PA_Blocks::renderLayers);
        modBus.addListener(PA_Entities::registerRenderers);
        modBus.addListener(PA_Blocks::registerWoodTypes);
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    }

    public static Item.Properties setupISTER(Item.Properties group) {
        return group.setISTER(ProjectApple::getISTER);
    }

    @OnlyIn(Dist.CLIENT)
    public static Callable<ItemStackTileEntityRenderer> getISTER() {
        return PA_ItemRenderer::new;
    }
}
