package braces.server.commands;

import java.util.Scanner;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
import braces.server.fields.SpaceMarine;
/**
 * Class for replace if greater command
 */
public class ReplaceIfGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private Asker asker;
    public ReplaceIfGreaterCommand(CollectionManager collectionManager, Asker asker)
    {
        this.asker = asker;
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(String argument,SpaceMarine spaceMarine) {
        if (collectionManager.replaceGreater(argument, spaceMarine)) return "replaced successful";
        return("Key does not exist");
    }
}
