package club.maxstats.weaveyoutube;

import club.maxstats.weave.loader.api.ModInitializer;
import club.maxstats.weave.loader.api.command.CommandBus;
import club.maxstats.weave.loader.api.event.EventBus;
import club.maxstats.weaveyoutube.command.ToggleESPCommand;
import club.maxstats.weaveyoutube.listener.RenderListener;

public class Main implements ModInitializer {
    @Override
    public void init() {
        System.out.println("Initializing Weave YouTube Mod!");

        RenderListener renderListener = new RenderListener();
        EventBus.subscribe(renderListener);
        CommandBus.register(new ToggleESPCommand(renderListener));
    }
}