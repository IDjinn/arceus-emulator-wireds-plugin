package outgoing.inventory;

import networking.packets.OutgoingPacket;


public class RemoveHabboItemComposer extends OutgoingPacket<U> {
    public RemoveHabboItemComposer(Integer itemId) {
        super(OutgoingHeaders.RemoveHabboItemComposer);
        this.appendInt(itemId);
    }
}
