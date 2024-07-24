package outgoing.rooms.prepare;

import networking.packets.OutgoingPacket;


public class HotelViewComposer extends OutgoingPacket<U> {
    public HotelViewComposer() {
        super(OutgoingHeaders.HotelViewComposer);
    }
}
