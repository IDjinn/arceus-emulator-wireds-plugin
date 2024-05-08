package org.emulator.wireds.boxes.util;

import java.util.EnumSet;

public enum WiredSelectionType {
    None(0),
    FloorItems(1),
    WallItems(1 << 1),
    Bots(1 << 2),
    Pets(1 << 3),
    ;

    private final int type;

    WiredSelectionType(final int type) {
        this.type = type;
    }


    public static int pack(EnumSet<WiredSelectionType> flags) {
        int packedValue = 0;
        for (WiredSelectionType flag : flags) {
            packedValue |= flag.getType();
        }
        return packedValue;
    }

    public static EnumSet<WiredSelectionType> unpack(int packedValue) {
        EnumSet<WiredSelectionType> flags = EnumSet.noneOf(WiredSelectionType.class);
        for (WiredSelectionType flag : WiredSelectionType.values()) {
            if ((packedValue & flag.getType()) != 0) {
                flags.add(flag);
            }
        }
        return flags;
    }

    public int getType() {
        return this.type;
    }
}
