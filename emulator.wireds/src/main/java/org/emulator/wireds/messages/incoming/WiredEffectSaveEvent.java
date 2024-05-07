package org.emulator.wireds.messages.incoming;

import com.google.inject.Singleton;
import networking.client.IClient;
import networking.packets.IIncomingPacket;
import networking.packets.IncomingEvent;
import packets.incoming.IncomingHeaders;

@Singleton
public class WiredEffectSaveEvent extends IncomingEvent {
    @Override
    public int getHeaderId() {
        return IncomingHeaders.WiredEffectSaveDataEvent;
    }

    @Override
    public void parse(final IIncomingPacket packet, final IClient client) {


    }
}
