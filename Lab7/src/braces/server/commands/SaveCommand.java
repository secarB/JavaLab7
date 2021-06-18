package braces.server.commands;

import braces.server.core.CollectionManager;
/**
 * Class for save command
 */
public class SaveCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public SaveCommand(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute(String filename) {
        collectionManager.save(filename);
        return "done";
    }
}
