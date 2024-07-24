package outgoing.achievements;

import habbo.habbos.IHabbo;
import networking.packets.OutgoingPacket;


public class AchievementListComposer extends OutgoingPacket<U> {
    public AchievementListComposer(IHabbo habbo) {
        super(OutgoingHeaders.AchievementListComposer);
        this.appendInt(0);
    }
}
