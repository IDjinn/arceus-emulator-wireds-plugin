package org.emulator.wireds.messages.outgoing.helpers;

import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.WiredItem;

public final class SerializeItems {
    public static void serialize(final OutgoingPacket packet, final WiredItem wiredItem) {
        packet.appendInt(wiredItem.getSelectedItems().size());
        for (var item : wiredItem.getSelectedItems().values()) {
            packet.appendInt(item.getId());
        }
    }
}
