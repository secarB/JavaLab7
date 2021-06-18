package braces.server.main;

import braces.server.commands.*;
import braces.server.core.*;
import braces.server.database.DataBaseCommmunicator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Main class
 * @author Braces
 * @version 1.0
 */
public class ServerMain
{
    static String fileName ;
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, ExecutionException {
  /*      try {
       fileName = "E:/test.csv";
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please insert file input via command line argument!");
            System.exit(-1);
        }*/
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.connect();
        InputChecker inputChecker = new InputChecker();
    //    collectionManager.readInput(fileName);
        Asker asker = new Asker(inputChecker);
        

       /* Handler handler = new Handler(commandManager);
        ServerRun serverRun = new ServerRun(handler);
        try {
			serverRun.start();
		} catch (ClassNotFoundException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        Runnable server = new Runnable() {
            @Override
            public void run() {
                 try {
                	 
                    try {
                    	CommandManager commandManager = new CommandManager(
                                new ClearCommand(collectionManager),
                                new AverageOfHealthCommand(collectionManager),
                                new CountGreaterThanHeightCommand(collectionManager,inputChecker),
                                new ExitCommand(),
                                new HelpCommand(collectionManager),
                                new InfoCommand(collectionManager),
                                new InsertCommand(collectionManager,asker),
                                new MinByHealthCommand(collectionManager),
                                new RemoveCommand(collectionManager,asker),
                                new RemoveGreaterCommand(collectionManager,asker),
                                new ReplaceIfGreaterCommand(collectionManager,asker),
                                new SaveCommand(collectionManager),
                                new ShowCommand(collectionManager),
                                new UpdateCommand(collectionManager,asker),
                                new RemoveLowerCommand(collectionManager,asker)
                        );
						new ServerRun(commandManager).start();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 
            }
        };

        new Thread(server).start();
    }
}
