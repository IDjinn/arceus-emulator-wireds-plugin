package org.emulator.wireds.boxes.effects;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.variables.Variable;
import org.emulator.wireds.boxes.util.WiredEvent;

public class WiredEffectMessage extends WiredEffect {
    public static final String InteractionName = "wf_act_show_message";

    public WiredEffectMessage(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);

        this.getContextVariables().put("this.wired.message.text", new Variable(
                "this.wired.message.text",
                "This is a test message"
        ));
    }

    @Override
    public int getInterface() {
        return 7;
    }

    @Override
    public int getMaxSelectionCount() {
        return 0;
    }

    public boolean evaluate(final WiredEvent event) {
        return false;
    }
}
