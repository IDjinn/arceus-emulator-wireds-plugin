package outgoing.inventory;

import habbo.habbos.inventory.IHabboInventoryItem;
import networking.packets.OutgoingPacket;

import java.util.List;

public class InventoryItemsComposer extends OutgoingPacket<U> {
    public InventoryItemsComposer(int fragment, int totalFragments, List<IHabboInventoryItem> items) {
        super(OutgoingHeaders.InventoryItemsComposer);

        this.appendInt(fragment);
        this.appendInt(totalFragments - 1);
        this.appendInt(items.size());
        for (var item : items) {
            item.serialize(this);
        }
    }
}
