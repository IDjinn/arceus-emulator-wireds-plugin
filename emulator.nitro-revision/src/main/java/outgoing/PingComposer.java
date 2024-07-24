package outgoing;


import networking.packets.OutgoingPacket;
import networking.packets.PacketDTO;

public class PingComposer extends OutgoingPacket<PacketDTO> {
    @Override
    public void compose(final PacketDTO dto) {
    }

    @Override
    public int getHeaderId() {
        return 3928;
    }
}
