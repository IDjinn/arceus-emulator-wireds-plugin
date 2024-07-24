package outgoing.session.logindata;

import networking.packets.OutgoingPacket;
import networking.packets.PacketWriter;
import outgoing.OutgoingHeaders;
import packets.dto.outgoing.session.logindata.UserDataDTO;


public class UserDataComposer implements OutgoingPacket<UserDataDTO> {
    @Override
    public void compose(final PacketWriter writer, final UserDataDTO dto) {
        final var habbo = dto.habbo();

        writer.appendInt(habbo.getData().getId());
        writer.appendString(habbo.getData().getUsername());
        writer.appendString(habbo.getData().getLook());
        writer.appendString(habbo.getData().getGender());
        writer.appendString(habbo.getData().getMotto());
        writer.appendString(habbo.getData().getUsername());
        writer.appendBoolean(false);
        writer.appendInt(habbo.getSettings().getRespectPointsReceived());
        writer.appendInt(habbo.getSettings().getRespectPointsGiven());
        writer.appendInt(habbo.getSettings().getPetRespectPointsToGive());
        writer.appendBoolean(false);
        writer.appendString("01-01-1970 00:00:00");
        writer.appendBoolean(habbo.getSettings().allowNameChange());
        writer.appendBoolean(false);
    }

    @Override
    public int getHeaderId() {
        return OutgoingHeaders.UserDataComposer;
    }

}
