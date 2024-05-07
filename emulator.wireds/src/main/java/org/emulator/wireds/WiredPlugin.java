package org.emulator.wireds;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import core.plugins.IPlugin;
import habbo.rooms.components.objects.items.IRoomItemFactory;
import networking.packets.IPacketManager;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.emulator.wireds.boxes.effects.WiredEffectMessage;
import org.emulator.wireds.boxes.selectors.WiredSelectorFilterFurniture;
import org.emulator.wireds.boxes.triggers.WiredTriggerEntitySayKeyword;
import org.emulator.wireds.messages.incoming.WiredEffectSaveEvent;
import org.emulator.wireds.messages.incoming.WiredTriggerSaveEvent;

@Singleton
public class WiredPlugin extends AbstractModule implements IPlugin {
    public static void main(String[] args) throws Exception {
    }
    
    private static final String author = "IDjinn";
    private static final ComparableVersion version = new ComparableVersion("1.0.0");
    private static final String description = "All habbo original wireds and some customs";
    private static final String name = "wireds-plugin";
    @Inject
    private Injector injector;

    @Inject
    private IRoomItemFactory roomItemFactory;

    @Inject
    private IPacketManager packetManager;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ComparableVersion getVersion() {
        return version;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void init() {
        this.packetManager.registerIncoming(new WiredTriggerSaveEvent());
        this.packetManager.registerIncoming(new WiredEffectSaveEvent());
        
        this.roomItemFactory.registerInteraction(
                WiredTriggerEntitySayKeyword.InteractionName,
                WiredTriggerEntitySayKeyword.class
        );
        this.roomItemFactory.registerInteraction(
                WiredSelectorFilterFurniture.InteractionName,
                WiredSelectorFilterFurniture.class
        );
        this.roomItemFactory.registerInteraction(
                WiredEffectMessage.InteractionName,
                WiredEffectMessage.class
        );
    }

    
    @Override
    public void destroy() {

    }

    @Override
    public Injector getInjector() {
        return this.injector;
    }

    @Override
    protected void configure() {
        this.bind(RoomInjector.class).asEagerSingleton();
    }
}
