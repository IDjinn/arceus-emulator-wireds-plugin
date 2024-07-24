package outgoing.rooms.objects.wall;

import habbo.rooms.components.objects.items.wall.IWallItem;
import networking.packets.OutgoingPacket;


public class RemoveWallItemComposer extends OutgoingPacket<U> {

    public RemoveWallItemComposer(IWallItem wallItem, int pickupPlayerId) {
        super(OutgoingHeaders.RemoveWallItemComposer);
        this.appendString(String.valueOf(wallItem.getVirtualId()));
        this.appendInt(pickupPlayerId);
    }
}
