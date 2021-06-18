package braces.server.commands;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
import braces.server.fields.SpaceMarine;
/**
 * Class for remove command
 */
public class RemoveCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public RemoveCommand(CollectionManager collectionManager, Asker asker)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(String argument, String owner) {
        return(collectionManager.remove(argument,owner));

    }
}
