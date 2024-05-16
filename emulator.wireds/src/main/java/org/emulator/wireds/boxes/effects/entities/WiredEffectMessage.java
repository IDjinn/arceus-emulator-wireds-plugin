package org.emulator.wireds.boxes.effects.entities;

import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import org.emulator.wireds.boxes.base.WiredEffect;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.util.codes.WiredEffectInterfaceCode;
import org.emulator.wireds.boxes.util.selection.WiredEntitySourceType;
import org.emulator.wireds.boxes.variables.WiredVariable;
import packets.outgoing.rooms.entities.chat.RoomUserWhisperMessageComposer;

public class WiredEffectMessage extends WiredEffect {
    public static final String InteractionName = "wf_act_show_message";

    private static final String WIRED_MESSAGE_PARAM = "this.wired.effect.message.text";

    public WiredEffectMessage(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);

        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                WIRED_MESSAGE_PARAM,
                ""
        ));
    }

    public boolean evaluate(final WiredEvent event) {
        final var messageVariable = this.getInputVariablesManager().get(WIRED_MESSAGE_PARAM, String.class);
        final var message = event.handleVariables(messageVariable.getValue());
        for (final var entity : event.getEntities(WiredEntitySourceType.Trigger)) {
            if (entity instanceof IPlayerEntity playerEntity) {
                playerEntity.getClient().sendMessage(new RoomUserWhisperMessageComposer(entity, message));
            }
        }

        return true;
    }

    @Override
    public WiredEffectInterfaceCode getInterface() {
        return WiredEffectInterfaceCode.SHOW_MESSAGE;
    }
}
