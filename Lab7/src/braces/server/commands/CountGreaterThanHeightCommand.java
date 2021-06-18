package braces.server.commands;

import braces.server.core.CollectionManager;
import braces.server.core.InputChecker;
/**
 * Class for count greater than height command
 */
public class CountGreaterThanHeightCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private InputChecker inputChecker;
    public CountGreaterThanHeightCommand(CollectionManager collectionManager,InputChecker inputChecker)
    {
        this.collectionManager = collectionManager;
        this.inputChecker = inputChecker;
    }
    @Override
    public String execute(String argument) {
        if (inputChecker.longValidCheck(argument, (long) 0 , Long.MAX_VALUE)) {
            long height = Long.parseLong(argument);
            return collectionManager.countGreaterThanHeight(height);
        }
        return "The height value is not in valid range";

    }
}
