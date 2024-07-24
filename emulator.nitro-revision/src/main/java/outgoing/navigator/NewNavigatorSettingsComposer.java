package outgoing.navigator;

import habbo.habbos.data.navigator.IHabboNavigatorWindowSettings;
import networking.packets.OutgoingPacket;


public class NewNavigatorSettingsComposer extends OutgoingPacket<U> {
    public NewNavigatorSettingsComposer(final IHabboNavigatorWindowSettings settings) {
        super(OutgoingHeaders.NewNavigatorSettingsComposer);

        this.appendInt(settings.getWindowX());
        this.appendInt(settings.getWindowY());
        this.appendInt(settings.getWindowWidth());
        this.appendInt(settings.getWindowHeight());
        this.appendBoolean(settings.isLeftPanelCollapsed());
        this.appendInt(settings.getResultsMode());
    }
}
