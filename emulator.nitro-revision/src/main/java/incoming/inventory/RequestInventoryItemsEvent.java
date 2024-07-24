package incoming.inventory;

import com.google.inject.Singleton;
import networking.client.IClient;
import networking.packets.IIncomingPacket;
import networking.packets.IncomingEvent;
import packets.incoming.IncomingHeaders;
import packets.outgoing.inventory.InventoryItemsComposer;

@Singleton
public class RequestInventoryItemsEvent extends IncomingEvent {
    private static final int InventoryPageSize = 50;

    @Override
    public int getHeaderId() {
        return IncomingHeaders.RequestInventoryItemsEvent;
    }

    @Override
    public void parse(IIncomingPacket packet, IClient client) { // TODO INCOMING RATE LIMIT
        var allItems = client.getHabbo().getInventory().getItems().values().stream().toList();
        final var totalFragments = Math.max((int) Math.ceil((double) allItems.size() / InventoryPageSize), 1);
        for (int i = 1; i <= totalFragments; i++) {
            client.sendMessages(new InventoryItemsComposer(totalFragments, i, allItems));
        }
    }
}
