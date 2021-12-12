package com.skizzium.projectapple.init.client;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.rpc.entities.pipe.PipeStatus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PA_ForgeClientEvents {
    @SubscribeEvent
    public static void disconnectRPC(ClientPlayerNetworkEvent.LoggedOutEvent event) {
        if (ProjectApple.RPC.getStatus() == PipeStatus.CONNECTED)
            ProjectApple.RPC.close();
    }
}
