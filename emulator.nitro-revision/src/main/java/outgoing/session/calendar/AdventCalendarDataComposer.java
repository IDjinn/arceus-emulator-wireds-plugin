package outgoing.session.calendar;

import networking.packets.OutgoingPacket;


public class AdventCalendarDataComposer extends OutgoingPacket<U> {
    public AdventCalendarDataComposer() {
        super(OutgoingHeaders.AdventCalendarDataComposer);
        this.appendString("xmas14");
        this.appendString("");
        this.appendInt(0);
        this.appendInt(0);
        this.appendInt(0);
        this.appendInt(0);
    }
}
