package braces.server.commands;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
/**
 * Class for clear command
 */
public class ClearCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(String argument) {
        return collectionManager.clear(argument);
    }
}
