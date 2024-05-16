package org.emulator.wireds.boxes.base;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import habbo.rooms.entities.IRoomEntity;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.util.codes.WiredTriggerInterfaceCode;
import org.emulator.wireds.messages.outgoing.WiredTriggerMessageComposer;

public abstract class WiredTrigger extends WiredItem {
    public WiredTrigger(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    @Override
    public abstract WiredTriggerInterfaceCode getInterface();

    @Override
    public void onInteract(final IRoomEntity entity) {
        if (!(entity instanceof IPlayerEntity player))
            return;

        if (!player.hasRights())
            return;

        this.setFlashing(true);
        player.getClient().sendMessage(new WiredTriggerMessageComposer(this));
    }

    public abstract int getTriggerHash();
}
