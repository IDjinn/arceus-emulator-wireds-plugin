package org.emulator.wireds.boxes.selectors;

import com.google.inject.Inject;
import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItem;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.components.objects.items.wall.IWallItem;
import habbo.rooms.entities.IRoomEntity;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.util.Area;
import org.emulator.wireds.boxes.util.AreaHelper;
import org.emulator.wireds.boxes.util.WiredEvent;

import java.util.ArrayList;
import java.util.List;

public class WiredSelector extends WiredItem {
    protected final List<Area> areas;
    protected boolean allowWallItems;

    @Inject
    public WiredSelector(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
        this.areas = new ArrayList<>(1);
    }

    @Override
    public int getInterface() {
        return 0;
    }

    @Override
    public int getMaxSelectionCount() {
        return 0;
    }

    public void filterEvent(final WiredEvent event) {
        this.filterItems(event);
        this.filterEntities(event);
    }

    protected void filterItems(final WiredEvent event) {
        event.getItems().get(this.getWiredItemSourceType()).removeIf(item -> !this.filterItem(item));
    }
    
    protected void filterEntities(final WiredEvent event) {
        event.getEntities().get(this.getWiredEntitiesSourceType()).removeIf(item -> !this.filterEntity(item));
    }

    protected boolean filterItem(final IRoomItem item) {
        if (item instanceof IWallItem)
            return this.allowWallItems;
        
        if (this.areas.isEmpty())
            return true;
        
        return AreaHelper.isItInside(this.areas, item.getItemData().getPosition());
    }

    protected boolean filterEntity(final IRoomEntity entity) {
        if (this.areas.isEmpty())
            return true;
        
        return AreaHelper.isItInside(this.areas, entity.getPosition());
    }
}
