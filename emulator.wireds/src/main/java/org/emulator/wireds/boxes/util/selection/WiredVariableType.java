package org.emulator.wireds.boxes.util.selection;

public enum WiredVariableType {
    Box("box"),
    Stack("stack"),
    Signal("signal"),
    Selector("selector"),
    Entity("entity"),
    Room("room"),
    Global("global");

    private final String type;

    WiredVariableType(String type) {
        this.type = type;
    }


    public static WiredVariableType fromLabel(String label) {
        for (WiredVariableType type : WiredVariableType.values()) {
            if (type.getType().equalsIgnoreCase(label)) {
                return type;
            }
        }
        return WiredVariableType.Box;
    }

    public String getType() {
        return this.type;
    }

}