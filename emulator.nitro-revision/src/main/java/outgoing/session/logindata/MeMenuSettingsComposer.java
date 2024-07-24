package outgoing.session.logindata;

import networking.packets.OutgoingPacket;
import networking.packets.PacketDTO;
import networking.packets.PacketWriter;
import outgoing.OutgoingHeaders;

public class MeMenuSettingsComposer implements OutgoingPacket<PacketDTO> { // TODO: USER CONFIGURATION SETTINGS
    @Override
    public void compose(final PacketWriter writer, final PacketDTO dto) {
        writer.appendInt(100);
        writer.appendInt(100);
        writer.appendInt(100);
        writer.appendBoolean(false);
        writer.appendBoolean(false);
        writer.appendBoolean(false);
        writer.appendInt(0);
        writer.appendInt(0);
    }

    @Override
    public int getHeaderId() {
        return OutgoingHeaders.MeMenuSettingsComposer;
    }
}
