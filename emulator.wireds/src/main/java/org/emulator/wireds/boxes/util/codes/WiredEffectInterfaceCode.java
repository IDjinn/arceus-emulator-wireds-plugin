package org.emulator.wireds.boxes.util.codes;

public enum WiredEffectInterfaceCode implements WiredInterfaceCode {
    TOGGLE_ITEM_STATE(0),
    SHOW_MESSAGE(7),

    ;

    private final int code;

    WiredEffectInterfaceCode(final int code) {

        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
