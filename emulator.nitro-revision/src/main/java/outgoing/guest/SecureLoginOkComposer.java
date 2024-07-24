package outgoing.guest;

import networking.packets.OutgoingPacket;


public class SecureLoginOkComposer extends OutgoingPacket<U> {
    public SecureLoginOkComposer() {
        super(OutgoingHeaders.SecureLoginOKComposer);
    }
}
