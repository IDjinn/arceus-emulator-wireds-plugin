package org.emulator.wireds.boxes.variables;

import habbo.variables.IVariable;
import habbo.variables.Variable;
import networking.packets.IIncomingPacket;
import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.util.WiredVariableContextType;
import org.jetbrains.annotations.Nullable;

public class WiredVariable extends Variable implements IVariable {
    private WiredVariableContextType contextType;
    private @Nullable String alias;
    private @Nullable String defaultValue;

    public WiredVariable(final String key, final WiredVariableContextType wiredVariableContextType) {
        super(key);
        this.contextType = wiredVariableContextType;
    }

    public WiredVariable(final WiredVariableContextType wiredVariableContextType, final String key, final String value) {
        super(key, value);
        this.contextType = wiredVariableContextType;
    }

    public static WiredVariable fromPacket(final IIncomingPacket packet) {
        final String key = packet.readString();
        final WiredVariableContextType contextType = WiredVariableContextType.fromLabel(packet.readString());
        final String alias = packet.readString();
        final String defaultValue = packet.readString();
        return new WiredVariable(contextType, key, defaultValue);
    }

    @Override
    public void serialize(final OutgoingPacket packet) {
        super.serialize(packet);
        packet.appendString(this.getContextType().getType());
        packet.appendString(this.getContextType().getKey() != null ? this.getContextType().getKey() : "");
        packet.appendString(this.getAlias() != null ? this.getAlias() : "");
        packet.appendString(this.getDefaultValue() != null ? this.getDefaultValue() : "");
    }

    public WiredVariableContextType getContextType() {
        return contextType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }
}