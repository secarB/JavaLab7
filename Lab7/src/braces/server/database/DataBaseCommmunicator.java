package braces.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

import braces.server.fields.SpaceMarine;

public class DataBaseCommmunicator {

	// on helios
	// private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";

	// on local PC
	private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

	private static Connection connection;
	private static SpaceMarineDB spaceMarineDB;
	private static SecretBase users;

	public void start() {
		try {
		/*	Scanner scanner = new Scanner(System.in);
			System.out.print("Enter your account:");
			// System.out.println("Enter your account:");
			String user = scanner.nextLine();
			System.out.println("Enter your password:");
			// System.out.println("Enter your password:");
			String pass = scanner.nextLine(); */
			connection = DriverManager.getConnection("jdbc:postgresql://pg:5432/studs","","h");
		//	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");

			users = new SecretBase(connection);
			spaceMarineDB = new SpaceMarineDB(connection);
		} catch (SQLException e) {

			e.printStackTrace();
			// System.exit(0);
		}

		if (connection != null) {
			System.out.println("Successfully connect to DataBase!");
		} else {
			System.out.println("Unsuccessfully connect to DataBase!");
			System.exit(0);
		}
	}

	public static SpaceMarineDB getSpaceMarineDB() {
		return spaceMarineDB;
	}

	public static SecretBase getUsers() {
		return users;
	}
}