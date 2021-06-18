package braces.client.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.CheckedOutputStream;

import javax.management.loading.PrivateClassLoader;

public class ClientConsole {
	private ClientAsker clientAsker;
	private ClientAsker clientAsker2;
	private final HashMap<String, Boolean> inStack = new HashMap<>();

	public ClientConsole(ClientAsker clientAsker, ClientAsker clientAsker2) {
		this.clientAsker = clientAsker;
		this.clientAsker2 = clientAsker2;
	}

	private boolean scriptMode(String fileName) throws IOException, InterruptedException {

		System.out.println("Executing script file " + fileName);
		if (inStack.get(fileName) != null) {
			if (inStack.get(fileName)) {
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
			clientAsker2.changeScanner(fileScanner);
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

			switch (userCommand[0]) {
			case "help":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("help", "", null);
				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "":
				break;
			case "info":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("info", "", null);
				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "show":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("show", "", null);
				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "insert":
				if (userCommand.length == 2) {
					System.out.println(userCommand[1]);
					ClientRun.readyToExchange("insert", userCommand[1], clientAsker2.createSpaceMarine());
				} else
					System.out.println("Please insert the key");
				break;
			case "clear":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("clear", "", null);

				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "exit":
				if (userCommand.length == 1) {

					ClientRun.readyToExchange("exit", "", null);
					break;
				} else
					System.out.println("This command doesn't support argument!");
			case "update":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("update", userCommand[1], clientAsker2.updateSpaceMarine());
					break;
				} else
					System.out.println("Please insert the id");
			case "remove_key":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("remove_key", userCommand[1], null);

				} else
					System.out.println("Please insert the id");
				break;
			case "execute_script":
				if (userCommand.length == 2) {
					scriptMode(userCommand[1]);
				} else
					System.out.println("Please insert script_file!");
				break;
			case "remove_greater":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("remove_greater", "", clientAsker2.createSpaceMarine());
					break;
				}
				System.out.println("This command doesn't support argument!");
			case "remove_lower":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("remove_lower", "", clientAsker2.createSpaceMarine());

				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "replace_if_greater":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("remove_if_greater", userCommand[1], clientAsker2.createSpaceMarine());

				} else
					System.out.println("Please insert the key");
				break;

			case "average_of_health":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("average_of_health", "", null);

				} else
					System.out.println("This command doesn't support argument!");
				break;

			case "min_by_health":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("min_by_health", "", null);
				} else
					System.out.println("This command doesn't support argument!");
				break;

			case "count_greater_than_height":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("count_greater_than_height", userCommand[1], null);

				} else
					System.out.println("Please insert height");
				break;

			case "save":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("save", userCommand[2], null);
				}
				System.out.println("Please insert filename");
				break;
			case "register":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("register", userCommand[2] + " " + userCommand[3], null);
				}
				System.out.println("Please insert user and password");
				break;
			default:
				System.out.println("Your command is not supported. Please insert correct name!\n"
						+ "Use help command to show the guideline.");
				break;

			}
		}
		inStack.put(fileName, false);
		return true;
	}

	public void userMode() throws IOException, ClassNotFoundException, InterruptedException {
		System.out.print("* ");
		Scanner userScanner = new Scanner(System.in);
		while (userScanner.hasNextLine()) {
			String[] userCommand = userScanner.nextLine().trim().split(" ");
			if (userCommand.length > 2) {
				System.out.println(
						"Invalid command! Valid command should contain 1 or 2 arguments." + " Please insert again!");
				System.out.print("* ");
				continue;
			}
			if (userCommand[0].equals("Exit")) {
				System.exit(0);
			}
			switch (userCommand[0]) {
			case "help":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("help", "", null);
				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "":
				break;
			case "info":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("info", "", null);

				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "show":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("show", "", null);
				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "insert":
				if (userCommand.length == 2) {
					System.out.println(userCommand[1]);
					ClientRun.readyToExchange("insert", userCommand[1], clientAsker.createSpaceMarine());
				} else
					System.out.println("Please insert the key");
				break;
			case "clear":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("clear", "", null);

				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "exit":
				if (userCommand.length == 1) {

					ClientRun.readyToExchange("exit", "", null);
					break;
				} else
					System.out.println("This command doesn't support argument!");
			case "update":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("update", userCommand[1], clientAsker.updateSpaceMarine());
					break;
				} else
					System.out.println("Please insert the id");
			case "remove_key":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("remove_key", userCommand[1], null);

				} else
					System.out.println("Please insert the id");
				break;
			case "execute_script":
				if (userCommand.length == 2) {
					scriptMode(userCommand[1]);
				} else
					System.out.println("Please insert script_file!");
				break;
			case "remove_greater":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("remove_greater", "", clientAsker.createSpaceMarine());
					break;
				}
				System.out.println("This command doesn't support argument!");
			case "remove_lower":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("remove_lower", "", clientAsker.createSpaceMarine());

				} else
					System.out.println("This command doesn't support argument!");
				break;
			case "replace_if_greater":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("remove_if_greater", userCommand[1], clientAsker.createSpaceMarine());

				} else
					System.out.println("Please insert the key");
				break;

			case "average_of_health":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("average_of_health", "", null);

				} else
					System.out.println("This command doesn't support argument!");
				break;

			case "min_by_health":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("min_by_health", "", null);
				} else
					System.out.println("This command doesn't support argument!");
				break;

			case "count_greater_than_height":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("count_greater_than_height", userCommand[1], null);

				} else
					System.out.println("Please insert height");
				break;

			case "save":
				if (userCommand.length == 2) {
					ClientRun.readyToExchange("save", userCommand[2], null);
				}
				System.out.println("Please insert filename");
				break;
			case "register":
				if (userCommand.length == 1) {
					System.out.println("Insert user ");
					String user = "";
					String pass = "";
					if (userScanner.hasNext()) {
						String[] input2 = userScanner.nextLine().trim().split(" ");
						while (input2[0].equals("")) {
							if (userScanner.hasNext()) {
								input2 = userScanner.nextLine().trim().split(" ");
							}
						}
						user = input2[0];
					}
					System.out.println("Insert password ");
					if (userScanner.hasNext()) {
						String[] input3 = userScanner.nextLine().trim().split(" ");
						while (input3[0].equals(""))
							if (userScanner.hasNext()) {
								input3 = userScanner.nextLine().trim().split(" ");
							}
						pass = input3[0];
					}
					ClientRun.readyToExchange("register", user + " " + pass, null);
				} else {
					System.out.println("Please insert only command name");
				}
				break;
			case "login":
				if (userCommand.length == 1) {
					System.out.println("Insert user ");
					String user = "";
					String pass = "";
					if (userScanner.hasNext()) {
						String[] input2 = userScanner.nextLine().trim().split(" ");
						while (input2[0].equals("")) {
							if (userScanner.hasNext()) {
								input2 = userScanner.nextLine().trim().split(" ");
							}
						}
						user = input2[0];
					}
					System.out.println("Insert password ");
					if (userScanner.hasNext()) {
						String[] input3 = userScanner.nextLine().trim().split(" ");
						while (input3[0].equals(""))
							if (userScanner.hasNext()) {
								input3 = userScanner.nextLine().trim().split(" ");
							}
						pass = input3[0];
					}
					ClientRun.readyToExchange("login", user + " " + pass, null);
				} else {
					System.out.println("Please insert only command name");
				}
				break;
			case "logout":
				if (userCommand.length == 1) {
					ClientRun.readyToExchange("logout", null, null);
				} else {
					System.out.println("Please inser only command name");
				}
				break;
			default:
				System.out.println("Your command is not supported. Please insert correct name!\n"
						+ "Use help command to show the guideline.");
				break;

			}
			System.out.print("* ");
		}
	}

}
