package outgoing.rooms.prepare;

import networking.packets.OutgoingPacket;


public class RoomPaintComposer extends OutgoingPacket<U> {
    public RoomPaintComposer(String type, String value) {
        super(OutgoingHeaders.RoomPaintComposer);
        this.appendString(type);
        this.appendString(value);
    }
}
