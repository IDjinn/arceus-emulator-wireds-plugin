package org.emulator.wireds.messages.outgoing;

import networking.packets.OutgoingPacket;
import packets.outgoing.OutgoingHeaders;

public class WiredSavedComposer extends OutgoingPacket<U> {
    public WiredSavedComposer() {
        super(OutgoingHeaders.WiredSavedComposer);
    }
}
