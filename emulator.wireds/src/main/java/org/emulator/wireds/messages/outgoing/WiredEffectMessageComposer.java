package org.emulator.wireds.messages.outgoing;

import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.base.WiredEffect;
import packets.outgoing.OutgoingHeaders;

public class WiredEffectMessageComposer extends OutgoingPacket {
    public WiredEffectMessageComposer(final WiredEffect wiredEffect) {
        super(OutgoingHeaders.WiredEffectDataComposer);

        WiredItemWriter.serialize(this, wiredEffect);
    }
}
