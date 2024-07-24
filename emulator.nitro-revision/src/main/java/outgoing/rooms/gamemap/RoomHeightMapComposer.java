package outgoing.rooms.gamemap;

import habbo.rooms.components.gamemap.IRoomGameMap;
import networking.packets.OutgoingPacket;


public class RoomHeightMapComposer extends OutgoingPacket<U> {
    public RoomHeightMapComposer(IRoomGameMap gameMap) {
        super(OutgoingHeaders.RoomHeightMapComposer);
        this.appendBoolean(true, "scale (true ? 32 : 64");
        this.appendInt(1, "wallHeight"); // TODO this
        this.appendString(gameMap.getModelMap());
    }
}
