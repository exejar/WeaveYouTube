package club.maxstats.weaveyoutube.command;

import club.maxstats.weave.loader.api.command.Command;
import club.maxstats.weaveyoutube.listener.RenderListener;

public class ToggleESPCommand extends Command {
    private final RenderListener renderListener;
    public ToggleESPCommand(RenderListener renderListener) {
        super("toggleesp");
        this.renderListener = renderListener;
    }

    @Override
    public void handle(String[] args) {
        this.renderListener.renderESP = !this.renderListener.renderESP;
    }
}
