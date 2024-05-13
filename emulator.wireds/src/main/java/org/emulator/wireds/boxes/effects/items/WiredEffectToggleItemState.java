package org.emulator.wireds.boxes.effects.items;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import org.emulator.wireds.boxes.effects.WiredEffect;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.variables.WiredVariable;

public class WiredEffectToggleItemState extends WiredEffect {
    public static final String InteractionName = "wf_act_show_message";

    private static final String TOGGLE_ALL_ITEMS_PARAM = "this.wired.items.toggle-count";
    private static final String TOGGLE_TYPE_PARAM = "this.wired.items.toggle-type";

    public WiredEffectToggleItemState(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);

        this.getInputVariablesManager().getOrCreate(new WiredVariable<Boolean>(
                TOGGLE_ALL_ITEMS_PARAM,
                true
        ));
        this.getInputVariablesManager().getOrCreate(new WiredVariable<Integer>(
                TOGGLE_TYPE_PARAM,
                ToggleType.NEXT.getType()
        ));
    }

    public boolean evaluate(final WiredEvent event) {
        return true;
    }

    @Override
    public int getInterface() {
        return 7;
    }

    public enum ToggleType {
        NEXT(0),
        PREV(1),
        RANDOM(2);

        private final int type;

        ToggleType(final int type) {
            this.type = type;
        }

        public static ToggleType fromType(int type) {
            return switch (type) {
                case 1 -> PREV;
                case 2 -> RANDOM;
                default -> NEXT;
            };
        }

        public int getType() {
            return type;
        }
    }

}


