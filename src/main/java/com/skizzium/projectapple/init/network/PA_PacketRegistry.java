package com.skizzium.projectapple.init.network;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.network.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_PacketRegistry {
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
        INSTANCE.registerMessage(ID++, BossMusicStartPacket.class, BossMusicStartPacket::encode, BossMusicStartPacket::decode, BossMusicStartPacket::handle);
        INSTANCE.registerMessage(ID++, BossMusicStopPacket.class, BossMusicStopPacket::encode, BossMusicStopPacket::decode, BossMusicStopPacket::handle);

        INSTANCE.registerMessage(ID++, AddBossEventPacket.class, AddBossEventPacket::encode, AddBossEventPacket::decode, AddBossEventPacket::handle);
        INSTANCE.registerMessage(ID++, RemoveBossEventPacket.class, RemoveBossEventPacket::encode, RemoveBossEventPacket::decode, RemoveBossEventPacket::handle);
        INSTANCE.registerMessage(ID++, UpdateNameBossEventPacket.class, UpdateNameBossEventPacket::encode, UpdateNameBossEventPacket::decode, UpdateNameBossEventPacket::handle);
        INSTANCE.registerMessage(ID++, UpdateStyleBossEventPacket.class, UpdateStyleBossEventPacket::encode, UpdateStyleBossEventPacket::decode, UpdateStyleBossEventPacket::handle);
        INSTANCE.registerMessage(ID++, UpdatePropertiesBossEventPacket.class, UpdatePropertiesBossEventPacket::encode, UpdatePropertiesBossEventPacket::decode, UpdatePropertiesBossEventPacket::handle);
        INSTANCE.registerMessage(ID++, UpdateProgressBossEventPacket.class, UpdateProgressBossEventPacket::encode, UpdateProgressBossEventPacket::decode, UpdateProgressBossEventPacket::handle);
    }
}
