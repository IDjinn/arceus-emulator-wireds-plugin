package org.emulator.wireds.boxes.selectors;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItem;
import habbo.rooms.components.objects.items.IRoomItemData;

public class WiredSelectorFilterFurniture extends WiredSelector {
    public static final String InteractionName = "wf_act_show_message";
    public WiredSelectorFilterFurniture(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    @Override
    protected boolean filterItem(final IRoomItem item) {
        var count = 0;
        if (super.filterItem(item))
            count++;
        
        return count < 10;
    }
}
