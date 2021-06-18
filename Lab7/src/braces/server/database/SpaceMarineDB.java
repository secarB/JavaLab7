package braces.server.database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;

import braces.server.fields.AstartesCategory;
import braces.server.fields.Chapter;
import braces.server.fields.Coordinates;
import braces.server.fields.MeleeWeapon;
import braces.server.fields.SpaceMarine;

public class SpaceMarineDB {

	private static Connection connection;

	public SpaceMarineDB(Connection connection) throws SQLException {
		SpaceMarineDB.connection = connection;
		Statement statement = connection.createStatement();
		String createTableSQL ="CREATE TABLE IF NOT EXISTS list123 " + "(id  BIGSERIAL NOT NULL PRIMARY KEY, "
				+ "owner  VARCHAR(50) NOT NULL, " + "sp_name VARCHAR(50) NOT NULL, " + "x INT NOT NULL, "
				+ "y INT NOT NULL, " + "creationDate VARCHAR(50) NOT NULL, " + "health INT NOT NULL, " 
				+ "height INT NOT NULL, " + "category VARCHAR(50) NOT NULL, " + "meleeweapon VARCHAR(50) NOT NULL, "
				+ "chapter_name VARCHAR(50) NOT NULL, " + "legion VARCHAR(50) NOT NULL, " + "marine_count INT NOT NULL, " +"world VARCHAR(50) NOT NULL, "
				+ "key VARCHAR(50) NOT NULL )";
		statement.execute(createTableSQL);
	}

	public static boolean validation(String handle, String password) throws SQLException {
		/*
		 * CallableStatement callableStatement =
		 * connection.prepareCall("{? = call getNumberOfPerson()}");
		 * callableStatement.registerOutParameter(1, Types.INTEGER);
		 * callableStatement.execute(); int res = callableStatement.getInt(1);
		 * System.out.println(res);
		 */

		String query = "SELECT * FROM users;";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			if (resultSet.getString(1).equals(handle) && resultSet.getString(2).equals(SecretBase.HashPsw(password))) {
				return true;
			}
		}
		return false;
	}

	public static boolean validationPermissionEdit(long id, String handle) throws SQLException {
		String query = "SELECT * FROM list123;";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			if (resultSet.getLong("id") == id && resultSet.getString("owner").equals(handle)) {
				return true;
			}
		}
		return false;
	}

	public static void clearCollectionOnDataBase(String owner) throws SQLException {
		String sql = "DELETE FROM list123 Where owner = ?;";
		// String sql = "DELETE FROM listperson WHERE owner = '" + owner + "';";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, owner);
		// Statement statement = connection.createStatement();
		preparedStatement.executeUpdate();
		// PersonBase.loadCollection(CollectionManager.getCollection());
	}

	public  static TreeMap<String, SpaceMarine> getTreeMap() throws SQLException {
		String query = " SELECT * FROM list123 ORDER by id";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		TreeMap<String, SpaceMarine> listSp = new TreeMap<String, SpaceMarine>();
		while (resultSet.next()) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			listSp.put(resultSet.getString(15),new SpaceMarine(
					resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), new Coordinates(
							resultSet.getInt(4), resultSet.getLong(5)),
					LocalDate.parse(resultSet.getString(6), dtf), resultSet.getLong(7), resultSet.getLong(8),
					AstartesCategory.valueOf(resultSet.getString(9)), MeleeWeapon.valueOf(resultSet.getString(10)),
					new Chapter(resultSet.getString(11), resultSet.getString(12), resultSet.getInt(13),
							resultSet.getString(14)),
					resultSet.getString(15)));
		}
		return listSp;
	}

	public void addSpaceMarineToDB(SpaceMarine spaceMarine, long id) throws SQLException {
		if (id == -1) {
			String sql = "INSERT INTO list123 (owner, sp_name, x, y, creationDate, health, height, "
					+ "category, meleeweapon , chapter_name, legion, marine_count,world,key ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, spaceMarine.getOwner());
			preparedStatement.setString(2, spaceMarine.getName());
			preparedStatement.setInt(3, spaceMarine.getCoordinates().getXCoordinate());
			preparedStatement.setDouble(4, spaceMarine.getCoordinates().getYCoordinate());
			LocalDate localDate = spaceMarine.getDate();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String strDate = localDate.format(dtf);
			preparedStatement.setString(5, strDate);
			preparedStatement.setLong(6, spaceMarine.getHeight());
			preparedStatement.setLong(7, spaceMarine.getHealth());
			preparedStatement.setString(8, spaceMarine.getCategory().toString());
			preparedStatement.setString(9, spaceMarine.getMeleeWeapon().toString());
			preparedStatement.setString(10, spaceMarine.getChapter().getName());
			preparedStatement.setString(11, spaceMarine.getChapter().getParentLegion());
			preparedStatement.setInt(12, spaceMarine.getChapter().getMarinesCount());
			preparedStatement.setString(13, spaceMarine.getChapter().getWorld());
			preparedStatement.setString(14, spaceMarine.getKey());

			preparedStatement.execute();
		} else {
			String sql = "INSERT INTO list123 (id, owner, sp_name, x, y, creationDate, health, height, "
					+ "category, meleeweapon , chapter_name, legion, marine_count,world,key ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.setString(2, spaceMarine.getOwner());
			preparedStatement.setString(3, spaceMarine.getName());
			preparedStatement.setInt(4, spaceMarine.getCoordinates().getXCoordinate());
			preparedStatement.setDouble(5, spaceMarine.getCoordinates().getYCoordinate());
			LocalDate localDate = spaceMarine.getDate();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String strDate = localDate.format(dtf);
			preparedStatement.setString(6, strDate);
			preparedStatement.setLong(7, spaceMarine.getHeight());
			preparedStatement.setLong(8, spaceMarine.getHealth());
			preparedStatement.setString(9, spaceMarine.getCategory().toString());
			preparedStatement.setString(10, spaceMarine.getMeleeWeapon().toString());
			preparedStatement.setString(11, spaceMarine.getChapter().getName());
			preparedStatement.setString(12, spaceMarine.getChapter().getParentLegion());
			preparedStatement.setInt(13, spaceMarine.getChapter().getMarinesCount());
			preparedStatement.setString(14, spaceMarine.getChapter().getWorld());
			preparedStatement.setString(15, spaceMarine.getKey());
			preparedStatement.execute();
		}
	}

	public void deleteOrganizationFromDataBase(long deleteId) throws SQLException {
		 String sql = "DELETE FROM list123 WHERE id = ?;"; 
		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
		 preparedStatement.setLong(1, deleteId); preparedStatement.executeUpdate();
	}
}