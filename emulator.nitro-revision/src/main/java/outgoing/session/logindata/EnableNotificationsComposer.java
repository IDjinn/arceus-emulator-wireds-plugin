package outgoing.session.logindata;


import networking.packets.OutgoingPacket;
import networking.packets.PacketDTO;
import outgoing.OutgoingHeaders;

public class EnableNotificationsComposer extends OutgoingPacket<PacketDTO> {
    @Override
    public void compose(final PacketDTO dto) {
        this.appendBoolean(true);
    }

    @Override
    public int getHeaderId() {
        return OutgoingHeaders.EnableNotificationsComposer;
    }
}
