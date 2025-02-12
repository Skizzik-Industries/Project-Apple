package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.network.PA_BossEventPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_PacketHandler {
    private static int ID = 0;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ProjectApple.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SubscribeEvent
    public static void registerMessages(FMLCommonSetupEvent event) {
        INSTANCE.registerMessage(ID++, PA_BossEventPacket.class, PA_BossEventPacket::write, PA_BossEventPacket::decode, PA_BossEventPacket::handle);
    }
}
