package org.emulator.wireds.boxes.util.codes;

public enum WiredTriggerInterfaceCode implements WiredInterfaceCode {
    TRIGGER_SAY_KEYWORD(0),
    ;

    private final int code;

    WiredTriggerInterfaceCode(final int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
