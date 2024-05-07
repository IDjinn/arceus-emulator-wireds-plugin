package org.emulator.wireds.boxes.effects;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.util.WiredVariableContextType;
import org.emulator.wireds.boxes.variables.WiredVariable;

public class WiredEffectMessage extends WiredEffect {
    public static final String InteractionName = "wf_act_show_message";

    public WiredEffectMessage(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);

        this.getInputContextVariables().put("this.wired.message.text", new WiredVariable(
                WiredVariableContextType.Stack,
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
