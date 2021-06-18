package braces.server.commands;

import java.util.Scanner;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
import braces.server.fields.SpaceMarine;
/**
 * Class for update command
 */
public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private Asker asker;
    public  UpdateCommand(CollectionManager collectionManager, Asker asker)
    {
        this.asker = asker;
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(String argument,SpaceMarine spaceMarine) {
        long id = Long.parseLong(argument);
        if (collectionManager.update(id, spaceMarine)) return "Updated successfully";
        return("Can't find id:");
    }
}
