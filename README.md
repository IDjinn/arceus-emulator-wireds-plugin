# Arceus Emulator Wireds Plugin

The `arceus-emulator-wireds-plugin` is a modular extension for the Arceus Emulator, designed to handle wired functionalities through an efficient pipeline system. This plugin leverages dependency injection and a robust execution pipeline, ensuring flexibility and extensibility.

## Key Features

- Wireds will be executed by a `WiredEvent` class, including all triggers, selectors, effects, furniture, entities so on.
- Processes wired interactions using a multi-step execution pipeline.
- Once execution per `WiredEvent`, ensured by hashing trigger's objects (see  [Wired Box](#Wired-Box)).
- Supports variable handling, condition checking, effect evaluation, and addons.
- Each wired is triggered by events from the room's event handler.

## Execution Pipeline

The execution of wired interactions is handled by the `WiredExecutionPipeline`, which operates in multiple steps. This design ensures a clear flow for processing events and interactions.

### Steps in the Pipeline:

1. Handle Variables
2. Handle Selectors
3. Handle Conditions
4. Get Effect List
5. Handle Addons
6. Handle Effects

### Pipeline

```java
public WiredExecutionPipeline(WiredManager wiredManager) {
    this.wiredManager = wiredManager;
    this.executingStacks = new ConcurrentHashMap<>();
    
    this.addStep("handle-variables", ctx -> {
        for (final var wired : this.wiredManager.getWireds().values()) {
            if (!wired.getPosition().equals(ctx.getEvent().getTriggerPosition())) continue;

            for (final var variable : wired.getOutputVariablesManager().getVariables().values()) {
                if (variable.getContextType().equals(WiredVariableType.Box)) continue;
                ctx.getEvent().addVariable(variable.getContextType(), variable);
            }
        }
        return ctx;
    });

    this.addStep("handle-selectors", ctx -> {
        final var selectors = this.wiredManager.getSelectorsAt(ctx.getEvent().getTriggerPosition());
        for (final var selector : selectors) {
            selector.filterEvent(ctx.getEvent());
            ctx.getEvent().addSelector(selector);
        }
        return ctx;
    });

    this.addStep("handle-conditions", ctx -> {
        final var conditions = this.wiredManager.getConditionsAt(ctx.getEvent().getTriggerPosition());
        final var conditionsOrIter = conditions.stream()
            .filter(condition -> condition.getLogicalType().equals(LogicalType.OR)).iterator();
        
        while (conditionsOrIter.hasNext()) {
            final var condition = conditionsOrIter.next();
            if (condition.matches(ctx.getEvent())) {
                ctx.getEvent().addCondition(condition);
                conditionsOrIter.remove();
                break;
            }
            conditionsOrIter.remove();
        }

        for (final var condition : conditions) {
            if (!condition.matches(ctx.getEvent())) {
                ctx.fail(true);
                break;
            }
            ctx.getEvent().addCondition(condition);
        }

        return ctx;
    });

    this.addStep("get-effect-list", ctx -> {
        final var effects = this.wiredManager.getEffectsAt(ctx.getEvent().getTriggerPosition());
        ctx.getEvent().addEffects(effects);
        return ctx;
    });

    this.addStep("handle-addons", ctx -> {
        // unique, random etc...
        return ctx;
    });

    this.addStep("handle-effects", ctx -> {
        for (final var effect : ctx.getEvent().getEffects()) {
            effect.evaluate(ctx.getEvent());
        }
        return ctx;
    });
}
```

### Wired Box
```java
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
            case TALK, SHOUT:
                this.getRoom().broadcastMessage(new RoomUserTalkMessageComposer(entityTalkEvent.entity(), entityTalkEvent.message(), 0, 0));
                break;
        }
    }
```

### Execution

```java
public Optional<IPipelineContext<WiredEvent>> execute(final WiredEvent event) {
        if (this.executingStacks.containsKey(event.hashCode())) {
            logger.trace("wireds is already executing: {} {}",
                    event.getTriggers().getFirst().getClass().getSimpleName(),
                    event
            );
            return Optional.empty();
        }

        try {
            this.injector.injectMembers(event);
            final var future = this.threadManager.getSoftwareThreadExecutor().submit(() -> super.execute(event));
            this.executingStacks.put(event.hashCode(), future);
            return future.get();
        } catch (Exception e) {
            logger.error("error while executing wireds: {}", e.getMessage(), e);
            return Optional.empty();
        } finally {
            this.executingStacks.remove(event.hashCode());
        }
    }
```

### Contributing
Contributions are welcome! Please follow the coding standards and submit pull requests with clear descriptions of your changes.
