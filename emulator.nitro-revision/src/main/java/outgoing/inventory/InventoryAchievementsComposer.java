package outgoing.inventory;

import networking.packets.OutgoingPacket;


public class InventoryAchievementsComposer extends OutgoingPacket<U> {
    public InventoryAchievementsComposer() {
        super(OutgoingHeaders.InventoryAchievementsComposer);
        this.appendInt(0);
    }
}
