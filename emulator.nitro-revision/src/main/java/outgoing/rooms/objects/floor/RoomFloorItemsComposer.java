package outgoing.rooms.objects.floor;

import habbo.furniture.FurnitureUsagePolicy;
import habbo.rooms.components.objects.items.floor.IFloorFloorItem;
import networking.packets.OutgoingPacket;

import java.util.Collection;
import java.util.List;

public class RoomFloorItemsComposer extends OutgoingPacket<U> {
    public RoomFloorItemsComposer(List<String> owners, Collection<? extends IFloorFloorItem> allItems) {
        super(OutgoingHeaders.RoomFloorItemsComposer);
        this.appendInt(owners.size());
        for (var i = 0; i < owners.size(); i++) {
            this.appendInt(i);
            this.appendString(owners.get(i));
        }

        this.appendInt(allItems.size());
        for (var item : allItems) {
            item.serializeItemIdentity(this);
            item.serializePosition(this);

            this.appendInt(1, "gift, song or something. It seems to be the extraData state (integer) of legacy data"); // TODO

            item.getExtraData().serialize(this);
            this.appendInt(-1, "expiration timeout");
            this.appendInt(FurnitureUsagePolicy.Controller.ordinal()); // TODO:FURNITURE USAGE

            if (item.getOwnerData() != null && item.getOwnerData().isPresent()) {
                var owner = item.getOwnerData().get();
                this.appendInt(owner.getId());
            } else {
                this.appendInt(0);
            }
        }
    }
}