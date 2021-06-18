package braces.server.core;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class used to get commands and execute them
 */
public class Commander {
   private final String outputFile;
    private final Scanner userScanner;
    private final HashMap<String, Boolean> inStack = new HashMap<>();
    private final CommandManager commandManager;

    /**
     * Constructor
     * @param outputFile Filename to save
     * @param userScanner Scanner
     * @param commandManager  All command
     */
    public Commander(String outputFile, Scanner userScanner, CommandManager commandManager) {
        this.outputFile = outputFile;
        this.userScanner = userScanner;
        this.commandManager = commandManager;
    }

    /**
     * Interactive mode when run program
     * @throws Exception 
     */
    /**  public void interactiveMode() 
    {
        System.out.print("* ");
        while (userScanner.hasNextLine())
        {
            String[] userCommand  = userScanner.nextLine().trim().split(" ");
            if(userCommand.length > 2 ){
                System.out.println("Invalid command! Valid command should contain 1 or 2 arguments." +
                        " Please insert again!");
                System.out.print("* ");
                continue;
            }
            if(userCommand[0].equals("Exit")) {
                System.exit(0);
            }
            if(categorizeCommand(userCommand,new Scanner(System.in))){
                System.out.println("Command is invalid. Can't execute!"); 
                System.out.print("* ");
                continue;
            } else 
            {
            	System.out.print("* ");
            }
        }

    }

    /**
     * Script mode for the execute_script command
     * @param fileName FileName executing
     * @return true/false if the file is successfully executed
     */
    /**private boolean scriptMode(String fileName){

      System.out.println("Executing script file " + fileName);
        if(inStack.get(fileName) != null){
            if(inStack.get(fileName)){
                System.out.println("To avoid infinite recursion. File " + fileName + " can't be executed.");
                return false;
            }
        }
        inStack.put(fileName, true);
        File scriptFile = new File(fileName);
        System.out.println(fileName);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(scriptFile);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Script file doesn't exist. Please check the file path!");
            return false;
        }
        while (fileScanner.hasNextLine()) {
            String[] userCommand = fileScanner.nextLine().trim().split(" ");
            if (userCommand.length > 2) {
                System.out.println("Can't execute! Invalid command! Valid command should contain 1 or 2 arguments.");
                continue;
            }
            if (categorizeCommand(userCommand,fileScanner)) {
                System.out.println("Command is invalid. Can't execute!");
                continue;
            }
        }
        inStack.put(fileName, false);
        return true;
    }

    /**
     * Get command in interactive mode and execute them
     * @param userCommand command from interactive mode
     * @return true/false if the command is successfully executed
     */
    /**   public boolean categorizeCommand(String [] userCommand, Scanner fileScanner)
    {
        switch (userCommand[0]) {
            case "help":
                if(userCommand.length == 1) {
                    return !commandManager.help();
                }
                return true;
            case "":
            	return false;
            case "info":
                if(userCommand.length == 1) {
                    return !commandManager.info();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "show":
                if(userCommand.length == 1){
                    return !commandManager.show();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "insert":
                if(userCommand.length == 2){
                    return !commandManager.insert(userCommand[1],fileScanner);
                }
                System.out.println("Please insert the key");
                return true;
            case "clear":
                if(userCommand.length == 1){
                    return !commandManager.clear();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "exit":
                if(userCommand.length == 1) {
                    return !commandManager.exit();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "update":
                if(userCommand.length == 2){
                    return !commandManager.update(userCommand[1],fileScanner);
                }
                System.out.println("Please insert the id");
                return true;
            case "remove_key":
                if(userCommand.length == 2){
                    return !commandManager.remove(userCommand[1]);
                }
                System.out.println("Please insert the id");
                return true;
            case "execute_script":
                if(userCommand.length != 1){
                    return !scriptMode(userCommand[1]);
                }
                System.out.println("Please insert script_file!");
                return true;
            case "remove_greater":
                if(userCommand.length == 1) {
                    return !commandManager.removeGreater(fileScanner);
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "remove_lower":
                if(userCommand.length == 1) {
                    return !commandManager.removeLower(fileScanner);
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "replace_if_greater":
                if(userCommand.length == 2){
                    return !commandManager.replaceIfGreater(userCommand[1],fileScanner);
                }
                System.out.println("Please insert the key");
                return true;
            case "average_of_health":
                if(userCommand.length == 1){
                    return !commandManager.average();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "min_by_health":
                if(userCommand.length == 1){
                    return !commandManager.minByHealth();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "count_greater_than_height":
                if(userCommand.length == 2){
                    return !commandManager.countGreaterThanHeight(userCommand[1]);
                }
                System.out.println("Please insert height");
                return true;
            case "save":
                if(userCommand.length == 2){
                    return !commandManager.save(userCommand[1]);
                }
                System.out.println("Please insert filename");
                return true;
            default:
                System.out.println("Your command is not supported. Please insert correct name!\n" +
                        "Use help command to show the guideline.");
                return true;
        }
    }
    */
}
