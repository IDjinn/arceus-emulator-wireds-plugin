package org.emulator.wireds.boxes.util;

public enum TriggerEntitiesType {
    ENTITY("entity"),
    OWNER("owner"),
    PLAYER("player"),
    BOT("bot"),
    PET("pet"),
    ;

    private final String entity;

    TriggerEntitiesType(final String entity) {
        this.entity = entity;
    }

    public static TriggerEntitiesType get(final String entity) {
        for (final TriggerEntitiesType type : TriggerEntitiesType.values()) {
            if (type.getEntity().equalsIgnoreCase(entity)) {
                return type;
            }
        }
        return TriggerEntitiesType.ENTITY;
    }

    public String getEntity() {
        return entity;
    }
}
