package org.emulator.wireds.messages.outgoing;

import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.WiredItem;
import org.emulator.wireds.boxes.util.selection.WiredSelectionType;

public final class WiredItemWriter {
    public static void serialize(final OutgoingPacket packet, final WiredItem wiredItem) {
        packet.appendInt(wiredItem.getId());
        packet.appendInt(wiredItem.getVirtualId());
        packet.appendInt(wiredItem.getInterface().getCode());
        packet.appendInt(wiredItem.getFurniture().getSpriteId());

        // INPUT CONFIGURATIONS
        {
            packet.appendInt(wiredItem.getWiredItemSourceType().ordinal());
            packet.appendInt(wiredItem.getWiredEntitiesSourceType().ordinal());
            packet.appendInt(wiredItem.getWiredVariableContextType().ordinal());
            packet.appendInt(WiredSelectionType.pack(wiredItem.getSelectionType()));
        }

        // VARIABLES
        {
            wiredItem.serializeCommonVariables(packet);
            packet.appendInt(wiredItem.getInputVariablesManager().getVariables().size());
            for (final var variable : wiredItem.getInputVariablesManager().getVariables().values()) {
                variable.serialize(packet);
            }

            packet.appendInt(wiredItem.getOutputVariablesManager().getVariables().size());
            for (final var variable : wiredItem.getOutputVariablesManager().getVariables().values()) {
                variable.serialize(packet);
            }
        }

        wiredItem.getWiredData().serialize(packet);

        packet.appendInt(wiredItem.getMaxSelectionCount());
        // SELECTED ITEMS
        {
            packet.appendInt(wiredItem.getSelectedItems().size());
            for (var selectedItem : wiredItem.getSelectedItems().keySet()) {
                packet.appendInt(selectedItem);
            }
        }

        wiredItem.serializeWiredExtraStuff(packet);
    }
}
