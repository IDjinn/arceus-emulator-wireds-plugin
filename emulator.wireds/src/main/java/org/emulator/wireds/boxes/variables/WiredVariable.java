package org.emulator.wireds.boxes.variables;

import habbo.variables.IVariable;
import habbo.variables.Variable;
import networking.packets.IIncomingPacket;
import networking.packets.OutgoingPacket;
import org.emulator.wireds.boxes.util.WiredVariableType;
import org.jetbrains.annotations.Nullable;

public class WiredVariable<T> extends Variable<T> implements IVariable<T> {
    private WiredVariableType contextType;
    private @Nullable String alias;
    private @Nullable String defaultValue;

    public WiredVariable(final String key, final T value, final String alias, final String defaultValue,
                         final WiredVariableType wiredVariableType) {
        super(key, value);
        this.contextType = wiredVariableType;
        this.alias = alias;
        this.defaultValue = defaultValue;
    }

    public WiredVariable(final WiredVariableType wiredVariableType, final String key,
                         final T value) {
        this(key, value, "", "", wiredVariableType);
    }

    public static WiredVariable fromPacket(final IIncomingPacket packet) {
        final String key = packet.readString();
        final String value = packet.readString();
        final WiredVariableType contextType = WiredVariableType.fromLabel(packet.readString());
        final String alias = packet.readString();
        final String defaultValue = packet.readString();
        return new WiredVariable(key, value, defaultValue, alias, contextType);
    }

    @Override
    public void serialize(final OutgoingPacket packet) {
        super.serialize(packet);
        packet.appendString(this.getContextType().getType());
        packet.appendString("");
        packet.appendString(this.getAlias() != null ? this.getAlias() : "");
        packet.appendString(this.getDefaultValue() != null ? this.getDefaultValue() : "");
    }

    public WiredVariableType getContextType() {
        return this.contextType;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }
}