package org.emulator.wireds.boxes.variables;

import habbo.variables.IVariableMessageFactory;
import habbo.variables.VariableMessageFactory;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.util.selection.WiredVariableType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class WiredVariableManager {
    private final VariableMessageFactory messageFormatter;
    private final Map<String, WiredVariable<?>> variables;
    private final WiredItem wiredItem;
    private boolean needUpdate;

    public WiredVariableManager(final WiredItem wiredItem, final Map<String, WiredVariable<?>> variables) {
        this.wiredItem = wiredItem;
        this.variables = variables;
        this.messageFormatter = new VariableMessageFactory();
    }


    public Map<String, WiredVariable<?>> getVariables() {
        return this.variables;
    }

    public <T> WiredVariable<T> getOrCreate(final WiredVariable<T> variable) {
        if (this.variables.containsKey(variable.getKey()))
            return (WiredVariable<T>) this.variables.get(variable.getKey());

        this.set(variable);
        return variable;
    }

    public <T> WiredVariable<T> setOrCreate(final String key, final T value) {
        if (this.variables.containsKey(key)) {
            final var foundVariable = (WiredVariable<T>) this.variables.get(key);
            final var oldValue = foundVariable.getValue();
            foundVariable.setValue(value);
            this.setNeedUpdate(!Objects.equals(oldValue, value));
            return foundVariable;
        }
        return this.set(new WiredVariable<>(
                key,
                value,
                "",
                "",
                WiredVariableType.Box
        ));
    }

    public <T> WiredVariable<T> setOrCreate(final WiredVariable<T> variable) {
        if (this.variables.containsKey(variable.getKey())) {
            final var foundVariable = (WiredVariable<T>) this.variables.get(variable.getKey());
            final var oldValue = foundVariable.getValue();
            foundVariable.setValue(variable.getValue());
            this.setNeedUpdate(!Objects.equals(oldValue, variable.getValue()));
            return foundVariable;
        }
        return this.set(variable);
    }

    public <T> WiredVariable<T> get(@NotNull final String key) {
        return (WiredVariable<T>) this.variables.get(key);
    }

    public <T> WiredVariable<T> get(final @NotNull String key, final Class<T> valueType) {
        return (WiredVariable<T>) this.variables.get(key);
    }

    public <T> WiredVariable<T> set(final WiredVariable<T> variable) {
        this.variables.put(variable.getKey(), variable);
        this.setNeedUpdate(true);
        return variable;
    }

    public boolean isNeedUpdate() {
        return this.needUpdate;
    }

    public void setNeedUpdate(final boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public void deleteVariable(@NotNull final String key) {
        this.variables.remove(key);
        this.setNeedUpdate(true);
    }

    public void tick() {
        for (final var variable : this.getVariables().values()) {
            if (variable.expiresAt().isEmpty()) {
            }
//   TODO          if (variable.expiresAt().get().isBefore(Instant.now())) {
//                this.removeVariable(variable.getKey());
//            }
        }
    }

    public IVariableMessageFactory getFormatter() {
        return this.messageFormatter;
    }
}
