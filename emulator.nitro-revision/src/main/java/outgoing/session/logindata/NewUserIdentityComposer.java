package outgoing.session.logindata;

import networking.packets.OutgoingPacket;


public class NewUserIdentityComposer extends OutgoingPacket<U> {
    public NewUserIdentityComposer() {
        super(OutgoingHeaders.NewUserIdentityComposer);
        this.appendInt(1);
    }
}
