package org.emulator.wireds.messages.outgoing;

import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.variables.WiredVariable;
import org.emulator.wireds.messages.outgoing.helpers.SerializeItems;

public class WiredVariableMessageComposer extends OutgoingPacket {
    public WiredVariableMessageComposer(final WiredVariable wiredVariable) {
        super(123123);

        SerializeItems.serialize(this, wiredVariable);
    }
}
