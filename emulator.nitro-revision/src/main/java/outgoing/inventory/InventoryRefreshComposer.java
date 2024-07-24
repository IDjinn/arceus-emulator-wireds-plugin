package outgoing.inventory;

import networking.packets.OutgoingPacket;


public class InventoryRefreshComposer extends OutgoingPacket<U> {
    public InventoryRefreshComposer() {
        super(OutgoingHeaders.InventoryRefreshComposer);
    }
}
