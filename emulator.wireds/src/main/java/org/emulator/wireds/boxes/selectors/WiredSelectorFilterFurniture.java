package org.emulator.wireds.boxes.selectors;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItem;
import habbo.rooms.components.objects.items.IRoomItemData;
import org.emulator.wireds.boxes.base.WiredSelector;
import org.emulator.wireds.boxes.util.codes.WiredSelectionInterfaceCode;

public class WiredSelectorFilterFurniture extends WiredSelector {
    public static final String InteractionName = "wf_act_show_messaasdasdge";
    public WiredSelectorFilterFurniture(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    @Override
    public WiredSelectionInterfaceCode getInterface() {
        return null;
    }

    @Override
    protected boolean filterItem(final IRoomItem item) {
        var count = 0;
        if (super.filterItem(item))
            count++;
        
        return count < 10;
    }
}
