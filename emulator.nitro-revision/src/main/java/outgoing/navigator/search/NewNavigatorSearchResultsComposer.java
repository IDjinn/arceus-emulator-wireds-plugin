package outgoing.navigator.search;

import habbo.navigator.data.INavigatorResultCategory;
import networking.packets.OutgoingPacket;

import java.util.List;

public class NewNavigatorSearchResultsComposer extends OutgoingPacket<U> {

    public NewNavigatorSearchResultsComposer(String code, String query, List<INavigatorResultCategory> categories) {
        super(OutgoingHeaders.NewNavigatorSearchResultsComposer);

        this.appendString(code);
        this.appendString(query);

        this.appendInt(categories.size());

        for (final INavigatorResultCategory category : categories) {
            category.write(this);
        }
    }
}
