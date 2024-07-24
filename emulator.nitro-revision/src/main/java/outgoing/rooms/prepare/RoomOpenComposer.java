package outgoing.rooms.prepare;

import networking.packets.OutgoingPacket;


public class RoomOpenComposer extends OutgoingPacket<U> {

    public RoomOpenComposer() {
        super(OutgoingHeaders.RoomOpenComposer);
    }
}
