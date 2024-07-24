package org.emulator.wireds.messages.outgoing;

import networking.packets.IOutgoingPacket;
import org.emulator.wireds.boxes.variables.WiredVariableItem;

public class WiredVariableMessageComposer extends IOutgoingPacket {
    public WiredVariableMessageComposer(final WiredVariableItem wiredVariableItem) {
        super(123123);

    }
}
