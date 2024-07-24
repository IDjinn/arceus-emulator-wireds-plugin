package outgoing.navigator;

import networking.packets.OutgoingPacket;


public class NewNavigatorLiftedRoomsComposer extends OutgoingPacket<U> {
    public NewNavigatorLiftedRoomsComposer() {
        super(OutgoingHeaders.NewNavigatorLiftedRoomsComposer);

        this.appendInt(0);
    }
}
