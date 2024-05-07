package org.emulator.wireds.messages.incoming;

import habbo.furniture.extra.data.MapExtraData;
import networking.client.IClient;
import networking.packets.IIncomingPacket;
import org.emulator.wireds.boxes.WiredItemSettings;
import org.emulator.wireds.boxes.util.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.WiredItemSourceType;
import org.emulator.wireds.boxes.util.WiredVariableContextType;
import org.emulator.wireds.boxes.variables.WiredVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WiredItemReader {
    public static WiredItemSettings readSettings(final IClient client, final IIncomingPacket packet) {
        final var settings = new WiredItemSettings();
        final int dataCount = packet.readInt();
        final MapExtraData wiredData = new MapExtraData();
        for (int i = 0; i < dataCount; i++) {
            wiredData.getValues().putIfAbsent(packet.readString(), packet.readString());
        }

        settings.setWiredData(wiredData);
        final int selectedObjectCount = packet.readInt();
        final List<Integer> selectedObjects = new ArrayList<>();
        for (int i = 0; i < selectedObjectCount; i++) {
            selectedObjects.add(packet.readInt());
        }

        settings.getSelectedItems().addAll(selectedObjects);
        final int outputVariablesCount = packet.readInt();
        final Map<String, WiredVariable> variables = new HashMap<>(outputVariablesCount);
        for (int i = 0; i < outputVariablesCount; i++) {
            final var variable = WiredVariable.fromPacket(packet);
            variables.put(variable.getKey(), variable);
        }

        settings.getOutputContextVariables().putAll(variables);
        final WiredItemSourceType itemSourceType = WiredItemSourceType.fromType(packet.readInt());
        final WiredEntitySourceType entitySourceType = WiredEntitySourceType.fromType(packet.readInt());
        final WiredVariableContextType variableContextType = WiredVariableContextType.fromLabel(packet.readString());

        settings.setWiredItemSourceType(itemSourceType);
        settings.setWiredEntitiesSourceType(entitySourceType);
        return settings;
    }
}
