package outgoing.session.rooms;

import networking.packets.OutgoingPacket;


public class FavoriteRoomsCountComposer extends OutgoingPacket<U> {
    public FavoriteRoomsCountComposer() {
        super(OutgoingHeaders.FavoriteRoomsCountComposer);
        this.appendInt(30);
        this.appendInt(0);
    }
}
