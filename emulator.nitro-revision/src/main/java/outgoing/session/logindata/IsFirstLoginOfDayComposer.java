package outgoing.session.logindata;


import networking.packets.OutgoingPacket;
import networking.packets.PacketDTO;
import networking.packets.PacketWriter;
import outgoing.OutgoingHeaders;

public class IsFirstLoginOfDayComposer implements OutgoingPacket<PacketDTO> {
    @Override
    public void compose(final PacketWriter writer, final PacketDTO dto) {
        writer.appendBoolean(true);
    }

    @Override
    public int getHeaderId() {
        return OutgoingHeaders.IsFirstLoginOfDayComposer;
    }
}
