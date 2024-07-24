package outgoing.rooms.objects.wall;

import habbo.furniture.FurnitureUsagePolicy;
import habbo.rooms.components.objects.items.wall.IWallItem;
import networking.packets.OutgoingPacket;


public class WallItemUpdateComposer extends OutgoingPacket<U> {

    public WallItemUpdateComposer(IWallItem wallItem) {
        super(OutgoingHeaders.WallItemUpdateComposer);
        wallItem.serializeItemIdentity(this);
        wallItem.serializePosition(this);
        wallItem.getExtraData().serializeState(this);
        this.appendInt(-1, "rent?");
        this.appendInt(FurnitureUsagePolicy.Everyone.ordinal()); // TODO
        this.appendInt(wallItem.getOwnerData().isPresent() ? wallItem.getOwnerData().get().getId() : -1);
        this.appendString(wallItem.getOwnerData().isPresent() ? wallItem.getOwnerData().get().getUsername() : "");
    }
}
