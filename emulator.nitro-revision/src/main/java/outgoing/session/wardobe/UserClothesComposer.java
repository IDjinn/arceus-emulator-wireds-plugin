package outgoing.session.wardobe;

import networking.packets.OutgoingPacket;


public class UserClothesComposer extends OutgoingPacket<U> {
    public UserClothesComposer() {
        super(OutgoingHeaders.UserClothesComposer);
        this.appendInt(0);
        this.appendInt(0);
    }
}
