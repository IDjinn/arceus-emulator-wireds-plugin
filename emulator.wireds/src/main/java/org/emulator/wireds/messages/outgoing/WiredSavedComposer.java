package org.emulator.wireds.messages.outgoing;

import networking.packets.outgoing.IOutgoingDTOSerializer;
import packets.outgoing.OutgoingHeaders;

public class WiredSavedComposer extends IOutgoingDTOSerializer {
    public WiredSavedComposer() {
        super(OutgoingHeaders.WiredSavedComposer);
    }
}
