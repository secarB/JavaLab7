package braces.server.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import braces.server.fields.*;

/**
 * Class get data from CSV file
 */
public class CSVParser {
	private Date dateCreateCollection;

	/**
	 * method get date from CSV file and convert into TreeMap
	 * 
	 * @param fileName file name
	 * @return treemap after conversion
	 */
	public TreeMap<String, SpaceMarine> parse(String fileName) {
		Date dateCreation = new Date();
		this.dateCreateCollection = dateCreation;
		TreeMap<String, SpaceMarine> map = new TreeMap<>();
		Random rand = new Random();
		File file = new File(fileName);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			Scanner dataScanner = null;
			Integer id = rand.nextInt();
			while (scanner.hasNextLine()) {
				dataScanner = new Scanner(scanner.nextLine());
				dataScanner.useDelimiter(",");
				SpaceMarine spaceMarine = new SpaceMarine();
				spaceMarine.setId(id);
				boolean check = true;
				Chapter chapter = new Chapter();
				Coordinates coordinates = new Coordinates();
				String data[] = new String[12];
				int index = 0;
				String key = "";
				while (dataScanner.hasNext()) {
					if ((index == 0) && (key == "")) {
						key = dataScanner.next();
						continue;
					}
					data[index] = dataScanner.next();
					index++;
					if (index > 11) {
						System.err.println("This line is not valid ");
						break;
					}
				}
				try {
					if (!spaceMarine.setName(data[0])) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Name is not valid...");
				}
				try {
					if (!coordinates.setXCoordinate(Integer.parseInt(data[1]))) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("X coordinate is not valid...");
				}

				try {
					if (!coordinates.setYCoordinate(Long.parseLong(data[2]))) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Y coordinate is not valid...");
				}

				try {
					if (!spaceMarine.setCoordinates(coordinates)) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Coordinate  is not valid..");
				}
				try {
					if (!spaceMarine.setHealth(Long.parseLong(data[3]))) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Health is not valid..");
				}
				try {
					spaceMarine.setHeight(Long.parseLong(data[4]));
				} catch (NumberFormatException e) {
					check = false;
					System.err.println("Height is not valid ...");
				}
				try {
					if (!spaceMarine.setCategory(AstartesCategory.valueOf(data[5]))) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Category is not valid...");
				}
				try {
					if (!spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(data[6]))) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Melle Weapon is not valid...");
				}
				try {
					if (!chapter.setName(data[7])) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Chapter name is not valid...");
				}
				chapter.setParentLegion(data[8]);
				try {
					if (!chapter.setMarinesCount(Integer.parseInt(data[9]))) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("Marines count is not valid...");
				}

				try {
					if (!chapter.setWorld(data[10])) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("World is not valid...");
				}
				try {
					if (!spaceMarine.setChapter(chapter)) {
						throw new Exception();
					}
				} catch (Exception e) {
					check = false;
					System.err.println("This line is not valid...");
				}
				try {
					if (!check)
						throw new Exception();
					LocalDate date = LocalDate.now();
					spaceMarine.setCreationDate(date);
					map.put(key, spaceMarine);
					CollectionManager.IDChecker.add(id);
					id++;
				} catch (Exception e) {
					System.err.println("This line is not valid");
				}
				index = 0;
			}
		} catch (FileNotFoundException b) {
			System.out.println("Can't find file");
			System.exit(0);
		}
		return map;
	}

	/**
	 * Get date creation the collection
	 * 
	 * @return date creation the collection
	 */
	public Date getDateCreateCollection() {
		return dateCreateCollection;
	}

}
