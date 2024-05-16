package org.emulator.wireds.component;

import com.google.inject.Inject;
import com.google.inject.Injector;
import core.events.Event;
import habbo.rooms.IRoom;
import habbo.rooms.IRoomComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.base.WiredAddon;
import org.emulator.wireds.boxes.base.WiredEffect;
import org.emulator.wireds.boxes.base.WiredSelector;
import org.emulator.wireds.boxes.base.WiredTrigger;
import org.emulator.wireds.boxes.base.condition.WiredCondition;
import storage.repositories.rooms.IRoomItemsRepository;
import utils.pathfinder.Position;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WiredManager implements IRoomComponent {
    private static final Logger LOGGER = LogManager.getLogger();
    private final IRoom room;
    private final WiredExecutionPipeline executionPipeline;
    private final Map<Integer, WiredItem> wireds;
    private final Map<Integer, WiredTrigger> triggers;
    private final Map<Integer, WiredCondition> conditions;
    private final Map<Integer, WiredEffect> effects;
    private final Map<Integer, WiredAddon> addons;
    private final Map<Integer, WiredSelector> selectors;
    @Inject
    private Injector injector;
    private static final String UPDATE_WIRED_SQL = """
            UPDATE  `items`
            SET `wired_data` = ?
            WHERE `id` = ?;
            """;

    public WiredManager(IRoom room) {
        this.room = room;
        this.wireds = new ConcurrentHashMap<>();
        this.triggers = new ConcurrentHashMap<>();
        this.conditions = new ConcurrentHashMap<>();
        this.effects = new ConcurrentHashMap<>();
        this.addons = new ConcurrentHashMap<>();
        this.selectors = new ConcurrentHashMap<>();
        this.executionPipeline = new WiredExecutionPipeline(this);
    }

    @Override
    public IRoom getRoom() {
        return this.room;
    }

    @Override
    public void init(final IRoom room) {
        this.injector.injectMembers(this.executionPipeline);
    }

    @Override
    public void onRoomLoaded() {
        for (final var floorItem : this.getRoom().getObjectManager().getAllFloorItems()) {
            if (!(floorItem instanceof WiredItem wiredItem)) continue;

            this.registerWired(wiredItem);
        }
        ;
    }

    @Override
    public void destroy() {

    }
    @Inject
    IRoomItemsRepository itemsRepository;

    @Override
    public void update() {
        final var updated = new AtomicInteger(0);
        this.itemsRepository.updateBatch(UPDATE_WIRED_SQL, statement -> {
            for (final var wiredItem : this.wireds.values()) {
                if (!wiredItem.isNeedSaveSettings()) continue;

                statement.setString(1, wiredItem.getSettings().toJson());
                statement.setInt(2, wiredItem.getId());
                statement.addBatch();
                wiredItem.setNeedSaveSettings(false);
                updated.incrementAndGet();
            }
        }, result -> {
        });

        LOGGER.info("total of {} wireds updated in room {}", updated.get(), this.getRoom().getData().getId());
    }

    public void registerWired(WiredItem wiredItem) {
        this.injector.injectMembers(wiredItem);
        this.getRoom().getEventHandler().subscribe(wiredItem);
        synchronized (this.wireds) {
            this.wireds.put(wiredItem.getId(), wiredItem);
            switch (wiredItem) {
                case WiredAddon addon -> this.addons.put(addon.getId(), addon);
                case WiredTrigger trigger -> this.triggers.put(trigger.getId(), trigger);
                case WiredEffect effect -> this.effects.put(effect.getId(), effect);
                case WiredCondition condition -> this.conditions.put(condition.getId(), condition);
                case WiredSelector selector -> this.selectors.put(selector.getId(), selector);
                default -> throw new IllegalStateException("Invalid wired type");
            }
        }
    }

    public Map<Integer, WiredItem> getWireds() {
        return this.wireds;
    }

    public Map<Integer, WiredTrigger> getTriggers() {
        return this.triggers;
    }

    public Map<Integer, WiredTrigger> getTriggersForEvent(Event event) {
        return this.triggers;
    }

    public Map<Integer, WiredCondition> getConditions() {
        return this.conditions;
    }

    public Map<Integer, WiredEffect> getEffects() {
        return this.effects;
    }

    public Map<Integer, WiredAddon> getAddons() {
        return this.addons;
    }

    public Map<Integer, WiredSelector> getSelectors() {
        return this.selectors;
    }

    public WiredExecutionPipeline getExecutionPipeline() {
        return this.executionPipeline;
    }

    public List<WiredTrigger> getTriggersAt(Position position) {
        return this.getTriggers().values().stream().filter(trigger -> trigger.getPosition().equals(position)).toList();
    }

    public List<WiredCondition> getConditionsAt(Position position) {
        return this.getConditions().values().stream().filter(condition -> condition.getPosition().equals(position)).toList();
    }

    public List<WiredEffect> getEffectsAt(Position position) {
        return this.getEffects().values().stream().filter(effect -> effect.getPosition().equals(position)).toList();
    }

    public List<WiredAddon> getAddonsAt(Position position) {
        return this.getAddons().values().stream().filter(addon -> addon.getPosition().equals(position)).toList();
    }

    public List<WiredSelector> getSelectorsAt(Position position) {
        return this.getSelectors().values().stream().filter(selector -> selector.getPosition().equals(position)).toList();
    }
}
