package org.emulator.wireds.boxes;

import habbo.furniture.extra.data.MapExtraData;
import org.emulator.wireds.boxes.util.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.WiredItemSourceType;
import org.emulator.wireds.boxes.util.WiredSelectionType;
import org.emulator.wireds.boxes.util.WiredVariableContextType;
import org.emulator.wireds.boxes.variables.WiredVariable;

import java.util.*;


@SuppressWarnings("PublicMethodNotExposedInInterface")
public class WiredItemSettings {
    private final Set<Integer> selectedItems;
    public MapExtraData wiredData;
    private EnumSet<WiredSelectionType> selectionTypes;

    private WiredItemSourceType wiredItemSourceType;
    private WiredEntitySourceType wiredEntitySourceType;
    private WiredVariableContextType variableContextType;
    private final Map<String, WiredVariable> inputContextVariables;
    private final Map<String, WiredVariable> outputContextVariables;

    public WiredItemSettings() {
        this.wiredData = new MapExtraData();
        this.selectedItems = new HashSet<>();
        this.selectionTypes = EnumSet.of(WiredSelectionType.None);

        this.wiredItemSourceType = WiredItemSourceType.Selected;
        this.wiredEntitySourceType = WiredEntitySourceType.Trigger;
        this.variableContextType = WiredVariableContextType.Stack;
        this.inputContextVariables = new HashMap<>();
        this.outputContextVariables = new HashMap<>();
    }

    public EnumSet<WiredSelectionType> getSelectionTypes() {
        return this.selectionTypes;
    }

    public void setSelectionTypes(final EnumSet<WiredSelectionType> selectionTypes) {
        this.selectionTypes = selectionTypes;
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

    public WiredVariableContextType getWiredVariableContextType() {
        return this.variableContextType;
    }

    public MapExtraData getWiredData() {
        return this.wiredData;
    }

    public void setWiredData(final MapExtraData wiredData) {
        this.wiredData = wiredData;
    }

    public Map<String, WiredVariable> getInputContextVariables() {
        return inputContextVariables;
    }

    public Map<String, WiredVariable> getOutputContextVariables() {
        return outputContextVariables;
    }
}