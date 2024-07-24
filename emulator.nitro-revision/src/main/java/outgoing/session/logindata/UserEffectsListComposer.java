package outgoing.session.logindata;

import networking.packets.OutgoingPacket;


public class UserEffectsListComposer extends OutgoingPacket<U> {
    public UserEffectsListComposer() {
        super(OutgoingHeaders.UserEffectsListComposer);
        this.appendInt(0);
    }
}
