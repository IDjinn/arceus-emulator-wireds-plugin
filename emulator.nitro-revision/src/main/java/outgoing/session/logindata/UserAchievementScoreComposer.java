package outgoing.session.logindata;

import networking.packets.OutgoingPacket;


public class UserAchievementScoreComposer extends OutgoingPacket<U> {
    public UserAchievementScoreComposer() {
        super(OutgoingHeaders.UserAchievementScoreComposer);
        this.appendInt(100);
    }
}
