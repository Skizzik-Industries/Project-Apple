package com.skizzium.projectapple.init.network;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.network.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

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
        INSTANCE.registerMessage(ID++, RPCPacket.class, RPCPacket::encode, RPCPacket::decode, RPCPacket::handle);
        INSTANCE.registerMessage(ID++, SaveMaxPlayers.class, SaveMaxPlayers::encode, SaveMaxPlayers::decode, SaveMaxPlayers::handle);
        INSTANCE.registerMessage(ID++, FriendlySkizzikOpenMenuPacket.class, FriendlySkizzikOpenMenuPacket::encode, FriendlySkizzikOpenMenuPacket::decode, FriendlySkizzikOpenMenuPacket::handle);
        INSTANCE.registerMessage(ID++, FriendlySkizzikOpenScreenPacket.class, FriendlySkizzikOpenScreenPacket::encode, FriendlySkizzikOpenScreenPacket::decode, FriendlySkizzikOpenScreenPacket::handle);
        INSTANCE.registerMessage(ID++, SkizzoConnectionParticlesPacket.class, SkizzoConnectionParticlesPacket::encode, SkizzoConnectionParticlesPacket::decode, SkizzoConnectionParticlesPacket::handle);
    }
}
