package com.skizzium.projectapple.init;

import com.skizzium.projectapple.entity.boss.RPCBoss;
import com.skizzium.projectapple.rpc.IPCClient;
import com.skizzium.projectapple.rpc.IPCListener;
import com.skizzium.projectapple.rpc.entities.RichPresence;
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

    public void initialRichPresence() {
        try {
            RichPresence.Builder presence = new RichPresence.Builder()
                    .setDetails("Fighting Skizzik (Sleeping)")
                    .setLargeImage("skizzik_sleeping", "Skizzik (Sleeping)")
                    .setStartTimestamp(this.timestamp.toEpochSecond());
            client.sendRichPresence(presence.build());
            LogManager.getLogger().info("Skizzik & Co. RPC Updated!");
        } catch (IllegalStateException e) {
            LogManager.getLogger().warn("Discord is not connected. Skipping RPC update!");
        }
    }
    
    public void reloadRichPresence(RPCBoss boss) {
        try {
            boss.getRichPresence().setStartTimestamp(this.timestamp.toEpochSecond());
            client.sendRichPresence(boss.getRichPresence().build());
            LogManager.getLogger().info("Skizzik & Co. RPC Updated!");
        } catch (IllegalStateException e) {
            LogManager.getLogger().warn("Discord is not connected. Skipping RPC update!");
        }
    }
}
