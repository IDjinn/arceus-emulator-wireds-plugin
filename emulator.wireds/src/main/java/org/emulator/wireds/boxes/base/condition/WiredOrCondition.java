package org.emulator.wireds.boxes.base.condition;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import org.emulator.wireds.boxes.util.WiredEvent;

public abstract class WiredOrCondition extends WiredCondition {
    public WiredOrCondition(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    public abstract boolean matches(final WiredEvent event);
}
