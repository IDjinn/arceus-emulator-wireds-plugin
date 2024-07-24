package outgoing.rooms.prepare;

import networking.packets.OutgoingPacket;


public class RoomModelComposer extends OutgoingPacket<U> {
    public RoomModelComposer(String modelName, int roomId) {
        super(OutgoingHeaders.RoomModelComposer);
        this.appendString(modelName);
        this.appendInt(roomId);
    }
}
