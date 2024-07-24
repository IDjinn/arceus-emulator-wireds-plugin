package outgoing.rooms.prepare;

import networking.packets.OutgoingPacket;


public class HideDoorbellComposer extends OutgoingPacket<U> {
    public HideDoorbellComposer() {
        super(OutgoingHeaders.HideDoorbellComposer);
    }
}
