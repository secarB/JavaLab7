package braces.server.commands;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
/**
 * Class for help command
 */
public class HelpCommand extends AbstractCommand{
    private CollectionManager   collectionManager;
    public HelpCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute() {
        return collectionManager.help();
    }
}
