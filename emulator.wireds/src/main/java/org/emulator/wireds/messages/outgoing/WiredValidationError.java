package org.emulator.wireds.messages.outgoing;

import networking.packets.OutgoingPacket;
import org.jetbrains.annotations.Nullable;
import packets.outgoing.OutgoingHeaders;

public class WiredValidationError extends OutgoingPacket<U> {
    public WiredValidationError(String key, String message, @Nullable String helperLink) {
        super(OutgoingHeaders.WiredValidationError);

        this.appendString(key);
        this.appendString(message);
        this.appendString(helperLink);
    }
}
