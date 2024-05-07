package org.emulator.wireds.boxes.variables;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import habbo.rooms.entities.IRoomEntity;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.messages.outgoing.WiredVariableMessageComposer;

public abstract class WiredVariableItem extends WiredItem {
    public WiredVariableItem(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    @Override
    public void onInteract(final IRoomEntity entity) {
        if (!(entity instanceof IPlayerEntity player))
            return;

        if (!this.getRoom().getRightsManager().hasRights(player.getHabbo()))
            return;

        this.setFlashing(true);
        player.getClient().sendMessage(new WiredVariableMessageComposer(this));
    }
}
