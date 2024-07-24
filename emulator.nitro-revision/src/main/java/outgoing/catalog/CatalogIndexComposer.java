package outgoing.catalog;

import habbo.catalog.pages.ICatalogPage;
import networking.packets.OutgoingPacket;

import java.util.List;

public class CatalogIndexComposer extends OutgoingPacket<U> {
    public CatalogIndexComposer(final boolean showId, final String mode, final List<? extends ICatalogPage> pages) {
        super(OutgoingHeaders.CatalogPagesListComposer);
        this.appendBoolean(true, "isVisible");
        this.appendInt(0, "icon");
        this.appendInt(-1, "page_id");
        this.appendString("root", "caption");
        this.appendString("", "localization");
        this.appendInt(0, "offers");

        this.appendInt(pages.size());
        for (var page : pages) {
            this.serializePage(page, showId);
        }

        this.appendBoolean(false);
        this.appendString(mode);
    }

    private void serializePage(final ICatalogPage catalogPage, final boolean showId) {
        this.appendBoolean(catalogPage.isVisible());
        this.appendInt(catalogPage.getIcon());
        this.appendInt(catalogPage.isEnabled() ? catalogPage.getId() : -1);
        this.appendString(catalogPage.getCaption());
        this.appendString(showId ? STR."\{catalogPage.getCaption()} (\{catalogPage.getId()})" : catalogPage.getCaption());
        this.appendInt(0, "offer-ids size");

        this.appendInt(catalogPage.getChildren().size());
        for (var page : catalogPage.getChildren()) {
            this.serializePage(page, showId);
        }
    }
}
