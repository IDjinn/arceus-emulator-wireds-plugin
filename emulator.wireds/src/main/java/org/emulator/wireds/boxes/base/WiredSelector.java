package org.emulator.wireds.boxes.base;

import com.google.inject.Inject;
import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItem;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.components.objects.items.wall.IWallItem;
import habbo.rooms.entities.IRoomEntity;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.util.codes.WiredSelectionInterfaceCode;
import org.emulator.wireds.boxes.util.selection.Area;
import org.emulator.wireds.boxes.util.selection.AreaHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class WiredSelector extends WiredItem {
    protected final List<Area> areas;
    protected boolean allowWallItems;

    @Inject
    public WiredSelector(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
        this.areas = new ArrayList<>(1);
    }

    @Override
    public abstract WiredSelectionInterfaceCode getInterface();

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

        return AreaHelper.isItInside(this.areas, entity.getPositionComponent().getPosition());
    }
}
