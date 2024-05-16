package org.emulator.wireds.boxes.util.codes;

public enum WiredSelectionInterfaceCode implements WiredInterfaceCode {

    ;

    private final int code;

    WiredSelectionInterfaceCode(final int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
