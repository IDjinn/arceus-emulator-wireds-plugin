package outgoing.session.logindata;

import networking.packets.OutgoingPacket;


public class UserPermissionsComposer extends OutgoingPacket<U> {
    public UserPermissionsComposer() {
        super(OutgoingHeaders.UserPermissionsComposer);
        this.appendInt(2);
        this.appendInt(1);
        this.appendBoolean(false);
    }
}
