package org.emulator.wireds.boxes.triggers;

import core.events.EventListener;
import core.events.EventListenerPriority;
import habbo.furniture.IFurniture;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.entities.IPlayerEntity;
import habbo.rooms.entities.events.RoomEntityTalkEvent;
import org.emulator.wireds.boxes.base.WiredTrigger;
import org.emulator.wireds.boxes.util.StringComparisonType;
import org.emulator.wireds.boxes.util.WiredEvent;
import org.emulator.wireds.boxes.util.WiredShoutType;
import org.emulator.wireds.boxes.util.codes.WiredTriggerInterfaceCode;
import org.emulator.wireds.boxes.util.selection.WiredEntitySourceType;
import org.emulator.wireds.boxes.variables.WiredVariable;
import org.jetbrains.annotations.NotNull;
import packets.outgoing.rooms.entities.chat.RoomUserTalkMessageComposer;
import packets.outgoing.rooms.entities.chat.RoomUserWhisperMessageComposer;

import java.util.Objects;

public class WiredTriggerEntitySayKeyword extends WiredTrigger {
    public static final String InteractionName = "wf_trg_says_something";
    private static final String TRIGGER_SHOUT_TYPE_PARAM = "this.wired.trigger.shout.type";
    private static final String TRIGGER_MESSAGE = "this.wired.trigger.message.text";
    private static final String TRIGGER_ENTITIES_TYPE = "this.wired.trigger.entities.type";
    private static final String TRIGGER_MESSAGE_SHOUT_TYPE = "this.wired.trigger.message.shout.type";
    private static final String STRING_COMPARISON_TYPE = "this.wired.trigger.message.comparison.type";
    private static final String MATCH_CASE_TYPE = "this.wired.trigger.message.match.type";

    public WiredTriggerEntitySayKeyword(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);

        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                TRIGGER_SHOUT_TYPE_PARAM,
                WiredShoutType.WHISPER.getType()
        ));
        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                TRIGGER_MESSAGE,
                ""
        ));
        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                TRIGGER_ENTITIES_TYPE,
                WiredShoutType.WHISPER.getType()
        ));
        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                TRIGGER_MESSAGE_SHOUT_TYPE,
                WiredShoutType.WHISPER.getType()
        ));
        this.getInputVariablesManager().getOrCreate(new WiredVariable<>(
                STRING_COMPARISON_TYPE,
                StringComparisonType.SENTENCE.getType()
        ));
    }

    @Override
    public WiredTriggerInterfaceCode getInterface() {
        return WiredTriggerInterfaceCode.TRIGGER_SAY_KEYWORD;
    }

    @Override
    public int getTriggerHash() {
        return Objects.hash(
                InteractionName.hashCode(),
                this.getInputVariablesManager().get(TRIGGER_MESSAGE).hashCode(),
                this.getPosition().hashCode()
        );
    }

    @EventListener(priority = EventListenerPriority.Low, listenCancelled = false)
    public void onEntityTalk(@NotNull RoomEntityTalkEvent entityTalkEvent) {
        entityTalkEvent.setCancelled(true);
        this.executionPipeline.execute(new WiredEvent(
                        entityTalkEvent,
                        this.getPosition(),
                        Objects.hash(
                                entityTalkEvent.entity().getVirtualId(),
                                this.getTriggerHash()
                        )
                ).addTrigger(this)
                        .addEntity(WiredEntitySourceType.Trigger, entityTalkEvent.entity())
        );

        final var triggerShoutTypeVariable = this.getInputVariablesManager().get(TRIGGER_SHOUT_TYPE_PARAM, String.class);
        final var triggerShoutType = WiredShoutType.fromString(triggerShoutTypeVariable.getValue());
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