package org.emulator.wireds.boxes.effects;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import habbo.rooms.entities.IRoomEntity;
import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.messages.outgoing.WiredEffectMessageComposer;

public abstract class WiredEffect extends WiredItem {
    public WiredEffect(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    public abstract boolean evaluate(final WiredEvent event);

    public int getDelay() {
        return 0;
    }

    @Override
    public void serializeWiredExtraStuff(final OutgoingPacket packet) {
        packet.appendInt(this.getDelay());
    }

    @Override
    public void onInteract(final IRoomEntity entity) {
        if (!(entity instanceof IPlayerEntity player))
            return;

        if (!this.getRoom().getRightsManager().hasRights(player.getHabbo()))
            return;

        this.setFlashing(true);
        player.getClient().sendMessage(new WiredEffectMessageComposer(this));
    }
}
