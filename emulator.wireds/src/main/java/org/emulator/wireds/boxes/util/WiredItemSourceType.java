package org.emulator.wireds.boxes.util;

public enum WiredItemSourceType {
    Selected(0),
    Selector(1),
    Signal(2),
    Trigger(3);

    private final int type;

    WiredItemSourceType(final int type) {
        this.type = type;
    }

    public static WiredItemSourceType fromType(int type) {
        return switch (type) {
            default -> Selected;
            case 1 -> Selector;
            case 2 -> Signal;
            case 3 -> Trigger;
        };
    }

    public int getType() {
        return this.type;
    }
}
