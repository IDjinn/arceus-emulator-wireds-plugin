package outgoing.session;


public class MysteryBoxKeysComposer extends OutgoingPacket<U> {
    public MysteryBoxKeysComposer() {
        super(OutgoingHeaders.MysteryBoxKeysComposer);
        this.appendString("");
        this.appendString("");
    }
}
