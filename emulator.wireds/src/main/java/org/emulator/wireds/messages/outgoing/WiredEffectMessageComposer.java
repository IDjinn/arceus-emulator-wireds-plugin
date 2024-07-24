package org.emulator.wireds.messages.outgoing;

import networking.packets.IOutgoingPacket;
import org.emulator.wireds.boxes.base.WiredEffect;
import packets.outgoing.OutgoingHeaders;

public class WiredEffectMessageComposer extends IOutgoingPacket {
    public WiredEffectMessageComposer(final WiredEffect wiredEffect) {
        super(OutgoingHeaders.WiredEffectDataComposer);

        WiredItemWriter.serialize(this, wiredEffect);
    }
}
