package org.emulator.wireds.boxes.util;

public enum StringComparisonType {
    SENTENCE("sentence"),
    STARTS_WITH("stars-with"),
    ENDS_WITH("ends-with"),
    CONTAINS("contains");

    private final String type;

    StringComparisonType(final String type) {
        this.type = type;
    }

    public static StringComparisonType fromType(String type) {
        return switch (type.toLowerCase()) {
            case "sentence" -> SENTENCE;
            case "starts-with" -> STARTS_WITH;
            case "ends-with" -> ENDS_WITH;
            case "contains" -> CONTAINS;
            default -> null;
        };
    }

    public String getType() {
        return type;
    }
}
