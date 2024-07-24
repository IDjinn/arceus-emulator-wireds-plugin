package outgoing.session.hotel;

import networking.packets.OutgoingPacket;
import networking.packets.PacketDTO;
import outgoing.OutgoingHeaders;

public class AvailabilityStatusMessageComposer extends OutgoingPacket<PacketDTO> {
    @Override
    public void compose(final PacketDTO dto) {
        this.appendBoolean(true);
        this.appendBoolean(false);
        this.appendBoolean(true);
    }

    @Override
    public int getHeaderId() {
        return OutgoingHeaders.AvailabilityStatusMessageComposer);
    }
}
