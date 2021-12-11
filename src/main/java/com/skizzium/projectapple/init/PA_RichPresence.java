package com.skizzium.projectapple.init;

import com.skizzium.projectapple.rpc.IPCClient;
import com.skizzium.projectapple.rpc.IPCListener;
import com.skizzium.projectapple.rpc.entities.RichPresence;

import java.time.OffsetDateTime;

public class PA_RichPresence implements IPCListener {
    private static OffsetDateTime timestamp;

    @Override
    public void onReady(IPCClient client) {
        timestamp = OffsetDateTime.now();
        RichPresence.Builder builder = new RichPresence.Builder().setDetails("LESS GO?");
        client.sendRichPresence(builder.build());
    }
}
