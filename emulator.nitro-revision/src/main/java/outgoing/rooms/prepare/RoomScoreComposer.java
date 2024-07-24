package outgoing.rooms.prepare;

import networking.packets.OutgoingPacket;


public class RoomScoreComposer extends OutgoingPacket<U> {
    public RoomScoreComposer(int score, boolean canVote) {
        super(OutgoingHeaders.RoomScoreComposer);
        this.appendInt(score);
        this.appendBoolean(canVote);
    }
}
