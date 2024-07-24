package org.emulator.wireds.messages.outgoing;

import networking.packets.IOutgoingPacket;
import org.emulator.wireds.boxes.base.WiredTrigger;
import packets.outgoing.OutgoingHeaders;

public class WiredTriggerMessageComposer extends IOutgoingPacket {
    public WiredTriggerMessageComposer(final WiredTrigger wiredTrigger) {
        super(OutgoingHeaders.WiredTriggerDataComposer);

        WiredItemWriter.serialize(this, wiredTrigger);
    }
}
