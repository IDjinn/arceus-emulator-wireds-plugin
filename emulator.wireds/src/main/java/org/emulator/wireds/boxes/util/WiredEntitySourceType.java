package org.emulator.wireds.boxes.util;

public enum WiredEntitySourceType {
    Selector(0),
    Signal(1),
    Trigger(2);

    private final int type;

    WiredEntitySourceType(final int type) {
        this.type = type;
    }

    public static WiredEntitySourceType fromType(int type) {
        return switch (type) {
            default -> Selector;
            case 1 -> Signal;
            case 2 -> Trigger;
        };
    }

    public int getType() {
        return this.type;
    }
}
