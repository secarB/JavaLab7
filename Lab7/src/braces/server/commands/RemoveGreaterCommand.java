package braces.server.commands;

import java.util.Scanner;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
import braces.server.fields.SpaceMarine;
/**
 * Class for remove greater command
 */
public class RemoveGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private Asker asker;
    public RemoveGreaterCommand(CollectionManager collectionManager, Asker asker)
    {
        this.asker = asker;
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(SpaceMarine spaceMarine) {
        collectionManager.removeGreater(spaceMarine);
        return "remove successful";
    }
}
