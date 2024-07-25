package org.emulator.wireds.messages.outgoing;

import networking.packets.outgoing.IOutgoingDTOSerializer;
import org.jetbrains.annotations.Nullable;
import packets.outgoing.OutgoingHeaders;

public class WiredValidationError extends IOutgoingDTOSerializer {
    public WiredValidationError(String key, String message, @Nullable String helperLink) {
        super(OutgoingHeaders.WiredValidationError);

        this.appendString(key);
        this.appendString(message);
        this.appendString(helperLink);
    }
}
