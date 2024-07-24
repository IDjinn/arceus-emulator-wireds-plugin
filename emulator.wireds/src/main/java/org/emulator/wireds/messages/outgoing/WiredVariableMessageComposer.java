package org.emulator.wireds.messages.outgoing;

import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.variables.WiredVariableItem;

public class WiredVariableMessageComposer extends OutgoingPacket<U> {
    public WiredVariableMessageComposer(final WiredVariableItem wiredVariableItem) {
        super(123123);

    }
}
