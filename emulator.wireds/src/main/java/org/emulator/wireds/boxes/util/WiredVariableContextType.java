package org.emulator.wireds.boxes.util;

import org.jetbrains.annotations.Nullable;

public enum WiredVariableContextType {
    Stack("Stack"),
    Signal("Signal"),
    Selector("Selector"),
    Entity("Entity"),
    Room("Room"),
    Global("Global"),
    Other("Other");

    private final String type;
    private @Nullable
    final String key;

    WiredVariableContextType(String type) {
        this.type = type;
        this.key = null;
    }

    WiredVariableContextType(String type, final @Nullable String key) {
        this.type = type;
        this.key = key;
    }

    public static WiredVariableContextType fromLabel(String label) {
        for (WiredVariableContextType type : WiredVariableContextType.values()) {
            if (type.getType().equalsIgnoreCase(label)) {
                return type;
            }
        }
        return WiredVariableContextType.Other;
    }

    public String getType() {
        return this.type;
    }

    public @Nullable String getKey() {
        return this.key;
    }
}