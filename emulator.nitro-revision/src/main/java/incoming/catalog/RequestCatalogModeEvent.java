package incoming.catalog;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import habbo.catalog.ICatalogManager;
import networking.client.IClient;
import networking.packets.IIncomingPacket;
import networking.packets.IncomingEvent;
import networking.packets.OutgoingPacket;
import packets.incoming.IncomingHeaders;
import packets.outgoing.catalog.CatalogIndexComposer;

@Singleton
public class RequestCatalogModeEvent extends IncomingEvent {
    private final int RootPageId = -1;

    @Inject
    private ICatalogManager catalogManager;

    @Override
    public int getHeaderId() {
        return IncomingHeaders.RequestCatalogModeEvent;
    }

    @Override
    public void parse(IIncomingPacket packet, IClient client) {
        client.sendMessage(new OutgoingPacket<U>(3828).appendInt(0));
        client.sendMessage(new CatalogIndexComposer(false, "normal",
                this.catalogManager.getCatalogPagesForHabbo(this.RootPageId, client.getHabbo())));
    }
}
