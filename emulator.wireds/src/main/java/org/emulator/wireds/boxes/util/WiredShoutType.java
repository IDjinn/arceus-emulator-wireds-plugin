package org.emulator.wireds.boxes.util;

public enum WiredShoutType {
    NONE("nothing"),
    WHISPER("whisper"),
    TALK("talk"),
    SHOUT("shout"),
    ;


    private final String type;

    WiredShoutType(final String type) {
        this.type = type;
    }

    public static WiredShoutType fromString(final String type) {
        return switch (type.toUpperCase()) {
            case "WHISPER" -> WHISPER;
            case "TALK" -> TALK;
            case "SHOUT" -> SHOUT;
            default -> NONE;
        };
    }

    public String getType() {
        return this.type;
    }
}
