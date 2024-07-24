package outgoing.inventory;

import networking.packets.OutgoingPacket;

import java.util.List;

public class AddHabboItemComposer extends OutgoingPacket<U> {
    public AddHabboItemComposer(AddHabboItemCategory category, List<Integer> itemIds) {
        super(OutgoingHeaders.AddHabboItemComposer);

        this.appendInt(1, "total unseen categories");
        this.appendInt(category.ordinal());
        this.appendInt(itemIds.size());
        for (int itemId : itemIds) {
            this.appendInt(itemId);
        }
    }
}
