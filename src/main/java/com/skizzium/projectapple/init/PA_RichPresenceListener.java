package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.RPCBoss;
import com.skizzium.projectapple.rpc.IPCClient;
import com.skizzium.projectapple.rpc.IPCListener;
import com.skizzium.projectapple.rpc.entities.RichPresence;
import com.skizzium.projectapple.rpc.entities.pipe.PipeStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;

import java.time.OffsetDateTime;

public class PA_RichPresenceListener implements IPCListener {
    private OffsetDateTime timestamp;
    private IPCClient client;
    
    @Override
    public void onReady(IPCClient client) {
        timestamp = OffsetDateTime.now();
        this.client = client;
    }
    
    private RichPresence.Builder setMultiplayerValues(RichPresence.Builder presence) {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (Minecraft.getInstance().hasSingleplayerServer() && Minecraft.getInstance().getSingleplayerServer().isPublished()) {
            presence.setState("Singleplayer");
            presence.setParty("Gabby", 0, 0); // I had to put something for the party id so I decided to put my GF's name :)
        }
        else {
            presence.setState("Multiplayer");
            presence.setParty("Gabby", Minecraft.getInstance().getConnection().getOnlinePlayers().size(), ProjectApple.maxPlayers);
        }
        return presence;
    }

    public void initialRichPresence() {
        if (PA_Config.clientInstance.clientOptions.discordRPC.get()) {
            RichPresence.Builder presence = new RichPresence.Builder()
                    .setDetails("Fighting Skizzik (Sleeping)")
                    .setLargeImage("skizzik_sleeping", "Skizzik (Sleeping)");
            this.updateRichPresence(presence);
        }
        else if (ProjectApple.RPC.getStatus() == PipeStatus.CONNECTED) {
            ProjectApple.RPC.close();
        }
    }
    
    public void reloadRichPresence(RPCBoss boss) {
        if (PA_Config.clientInstance.clientOptions.discordRPC.get()) {
            this.updateRichPresence(boss.getRichPresence());
        }
        else if (ProjectApple.RPC.getStatus() == PipeStatus.CONNECTED) {
            ProjectApple.RPC.close();
        }
    }

    public void updateRichPresence(RichPresence.Builder presence) {
        try {
            presence = this.setMultiplayerValues(presence);
            presence.setStartTimestamp(this.timestamp.toEpochSecond());
            client.sendRichPresence(presence.build());
        } catch (IllegalStateException e) {
            LogManager.getLogger().warn("Discord is not connected. Skipping RPC update!");
        }
    }
}
