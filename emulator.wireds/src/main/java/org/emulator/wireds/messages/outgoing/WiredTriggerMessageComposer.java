package org.emulator.wireds.messages.outgoing;

import org.emulator.wireds.boxes.base.WiredTrigger;
import packets.outgoing.OutgoingHeaders;

public class WiredTriggerMessageComposer extends IOutgoingDTOSerializer {
    public WiredTriggerMessageComposer(final WiredTrigger wiredTrigger) {
        super(OutgoingHeaders.WiredTriggerDataComposer);

        WiredItemWriter.serialize(this, wiredTrigger);
    }
}
