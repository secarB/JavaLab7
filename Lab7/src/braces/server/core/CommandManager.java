package braces.server.core;

import java.util.Scanner;

import braces.server.commands.*;
import braces.server.fields.SpaceMarine;

/**
 * Executor of all commands
 */
public class CommandManager {
    private final Command ClearCommand;
    private final Command AverageOfHealthCommand;
    private final Command CountGreaterThanHeightCommand;
    private final Command ExitCommand;
    private final Command HelpCommand;
    private final Command InfoCommand;
    private final Command InsertCommand;
    private final Command MinByHealthCommand;
    private final Command RemoveCommand;
    private final Command RemoveGreaterCommand;
    private final Command ReplaceIfGreaterCommand;
    private final Command SaveCommand;
    private final Command ShowCommand;
    private final Command UpdateCommand;
    private final Command RemoveLowerCommand;

    public CommandManager(Command clearCommand, Command averageOfHealthCommand, Command countGreaterThanHeightCommand,
                          Command exitCommand, Command helpCommand, Command infoCommand,
                          Command insertCommand, Command minByHealthCommand, Command removeCommand,
                          Command removeGreaterCommand, Command replaceIfGreaterCommand,
                          Command saveCommand, Command showCommand, Command updateCommand, Command removeLowerCommand)
    {
        ClearCommand = clearCommand;
        AverageOfHealthCommand = averageOfHealthCommand;
        CountGreaterThanHeightCommand = countGreaterThanHeightCommand;
        ExitCommand = exitCommand;
        HelpCommand = helpCommand;
        InfoCommand = infoCommand;
        InsertCommand = insertCommand;
        MinByHealthCommand = minByHealthCommand;
        RemoveCommand = removeCommand;
        RemoveGreaterCommand = removeGreaterCommand;
        ReplaceIfGreaterCommand = replaceIfGreaterCommand;
        SaveCommand = saveCommand;
        ShowCommand = showCommand;
        UpdateCommand = updateCommand;
        RemoveLowerCommand = removeLowerCommand;
    }
    public String average()
    {
        return AverageOfHealthCommand.execute();
    }

    public String countGreaterThanHeight(String argument)
    {
        return CountGreaterThanHeightCommand.execute(argument);
    }
    public String insert(String argument,SpaceMarine spaceMarine)
    {
        return  InsertCommand.execute(argument,spaceMarine);
    }
    public String minByHealth(String argument)
    {
        return MinByHealthCommand.execute(argument);
    }
    public String remove(String argument, String owner)
    {
        return RemoveCommand.execute(argument, owner);
    }
    public String removeGreater(SpaceMarine spaceMarine)
    {
        return RemoveGreaterCommand.execute(spaceMarine);
    }
    public String removeLower(SpaceMarine spaceMarine)
    {
        return RemoveLowerCommand.execute(spaceMarine);
    }
    public String replaceIfGreater(String argument, SpaceMarine spaceMarine)
    {
        return ReplaceIfGreaterCommand.execute(argument,spaceMarine);
    }
    public String update(String argument,SpaceMarine spaceMarine)
    {
        return UpdateCommand.execute(argument,spaceMarine);
    }
    public String clear(String argument)
    {
        return ClearCommand.execute(argument);
    }
    public String help(){
        return HelpCommand.execute();
    }
    public String info(){
        return InfoCommand.execute();
    }
    public String show(){
        return ShowCommand.execute();
    }
    public String save(String fileName){
        return SaveCommand.execute(fileName);
    }
    public String exit(){
        return ExitCommand.execute();
    }
}
