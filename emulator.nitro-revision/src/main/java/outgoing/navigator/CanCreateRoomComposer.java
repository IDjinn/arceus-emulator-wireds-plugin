package outgoing.navigator;

import networking.packets.OutgoingPacket;


public class CanCreateRoomComposer extends OutgoingPacket<U> {
    public CanCreateRoomComposer() {
        super(OutgoingHeaders.CanCreateRoomComposer);

        // TODO
        this.appendInt(0); // 0 = can create room, 1 = room limit reached
        this.appendInt(50); // room limit
    }
}
