package org.emulator.wireds.boxes;

import habbo.furniture.IFurniture;
import habbo.furniture.extra.data.MapExtraData;
import habbo.rooms.IRoom;
import habbo.rooms.components.objects.items.IRoomItem;
import habbo.rooms.components.objects.items.IRoomItemData;
import habbo.rooms.components.objects.items.floor.AdvancedFloorItem;
import networking.packets.IOutgoingPacket;
import org.emulator.wireds.boxes.util.codes.WiredInterfaceCode;
import org.emulator.wireds.boxes.util.selection.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.selection.WiredItemSourceType;
import org.emulator.wireds.boxes.util.selection.WiredSelectionType;
import org.emulator.wireds.boxes.util.selection.WiredVariableType;
import org.emulator.wireds.boxes.variables.WiredVariable;
import org.emulator.wireds.boxes.variables.WiredVariableManager;
import org.emulator.wireds.component.WiredExecutionPipeline;
import org.emulator.wireds.component.WiredManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

@SuppressWarnings("PublicMethodNotExposedInInterface")
public abstract class WiredItem extends AdvancedFloorItem {
    private static final Map<String, Function<WiredItem, String>> COMMON_WIRED_VARIABLES = new HashMap<>() {
        {
            this.put("this.position.x", wiredItem -> String.valueOf(wiredItem.getPosition().getX()));
            this.put("this.position.y", wiredItem -> String.valueOf(wiredItem.getPosition().getY()));
            this.put("this.position.z", wiredItem -> String.valueOf(wiredItem.getPosition().getZ()));
        }
    };


    private WiredItemSettings settings;
    private final Map<Integer, IRoomItem> selectedItems;
    
    
    protected boolean isFlashing;
    protected boolean needSaveSettings;
    protected final WiredExecutionPipeline executionPipeline;

    private final WiredVariableManager inputVariablesManager;
    private final WiredVariableManager outputVariablesManager;

    public WiredItem(final IRoomItemData itemData, final IRoom room, final IFurniture furniture) {
        super(itemData, room, furniture);
        this.settings = WiredItemSettings.fromJson(itemData.getWiredData());
        this.selectedItems = new HashMap<>();
        this.executionPipeline = Objects.requireNonNull(room.getCustomComponent(WiredManager.class)).getExecutionPipeline();
        this.inputVariablesManager = new WiredVariableManager(this, this.settings.getInputContextVariables());
        this.outputVariablesManager = new WiredVariableManager(this, this.settings.getOutputContextVariables());
    }

    @Override
    public void onRoomLoaded() {
        final var iterator = this.settings.getSelectedItems().iterator();
        while (iterator.hasNext()) {
            final var selectedItemId = iterator.next();
            final var item = this.getRoom().getObjectManager().getItem(selectedItemId);
            if (item == null)
                iterator.remove();
            else
                this.selectItem(item);
        }
    }

    public Map<Integer, IRoomItem> getSelectedItems() {
        return this.selectedItems;
    }

    public void selectItem(@NotNull IRoomItem roomItem) {
        this.settings.getSelectedItems().add(roomItem.getId());
        this.selectedItems.put(roomItem.getId(), roomItem);
    }

    public void deselectItem(@NotNull IRoomItem roomItem) {
        this.settings.getSelectedItems().remove(roomItem.getId());
        this.selectedItems.remove(roomItem.getId());
    }

    public EnumSet<WiredSelectionType> getSelectionType() {
        return this.settings.getSelectionTypes();
    }

    public void setSelectionTypes(final EnumSet<WiredSelectionType> selectionTypes) {
        this.settings.setSelectionTypes(selectionTypes);
    }
    
    public WiredItemSettings getSettings() {
        return this.settings;
    }
    
    public WiredItemSourceType getWiredItemSourceType() {
        return this.settings.getWiredItemSourceType();
    }
    
    public void setWiredItemSourceType(final WiredItemSourceType wiredItemSourceType) {
        this.settings.setWiredItemSourceType(wiredItemSourceType);
    }


    public WiredEntitySourceType getWiredEntitiesSourceType() {
        return this.settings.getWiredEntitiesSourceType();
    }

    public void setWiredEntitiesSourceType(final WiredEntitySourceType entitySourceType) {
        this.settings.setWiredEntitiesSourceType(entitySourceType);
    }
    
    public boolean isFlashing() {
        return this.isFlashing;
    }
    
    public void setFlashing(final boolean flashing) {
        this.isFlashing = flashing;
    }
    
    public WiredExecutionPipeline getExecutionPipeline() {
        return this.executionPipeline;
    }

    public abstract WiredInterfaceCode getInterface();

    public int getMaxSelectionCount() {
        return 0;
    }

    public WiredVariableType getWiredVariableContextType() {
        return this.settings.getWiredVariableContextType();
    }

    public void serializeCommonVariables(final IOutgoingPacket packet) {
        packet.appendInt(COMMON_WIRED_VARIABLES.size());
        for (final Map.Entry<String, Function<WiredItem, String>> entry : COMMON_WIRED_VARIABLES.entrySet()) {
            final var variable = new WiredVariable(entry.getKey(), entry.getValue().apply(this), "", "",
                    WiredVariableType.Stack);
            variable.serialize(packet);
        }
    }


    public List<WiredVariable<?>> getCommonVariables() {
        final var variables = new ArrayList<WiredVariable<?>>();
        for (final Map.Entry<String, Function<WiredItem, String>> entry : COMMON_WIRED_VARIABLES.entrySet()) {
            final var variable = new WiredVariable(entry.getKey(), entry.getValue().apply(this), "", "",
                    WiredVariableType.Stack);
            variables.add(variable);
        }
        return variables;
    }

    public void serializeWiredExtraStuff(final IOutgoingPacket packet) {

    }

    public MapExtraData getWiredData() {
        return this.settings.wiredData;
    }

    public void setSettings(final WiredItemSettings settings) {
        this.settings = settings;
        this.onRoomLoaded();
        this.onWiredSettingsChanged();
    }

    public void onWiredSettingsChanged() {
    }

    public boolean isNeedSaveSettings() {
        return this.needSaveSettings;
    }

    public void setNeedSaveSettings(final boolean needSaveSettings) {
        this.needSaveSettings = needSaveSettings;
    }

    public WiredVariableManager getInputVariablesManager() {
        return this.inputVariablesManager;
    }

    public WiredVariableManager getOutputVariablesManager() {
        return this.outputVariablesManager;
    }
}
