package braces.server.commands;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
/**
 * Class for min by health command
 */
public class MinByHealthCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public  MinByHealthCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(String argument) {
        return collectionManager.minByHealth(argument);
    }
}
