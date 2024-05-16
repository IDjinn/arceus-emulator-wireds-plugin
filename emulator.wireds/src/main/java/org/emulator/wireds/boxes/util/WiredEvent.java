package org.emulator.wireds.boxes.util;

import com.google.inject.Inject;
import core.events.Event;
import core.pipeline.PipelineEvent;
import habbo.rooms.components.objects.items.IRoomItem;
import habbo.rooms.entities.IRoomEntity;
import habbo.variables.IVariable;
import habbo.variables.IVariableMessageFactory;
import org.emulator.wireds.boxes.base.WiredEffect;
import org.emulator.wireds.boxes.base.WiredSelector;
import org.emulator.wireds.boxes.base.WiredTrigger;
import org.emulator.wireds.boxes.base.condition.WiredCondition;
import org.emulator.wireds.boxes.util.selection.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.selection.WiredItemSourceType;
import org.emulator.wireds.boxes.util.selection.WiredVariableType;
import utils.pathfinder.Position;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class WiredEvent extends PipelineEvent {
    @Inject
    private IVariableMessageFactory variableMessageFactory;

    private final Event triggerEvent;
    private final List<WiredTrigger> triggers;
    private final List<WiredSelector> selectors;
    private final List<WiredCondition> conditions;
    private final List<WiredEffect> effects;
    private final int hash;
    private final Position triggerPosition;

    private final Map<WiredItemSourceType, List<IRoomItem>> items;
    private final Map<WiredEntitySourceType, List<IRoomEntity>> entities;
    private final Map<WiredVariableType, List<IVariable<?>>> variables;

    public WiredEvent(Event triggerEvent, Position triggerPosition, int hash) {
        this.triggerEvent = triggerEvent;
        this.triggerPosition = triggerPosition;
        this.hash = hash;
        this.triggers = new LinkedList<>();
        this.selectors = new LinkedList<>();
        this.conditions = new LinkedList<>();
        this.effects = new LinkedList<>();
        this.items = new HashMap<>();
        this.entities = new HashMap<>();
        this.variables = new HashMap<>();

        for (final var sourceType : WiredItemSourceType.values()) {
            this.items.put(sourceType, new LinkedList<>());
        }

        for (final var sourceType : WiredEntitySourceType.values()) {
            this.entities.put(sourceType, new LinkedList<>());
        }

        for (final var sourceType : WiredVariableType.values()) {
            this.variables.put(sourceType, new LinkedList<>());
        }
    }

    public Event getTriggerEvent() {
        return this.triggerEvent;
    }

    public List<WiredTrigger> getTriggers() {
        return this.triggers;
    }

    public List<WiredSelector> getSelectors() {
        return this.selectors;
    }

    public List<WiredCondition> getConditions() {
        return this.conditions;
    }

    public List<WiredEffect> getEffects() {
        return this.effects;
    }

    public WiredEvent addTrigger(WiredTrigger trigger) {
        this.triggers.add(trigger);
        return this;
    }

    public WiredEvent addSelector(WiredSelector selector) {
        this.selectors.add(selector);
        return this;
    }

    public WiredEvent addCondition(WiredCondition condition) {
        this.conditions.add(condition);
        return this;
    }

    public WiredEvent addEffect(WiredEffect effect) {
        this.effects.add(effect);
        return this;
    }

    public Position getTriggerPosition() {
        return this.triggerPosition;
    }

    public WiredEvent addEntity(WiredEntitySourceType sourceType, IRoomEntity entity) {
        this.entities.get(sourceType).add(entity);
        return this;
    }

    public WiredEvent removeEntity(WiredEntitySourceType sourceType, IRoomEntity entity) {
        this.entities.get(sourceType).remove(entity);
        return this;
    }

    public WiredEvent addEntities(WiredEntitySourceType sourceType, List<? extends IRoomEntity> entity) {
        this.entities.get(sourceType).addAll(entity);
        return this;
    }

    public WiredEvent addItem(WiredItemSourceType sourceType, IRoomItem item) {
        this.items.get(sourceType).add(item);
        return this;
    }

    public WiredEvent removeItem(WiredItemSourceType sourceType, IRoomItem item) {
        this.items.get(sourceType).remove(item);
        return this;
    }

    public WiredEvent addItems(WiredItemSourceType sourceType, List<? extends IRoomItem> item) {
        this.items.get(sourceType).addAll(item);
        return this;
    }

    public WiredEvent addVariable(WiredVariableType sourceType, IVariable variable) {
        this.variables.get(sourceType).add(variable);
        return this;
    }

    public WiredEvent removeVariable(WiredVariableType sourceType, IVariable variable) {
        this.variables.get(sourceType).remove(variable);
        return this;
    }

    public WiredEvent addVariables(WiredVariableType sourceType, List<IVariable<?>> variable) {
        this.variables.get(sourceType).addAll(variable);
        return this;
    }

    public Map<WiredItemSourceType, List<IRoomItem>> getItems() {
        return this.items;
    }

    public Map<WiredEntitySourceType, List<IRoomEntity>> getEntities() {
        return this.entities;
    }

    public List<IRoomEntity> getEntities(WiredEntitySourceType entitySourceType) {
        return this.entities.get(entitySourceType);
    }

    public Map<WiredVariableType, List<IVariable<?>>> getVariables() {
        return this.variables;
    }


    @Override
    public int hashCode() {
        return this.hash;
    }

    public void addEffects(final List<WiredEffect> effects) {
        this.effects.addAll(effects);
    }

    public String handleVariables(final String template) {
        return this.variableMessageFactory.format(template, this.variables.get(WiredVariableType.Stack));
    }
}
