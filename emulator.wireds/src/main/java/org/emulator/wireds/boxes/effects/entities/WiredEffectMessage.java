package org.emulator.wireds.boxes.effects.entities;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import org.emulator.wireds.boxes.base.WiredEffect;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.util.WiredShoutType;
import org.emulator.wireds.boxes.util.codes.WiredEffectInterfaceCode;
import org.emulator.wireds.boxes.util.selection.WiredEntitySourceType;
import org.emulator.wireds.boxes.variables.WiredVariable;
import packets.outgoing.rooms.entities.chat.RoomUserWhisperMessageComposer;

public class WiredEffectMessage extends WiredEffect {
    public static final String InteractionName = "wf_act_show_message";

    private static final String WIRED_MESSAGE_PARAM = "this.wired.effect.message.text";
    private static final String SHOUT_TYPE_PARAM = "this.wired.effect.message.shout.type";

    public WiredEffectMessage(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);

        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                SHOUT_TYPE_PARAM,
                WiredShoutType.WHISPER.ordinal()
        ));
        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                WIRED_MESSAGE_PARAM,
                ""
        ));
    }

    public boolean evaluate(final WiredEvent event) {
        final var shoutTypeVariable = this.getInputVariablesManager().get(SHOUT_TYPE_PARAM, String.class);
        final var type = WiredShoutType.fromString(shoutTypeVariable.getValue());
        if (type.equals(WiredShoutType.NONE)) return false;

        final var messageVariable = this.getInputVariablesManager().get(WIRED_MESSAGE_PARAM, String.class);
        final var message = event.handleVariables(messageVariable.getValue());
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
    public WiredEffectInterfaceCode getInterface() {
        return WiredEffectInterfaceCode.SHOW_MESSAGE;
    }

    @Override
    public int getMaxSelectionCount() {
        return 0;
    }
}
