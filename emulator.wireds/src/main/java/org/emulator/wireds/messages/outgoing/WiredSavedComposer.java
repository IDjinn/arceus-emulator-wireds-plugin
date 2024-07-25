package org.emulator.wireds.messages.outgoing;

import packets.outgoing.OutgoingHeaders;

public class WiredSavedComposer extends IOutgoingDTOSerializer {
    public WiredSavedComposer() {
        super(OutgoingHeaders.WiredSavedComposer);
    }
}
