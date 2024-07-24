package outgoing.purse;

import habbo.habbos.IHabbo;
import networking.packets.OutgoingPacket;


public class UserCreditsComposer extends OutgoingPacket<U> {
    public UserCreditsComposer(IHabbo habbo) {
        super(OutgoingHeaders.UserCreditsComposer);

        this.appendString(STR."\{habbo.getData().getCredits()}");
    }
}
