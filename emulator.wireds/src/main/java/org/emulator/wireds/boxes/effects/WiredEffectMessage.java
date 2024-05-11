package org.emulator.wireds.boxes.effects;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import org.emulator.wireds.boxes.util.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.util.WiredVariableContextType;
import org.emulator.wireds.boxes.variables.WiredVariable;
import packets.outgoing.rooms.entities.chat.RoomUserWhisperMessageComposer;

public class WiredEffectMessage extends WiredEffect {
    public static final String InteractionName = "wf_act_show_message";

    private static final String WIRED_MESSAGE_PARAM = "this.wired.message.text";
    private static final String SHOUT_TYPE_PARAM = "this.wired.message.shout-type";

    public boolean evaluate(final WiredEvent event) {
        final var message = event.handleVariables(this.getOutputContextVariables().get("this.wired.message" +
                ".text").getValue());
        final var entities = event.getEntities(WiredEntitySourceType.Trigger);
        for (final var entity : entities) {
            if (entity instanceof IPlayerEntity playerEntity) {
                playerEntity.getClient().sendMessage(new RoomUserWhisperMessageComposer(entity, message));
            }
        }
        return true;
    }
    
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

    public enum WiredShoutType {
        NONE(0),
        WHISPER(1),
        TALK(2),
        SHOUT(3),
        ;

        private final int type;

        WiredShoutType(final int type) {
            this.type = type;
        }

        public static WiredShoutType fromType(int type) {
            return switch (type) {
                default -> NONE;
                case 1 -> WHISPER;
                case 2 -> TALK;
                case 3 -> SHOUT;
            };
        }

        public int getType() {
            return type;
        }
    }
}
