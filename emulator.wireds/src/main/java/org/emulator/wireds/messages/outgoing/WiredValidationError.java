package org.emulator.wireds.messages.outgoing;

import networking.packets.IOutgoingPacket;
import org.jetbrains.annotations.Nullable;
import packets.outgoing.OutgoingHeaders;

public class WiredValidationError extends IOutgoingPacket {
    public WiredValidationError(String key, String message, @Nullable String helperLink) {
        super(OutgoingHeaders.WiredValidationError);

        this.appendString(key);
        this.appendString(message);
        this.appendString(helperLink);
    }
}
