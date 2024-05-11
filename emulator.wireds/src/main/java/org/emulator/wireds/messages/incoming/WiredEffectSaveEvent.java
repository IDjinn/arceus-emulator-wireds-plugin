package org.emulator.wireds.messages.incoming;

import com.google.inject.Singleton;
import habbo.rooms.components.objects.items.floor.IFloorItem;
import networking.client.IClient;
import networking.packets.IIncomingPacket;
import networking.packets.IncomingEvent;
import org.emulator.wireds.boxes.effects.WiredEffect;
import org.emulator.wireds.messages.outgoing.WiredSavedComposer;
import packets.incoming.IncomingHeaders;

@Singleton
public class WiredEffectSaveEvent extends IncomingEvent {
    @Override
    public int getHeaderId() {
        return IncomingHeaders.WiredEffectSaveDataEvent;
    }

    @Override
    public void parse(final IIncomingPacket packet, final IClient client) {
        if (client.getHabbo().getRoom() == null) return;

        if (!client.getHabbo().getRoom().getRightsManager().hasRights(client.getHabbo())) return;

        final int effectId = packet.readInt();
        final IFloorItem item = client.getHabbo().getRoom().getObjectManager().getFloorItem(effectId);
        if (!(item instanceof WiredEffect effect)) return;

        final var settings = WiredItemReader.readSettings(client, packet);
        effect.setSettings(settings);
        client.sendMessage(new WiredSavedComposer());
    }
}
