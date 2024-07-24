package outgoing.session.rooms;

import networking.packets.OutgoingPacket;


public class UserHomeRoomComposer extends OutgoingPacket<U> {
    public UserHomeRoomComposer(int homeRoom, int roomToEnter) {
        super(OutgoingHeaders.UserHomeRoomComposer);
        this.appendInt(homeRoom);
        this.appendInt(roomToEnter);
    }
}