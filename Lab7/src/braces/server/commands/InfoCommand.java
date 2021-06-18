package braces.server.commands;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
/**
 * Class for info command
 */
public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public InfoCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        return collectionManager.info();
    }
}
