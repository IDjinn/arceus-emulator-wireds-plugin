package org.emulator.wireds.boxes.util.codes;

public enum WiredAddonInterfaceCode implements WiredInterfaceCode {

    ;

    private final int code;

    WiredAddonInterfaceCode(final int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
