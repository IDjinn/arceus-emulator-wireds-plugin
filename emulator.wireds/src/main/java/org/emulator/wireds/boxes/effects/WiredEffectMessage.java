package org.emulator.wireds.boxes.effects;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import org.emulator.wireds.boxes.util.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.WiredEvent;
import packets.outgoing.rooms.entities.chat.RoomUserWhisperMessageComposer;

public class WiredEffectMessage extends WiredEffect {
    public static final String InteractionName = "wf_act_show_message";

    private static final String WIRED_MESSAGE_PARAM = "this.wired.message.text";
    private static final String SHOUT_TYPE_PARAM = "this.wired.message.shout.type";

    public WiredEffectMessage(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }
    
    public boolean evaluate(final WiredEvent event) {
        final var type = WiredShoutType.fromString(this.getInputContextVariables().get(SHOUT_TYPE_PARAM).getValue());
        if (type.equals(WiredShoutType.NONE)) return false;

        final var message = event.handleVariables(this.getInputContextVariables().get(WIRED_MESSAGE_PARAM).getValue());
        for (final var entity : event.getEntities(WiredEntitySourceType.Trigger)) {
            if (entity instanceof IPlayerEntity playerEntity) {
                switch (type) {
                    case WHISPER:
                        playerEntity.getClient().sendMessage(new RoomUserWhisperMessageComposer(entity, message));
                        break;
                    case TALK:
                        this.getRoom().getEntityManager().talk(entity, message, 0);
                        break;
                    case SHOUT:
                        this.getRoom().getEntityManager().shout(entity, message, 0);
                        break;
                }
            }
        }
        
        return true;
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

        public static WiredShoutType fromString(String type) {
            return switch (type.toUpperCase()) {
                case "1" -> WHISPER;
                case "2" -> TALK;
                case "3" -> SHOUT;
                default -> NONE;
            };
        }

        public int getType() {
            return type;
        }
    }
}
