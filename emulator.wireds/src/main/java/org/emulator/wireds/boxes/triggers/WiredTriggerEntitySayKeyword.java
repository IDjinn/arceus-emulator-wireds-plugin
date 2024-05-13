package org.emulator.wireds.boxes.triggers;

import core.events.EventListener;
import core.events.EventListenerPriority;
import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import habbo.rooms.entities.events.RoomEntityTalkEvent;
import org.emulator.wireds.boxes.effects.WiredEffectMessage;
import org.emulator.wireds.boxes.util.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.util.WiredSelectionType;
import org.emulator.wireds.boxes.variables.WiredVariable;
import org.jetbrains.annotations.NotNull;
import packets.outgoing.rooms.entities.chat.RoomUserTalkMessageComposer;
import packets.outgoing.rooms.entities.chat.RoomUserWhisperMessageComposer;

import java.util.EnumSet;
import java.util.Objects;

public class WiredTriggerEntitySayKeyword extends WiredTrigger {
    public static final String InteractionName = "wf_trg_says_something";
    private static final String TRIGGER_SHOUT_TYPE_PARAM = "this.trigger.shout.type";

    public WiredTriggerEntitySayKeyword(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
    }

    @Override
    public int getInterface() {
        return 0;
    }

    @Override
    public int getMaxSelectionCount() {
        return 0;
    }

    @Override
    public EnumSet<WiredSelectionType> getSelectionType() {
        return EnumSet.of(WiredSelectionType.None);
    }

    @EventListener(priority = EventListenerPriority.Low, listenCancelled = false)
    public void onEntityTalk(@NotNull RoomEntityTalkEvent entityTalkEvent) {
        entityTalkEvent.setCancelled(true);
        this.executionPipeline.execute(
                new WiredEvent(
                        entityTalkEvent,
                        this.getPosition(),
                        Objects.hash(
                                entityTalkEvent.entity().getVirtualId(),
                                InteractionName.hashCode(),
                                this.getPosition()
                        )
                ).addTrigger(this)
                        .addEntity(WiredEntitySourceType.Trigger, entityTalkEvent.entity())
        );

        final var triggerShoutTypeVariable =
                (WiredVariable<String>) this.getInputContextVariables().get(TRIGGER_SHOUT_TYPE_PARAM);
        if (triggerShoutTypeVariable == null) return;
        final var triggerShoutType = WiredEffectMessage.WiredShoutType.fromString(triggerShoutTypeVariable.getValue());
        switch (triggerShoutType) {
            case WHISPER:
                if (entityTalkEvent.entity() instanceof IPlayerEntity playerEntity)
                    playerEntity.getClient().sendMessage(new RoomUserWhisperMessageComposer(entityTalkEvent.entity(), entityTalkEvent.message(), 0, 0));
                break;
            case TALK:
                this.getRoom().broadcastMessage(new RoomUserTalkMessageComposer(entityTalkEvent.entity(), entityTalkEvent.message(), 0, 0));
                break;
            case SHOUT:
                this.getRoom().broadcastMessage(new RoomUserTalkMessageComposer(entityTalkEvent.entity(), entityTalkEvent.message(), 0, 0));
                break;
        }
    }
}
