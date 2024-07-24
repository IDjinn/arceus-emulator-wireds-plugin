package outgoing.session;

import networking.packets.OutgoingPacket;


public class CfhTopicsMessageComposer extends OutgoingPacket<U> {
    public CfhTopicsMessageComposer() {
        super(OutgoingHeaders.CfhTopicsMessageComposer);
        this.appendInt(0);
    }
}
