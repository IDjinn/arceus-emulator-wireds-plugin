package incoming.catalog;

import com.google.inject.Inject;
import habbo.catalog.ICatalogManager;
import networking.client.IClient;
import networking.packets.IIncomingPacket;
import networking.packets.IncomingEvent;
import packets.incoming.IncomingHeaders;
import packets.outgoing.catalog.CatalogPageComposer;

public class RequestCatalogPage extends IncomingEvent {
    @Inject
    ICatalogManager catalogManager;

    @Override
    public int getHeaderId() {
        return IncomingHeaders.RequestCatalogPageEvent;
    }

    @Override
    public void parse(IIncomingPacket packet, IClient client) {
        var pageId = packet.readInt();
        var offerId = packet.readInt();
        var mode = packet.readString();

        var page = this.catalogManager.getCatalogPageForHabbo(pageId, client.getHabbo());
        if (page == null)
            return;

        client.sendMessage(new CatalogPageComposer(page, client.getHabbo(), offerId, mode));
    }
}
