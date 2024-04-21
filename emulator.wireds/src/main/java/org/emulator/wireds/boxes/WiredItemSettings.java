package org.emulator.wireds.boxes;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@SuppressWarnings("PublicMethodNotExposedInInterface")
public class WiredItemSettings {
    private final Map<Integer, Integer> parameters;
    private final Set<Integer> selectedItems;
    private @Nullable String data;
    private WiredSelectionType selectionType;

    private WiredItemSourceType wiredItemSourceType;
    private WiredEntitySourceType wiredEntitySourceType;

    public WiredItemSettings() {
        this.parameters = new HashMap<>();
        this.selectedItems = new HashSet<>();
        this.selectionType = WiredSelectionType.None;

        this.wiredItemSourceType = WiredItemSourceType.Selected;
        this.wiredEntitySourceType = WiredEntitySourceType.Trigger;
    }

    public Map<Integer, Integer> getParameters() {
        return this.parameters;
    }

    public @Nullable String getData() {
        return this.data;
    }

    public void setData(final @Nullable String data) {
        this.data = data;
    }

    public WiredSelectionType getSelectionType() {
        return this.selectionType;
    }

    public void setSelectionType(final WiredSelectionType selectionType) {
        this.selectionType = selectionType;
    }

    public Set<Integer> getSelectedItems() {
        return this.selectedItems;
    }

    public WiredItemSourceType getWiredItemSourceType() {
        return this.wiredItemSourceType;
    }
    public void setWiredItemSourceType(final WiredItemSourceType wiredItemSourceType) {
        this.wiredItemSourceType = wiredItemSourceType;
    }
    public WiredEntitySourceType getWiredEntitiesSourceType() {
        return this.wiredEntitySourceType;
    }
    public void setWiredEntitiesSourceType(final WiredEntitySourceType wiredEntitySourceType) {
        this.wiredEntitySourceType = wiredEntitySourceType;
    }
}