package outgoing.rooms.entities.variables;

import habbo.variables.IVariable;
import networking.packets.OutgoingPacket;

import java.util.Map;

public class EntityVariablesComposer extends OutgoingPacket<U> {
    public EntityVariablesComposer(Map<String, IVariable<?>> variables) {
        super(OutgoingHeaders.EntityVariablesComposer);

        final var visible = variables.values().stream().filter(IVariable::isVisible).toList();
        this.appendInt(visible.size());
        for (final var variable : visible) {
            variable.serialize(this);
        }
    }
}
