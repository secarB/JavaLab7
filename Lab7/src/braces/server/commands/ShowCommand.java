package braces.server.commands;

import braces.server.core.Asker;
import braces.server.core.CollectionManager;
/**
 * Class for show command
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Asker asker;
    public ShowCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute() {
        return collectionManager.show();
    }
}
