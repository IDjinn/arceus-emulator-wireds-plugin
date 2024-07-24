package outgoing.rooms.prepare;

import habbo.rooms.RoomRightLevel;
import networking.packets.OutgoingPacket;


public class RoomRightsComposer extends OutgoingPacket<U> {
    public RoomRightsComposer(RoomRightLevel level) {
        super(OutgoingHeaders.RoomRightsComposer);
        this.appendInt(level.ordinal());
    }
}
