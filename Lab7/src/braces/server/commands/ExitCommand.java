package braces.server.commands;

import braces.server.core.CollectionManager;
/**
 * Class for exit command
 */
public class ExitCommand extends AbstractCommand{
    @Override
    public String execute() {
        System.exit(0);
        return "";
    }
}
