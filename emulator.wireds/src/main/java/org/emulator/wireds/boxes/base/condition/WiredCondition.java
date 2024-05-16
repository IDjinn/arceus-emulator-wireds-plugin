package org.emulator.wireds.boxes.base.condition;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.util.WiredEvent;

public abstract class WiredCondition extends WiredItem {
    public WiredCondition(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    public abstract boolean matches(final WiredEvent event);

    public abstract LogicalType getLogicalType();

    public abstract ConditionType getConditionType();
}


