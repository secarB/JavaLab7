package braces.server.core;

import java.sql.SQLException;
import java.util.Scanner;

import braces.Exchanger.ClientExchanger;
import braces.server.database.DataBaseCommmunicator;
import braces.server.database.SpaceMarineDB;
import braces.server.fields.SpaceMarine;

public class Handler {
	private CommandManager commandManager;
	private String loginString = "";
	private String passWordString;
	public Handler(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

	public ClientExchanger startCommand(ClientExchanger s) {
		try {
			switch (s.getName().trim()) {
			case "register":
				String[] infoStrings = s.getArgument().trim().split(" ");
				if (DataBaseCommmunicator.getUsers().isExisting("handle", infoStrings[0])) {
					s.setAnswer("user already exist");
				} else {
					DataBaseCommmunicator.getUsers().addUserToDataBase(infoStrings[0],infoStrings[1]);
					s.setAnswer("register successful" );
				}
				break;
			case "login":
				String[] infoLogin = s.getArgument().trim().split(" ");
				if (SpaceMarineDB.validation(infoLogin[0], infoLogin[1])) {
					s.setAnswer("login successful");
					this.loginString = infoLogin[0];
				} else 
				{	
					s.setAnswer("Wrong user or password! Please try again");
				}
				break;
			case "logout":
				if (!this.loginString.equals("")) {
					s.setAnswer("logout succesful");
					this.loginString = "";
				} else 
				{
					s.setAnswer("Can't log out because not logged in");
				}
				break;
			case "help":
				s.setAnswer(commandManager.help());
				break;
			case "info":
				s.setAnswer(commandManager.info());
				break;
			case "show":
				s.setAnswer(commandManager.show());
				break;
			case "insert":
				if (loginString.equals("")) s.setAnswer("Need login");  else {
				s.getSpaceMarine().setOwner(loginString);
				s.setAnswer(commandManager.insert(s.getArgument(), s.getSpaceMarine()));}
				break;
			case "clear":
				s.setAnswer(commandManager.clear(loginString));
				break;
			case "exit":
				s.setAnswer(commandManager.exit());
				break;
			case "update":
				if (loginString.equals("")) s.setAnswer("Need login");  else {
				s.getSpaceMarine().setOwner(loginString);
				s.setAnswer(commandManager.update(s.getArgument(), s.getSpaceMarine()));}
				break;
			case "remove_key":
				if (loginString.equals("")) s.setAnswer("Need login");  else {
				s.setAnswer(commandManager.remove(s.getArgument(),loginString));}
				break;
			case "remove_greater":
				if (loginString.equals("")) s.setAnswer("Need login");  else {
				s.getSpaceMarine().setOwner(loginString);
				s.setAnswer(commandManager.removeGreater(s.getSpaceMarine()));}
				break;
			case "remove_lower":
				if (loginString.equals("")) s.setAnswer("Need login");  else {
				s.getSpaceMarine().setOwner(loginString);
				s.setAnswer(commandManager.removeLower(s.getSpaceMarine()));}
				break;
			case "replace_if_greater":
				if (loginString.equals("")) s.setAnswer("Need login");  else {
				s.getSpaceMarine().setOwner(loginString);
				s.setAnswer(commandManager.replaceIfGreater(s.getArgument(), s.getSpaceMarine()));}
				break;
			case "average_of_health":
				s.setAnswer(commandManager.average());
				break;
			case "min_by_health":
				s.setAnswer(commandManager.minByHealth(loginString));
				break;
			case "count_greater_than_height":
				s.setAnswer(commandManager.countGreaterThanHeight(s.getArgument()));
				break;
			case "save":
				s.setAnswer(commandManager.save(s.getArgument()));
				break;
			default:
				System.out.println("Your command is not supported. Please insert correct name!\n"
						+ "Use help command to show the guideline.");
				break;

			}
		} catch (ExceptionInInitializerError e) {
			System.out.println("Что-то пошло не так");
		} catch (SQLException e) {
			System.out.print("Error with database");
			e.printStackTrace();
		}
		return s;
	}
}
