package braces.server.commands;

import java.util.Scanner;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
import braces.server.fields.SpaceMarine;
/**
 * Class for insert command
 */
public class InsertCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private Asker asker;
    public InsertCommand(CollectionManager collectionManager, Asker asker)
    {
        this.asker = asker;
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(String argument, SpaceMarine spaceMarine)
    {
        if (collectionManager.add(argument,spaceMarine)) return ("Added successfully");
        return("Key already exists");
      
    }
}
