package org.emulator.wireds.messages.incoming;

import com.google.inject.Singleton;
import habbo.rooms.components.objects.items.floor.IFloorItem;
import networking.client.IClient;
import networking.packets.IIncomingPacket;
import networking.packets.IncomingEvent;
import org.emulator.wireds.boxes.triggers.WiredTrigger;
import org.emulator.wireds.messages.outgoing.WiredSavedComposer;
import packets.incoming.IncomingHeaders;

@Singleton
public class WiredTriggerSaveEvent extends IncomingEvent {
    @Override
    public int getHeaderId() {
        return IncomingHeaders.WiredTriggerSaveDataEvent;
    }

    @Override
    public void parse(final IIncomingPacket packet, final IClient client) {
        if (client.getHabbo().getRoom() == null) return;

        if (!client.getHabbo().getPlayerEntity().hasRights()) return;

        final int triggerId = packet.readInt();
        final IFloorItem item = client.getHabbo().getRoom().getObjectManager().getFloorItem(triggerId);
        if (!(item instanceof WiredTrigger trigger)) return;

        final var settings = WiredItemReader.readSettings(client, packet);
        trigger.setSettings(settings);
        client.sendMessage(new WiredSavedComposer());
    }
}
