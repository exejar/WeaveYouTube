package club.maxstats.weaveyoutube;

import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.weavemc.loader.api.event.EventBus;
import club.maxstats.weaveyoutube.command.ToggleESPCommand;
import club.maxstats.weaveyoutube.listener.RenderListener;

public class Main implements ModInitializer {
    @Override
    public void preInit() {
        System.out.println("Initializing Weave YouTube Mod!");

        RenderListener renderListener = new RenderListener();
        EventBus.subscribe(renderListener);
        CommandBus.register(new ToggleESPCommand(renderListener));
    }
}