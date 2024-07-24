package outgoing.rooms.entities;

import habbo.rooms.entities.IRoomEntity;
import networking.packets.OutgoingPacket;


public class RoomUserRemoveComposer extends OutgoingPacket<U> {
    public RoomUserRemoveComposer(IRoomEntity roomEntity) {
        super(OutgoingHeaders.RoomUserRemoveComposer);
        this.appendInt(roomEntity.getVirtualId());
    }
}
