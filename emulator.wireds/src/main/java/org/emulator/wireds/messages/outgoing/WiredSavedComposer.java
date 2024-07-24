package org.emulator.wireds.messages.outgoing;

import networking.packets.IOutgoingPacket;
import packets.outgoing.OutgoingHeaders;

public class WiredSavedComposer extends IOutgoingPacket {
    public WiredSavedComposer() {
        super(OutgoingHeaders.WiredSavedComposer);
    }
}
