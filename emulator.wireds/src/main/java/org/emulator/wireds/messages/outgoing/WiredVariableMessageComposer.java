package org.emulator.wireds.messages.outgoing;

import networking.packets.outgoing.IOutgoingDTOSerializer;
import org.emulator.wireds.boxes.variables.WiredVariableItem;

public class WiredVariableMessageComposer extends IOutgoingDTOSerializer {
    public WiredVariableMessageComposer(final WiredVariableItem wiredVariableItem) {
        super(123123);

    }
}
