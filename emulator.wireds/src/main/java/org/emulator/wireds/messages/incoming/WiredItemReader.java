package org.emulator.wireds.messages.incoming;

import networking.packets.IIncomingPacket;
import org.emulator.wireds.boxes.WiredItemSettings;
import org.emulator.wireds.boxes.util.selection.WiredEntitySourceType;
import org.emulator.wireds.boxes.util.selection.WiredItemSourceType;
import org.emulator.wireds.boxes.util.selection.WiredVariableType;
import org.emulator.wireds.boxes.variables.WiredVariable;

import java.util.HashMap;
import java.util.Map;

public final class WiredItemReader {
    public static WiredItemSettings readSettings(
            final WiredItemSettings settings,
            final IIncomingPacket packet
    ) {
        settings.getWiredData().getValues().clear();
        final int dataCount = packet.readInt();
        for (int i = 0; i < dataCount; i++) {
            settings.getWiredData().getValues().putIfAbsent(packet.readString(), packet.readString());
        }

        settings.getSelectedItems().clear();
        final int selectedObjectCount = packet.readInt();
        for (int i = 0; i < selectedObjectCount; i++) {
            settings.getSelectedItems().add(packet.readInt());
        }

        {
            settings.getInputContextVariables().clear();
            settings.getInputContextVariables().putAll(readWiredVariables(packet));

            settings.getOutputContextVariables().clear();
            settings.getOutputContextVariables().putAll(readWiredVariables(packet));
        }

        final WiredItemSourceType itemSourceType = WiredItemSourceType.fromType(packet.readInt());
        final WiredEntitySourceType entitySourceType = WiredEntitySourceType.fromType(packet.readInt());
        final WiredVariableType variableContextType = WiredVariableType.fromLabel(packet.readString());

        settings.setWiredItemSourceType(itemSourceType);
        settings.setWiredEntitiesSourceType(entitySourceType);
        return settings;
    }

    private static Map<String, WiredVariable<?>> readWiredVariables(final IIncomingPacket packet) {
        final int variablesCount = packet.readInt();
        final Map<String, WiredVariable<?>> variables = new HashMap<>(variablesCount);
        for (int i = 0; i < variablesCount; i++) {
            final var variable = WiredVariable.fromPacket(packet);
            variables.put(variable.getKey(), variable);
        }
        return variables;
    }
}
