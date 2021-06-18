package braces.server.commands;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
/**
 * Class for the get average of health command
 */
public class AverageOfHealthCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public  AverageOfHealthCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute() {
        return collectionManager.getAverageOfHealth();
    }
}
