package braces.server.core;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

import braces.server.fields.*;

/**
 * This class use to get info when create a new element
 */
public class Asker {
    private Scanner scanner	= new Scanner(System.in);
    private final InputChecker inputChecker;
    /**
     * Constructor
     * @param ic input checker
     */
    public Asker(InputChecker ic){
        this.inputChecker = ic;
    }
	public void changeScanner(Scanner fromFile) {
		scanner = fromFile;	
	}
	public void backScanner()
	{
		Scanner fromKeyBoard = new Scanner(System.in);
		scanner = fromKeyBoard;
	}
    /**
     * Returns a space marine
     * @return space marine to be returned
     */
    public SpaceMarine createSpaceMarine() 
    {
    	
        SpaceMarine spaceMarine = new SpaceMarine();
        
        spaceMarine.setId(generateID());
    	System.out.println("Insert name: ");
        spaceMarine.setName(nameAsker());
        System.out.println("Insert coordinates: ");
        System.out.println("Insert x and y:");
        spaceMarine.setCoordinates(coordinatesAsker());
        System.out.println("Insert height: ");
        spaceMarine.setHeight(heightAsker());
        System.out.println("Insert health: ");
        spaceMarine.setHealth(healthAsker());
        System.out.println("Insert Astartes Category: ");
        System.out.println("Please insert one of these following categories");
        for (AstartesCategory category : AstartesCategory.values()){
            System.out.println(category);
        }
        spaceMarine.setCategory(categoryAsker());
        System.out.println("Insert Melee Weapon: ");
        System.out.println("Please insert one of these following melee weapons");
        for (MeleeWeapon meleeWeapon : MeleeWeapon.values()){
            System.out.println(meleeWeapon);
        }
        spaceMarine.setMeleeWeapon(meleeWeaponAsker());
        spaceMarine.setChapter(chapterAsker());
        LocalDate date = LocalDate.now();
        spaceMarine.setCreationDate(date);
        return spaceMarine;
    }
    public SpaceMarine updateSpaceMarine(long id) {
    	SpaceMarine spaceMarine = new SpaceMarine();
        spaceMarine.setName(nameAsker());
        spaceMarine.setId(id);
        spaceMarine.setCoordinates(coordinatesAsker());
        spaceMarine.setHeight(heightAsker());
        spaceMarine.setHealth(healthAsker());
        spaceMarine.setCategory(categoryAsker());
        spaceMarine.setMeleeWeapon(meleeWeaponAsker());
        spaceMarine.setChapter(chapterAsker());
        LocalDate date = LocalDate.now();
        spaceMarine.setCreationDate(date);
        return spaceMarine;
	}

    /**
     * Automatically generates an ID
     * @return id
     */
    public long generateID()
    {
        Integer newID = new Random().nextInt();
        if (CollectionManager.IDChecker.contains(newID) || newID <= 0)
            return generateID();
        else
        {
            CollectionManager.IDChecker.add(newID);
            System.out.println("ID = " + newID + " is successfully generated!");
            return newID;
        }
    }

    /**
     * Gets name from scanner
     * @return name
     */
    public String nameAsker()
    {
        while (scanner.hasNextLine()) {
        	String[] input = scanner.nextLine().trim().split(" ");
            if (input.length != 1) {
                System.out.println("Please insert 1 name!");
            } else {
            	if ((input[0] != null) && (!input[0].equals(""))) return input[0];
            	System.out.println("Name can't be null or empty");
            }
        } 
        return nameAsker();
   
    }

    /**
     * Gets coordinates from scanner
     * @return coordinates
     */
    public Coordinates coordinatesAsker(){
        while (scanner.hasNext()) {
            Coordinates coordinates = new Coordinates();
            String[] input = scanner.nextLine().trim().split(" ");
        	if (input.length == 1 && input[0].equals("")) continue;
            if(input.length != 2 ){
                System.out.println("please insert exactly two number!");
            }
            else
            {   if (!inputChecker.integerValidCheck(input[0],Integer.MIN_VALUE,Integer.MAX_VALUE))
                    continue;
                if (!inputChecker.longValidCheck(input[0],Long.MIN_VALUE,(long)316))
                    continue;
                coordinates.setXCoordinate(Integer.parseInt(input[0]));
                coordinates.setYCoordinate(Long.parseLong(input[1]));
                return coordinates;
            }
        }
        return coordinatesAsker();
    }

    /**
     * Gets current date
     * @return current date
     */
    public LocalDateTime dateGenerator(){
        return java.time.LocalDateTime.now();
    }

    /**
     * Gets health from scanner
     * @return health
     */
    public long healthAsker()
    {
        while (scanner.hasNext()) {
            String[] input = scanner.nextLine().trim().split(" ");
        	if (input.length == 1 && input[0].equals("")) continue;
            if (input.length != 1) {
                System.out.println("Please insert exactly one number!");
            } else {
                if (!inputChecker.longValidCheck(input[0], (long) 1, Long.MAX_VALUE)) continue;
                return Long.parseLong(input[0]);
            }
        }
        return healthAsker();
    }

    /**
     * Gets height from scanner
     * @return height
     */
    public long heightAsker()
    {
        while (scanner.hasNext())
        {
            String[] input = scanner.nextLine().trim().split(" ");
        	if (input.length == 1 && input[0].equals("")) continue;
            if(input.length != 1 ) {
                System.out.println("Please insert exactly one number!");
            }
            else
            {
                if (!inputChecker.longValidCheck(input[0], Long.MIN_VALUE, Long.MAX_VALUE)) continue;
                return Long.parseLong(input[0]);
            }
        }
        return heightAsker();
    }

    /**
     * Gets category from scanner
     * @return Astartes Category
     */
    public AstartesCategory categoryAsker()
    {
        if (scanner.hasNext()) {
	        String[] input = scanner.nextLine().trim().split(" ");
	        if(input.length != 1 ) {
	            System.out.println("Please insert exactly one category!");
	            return categoryAsker();
	        }
	        else
	        {
	            try {
	                return AstartesCategory.valueOf(input[0]);
	            } catch(IllegalArgumentException e){
	                System.out.println("Invalid category! The category is not in the list!");
	                return categoryAsker();
	            }
	        }
        }
        else 
        {
        	return categoryAsker();
        }
    }

    /**
     * Gets melee weapon from scanner
     * @return Melee weapon
     */
    public MeleeWeapon meleeWeaponAsker()
    {
       
        if (scanner.hasNext()) {
	        String[] input = scanner.nextLine().trim().split(" ");
	        if(input.length != 1 ) {
	            System.out.println("Please insert exactly one melee weapon!");
	            return meleeWeaponAsker();
	        }
	        else
	        {
	
	            try {
	                return  MeleeWeapon.valueOf(input[0]);
	            } catch(IllegalArgumentException e){
	                System.out.println("Invalid Melee Weapon! The melee weapon is not in the list!");
	                return meleeWeaponAsker();
	            }
	        }
        } else {
        	return meleeWeaponAsker();
        }
    }

    /**
     * Gets chapter from scanner
     * @return Chapter
     */
    public Chapter chapterAsker()
    {
        Chapter chapter = new Chapter();
        System.out.println("Insert name, parent legion, marines count, world: ");
        /* while (true) {
        	  String[] input = scanner.nextLine().trim().split(" ");
        	  if (input.length != 4)
        		  System.out.println("Insert 4 para"); else {
	        	  if (!chapter.setName(input[0])) continue;
	        	  chapter.setParentLegion(input[1]);
	        	  if (!inputChecker.integerValidCheck(input[2],1,1000)) {
	        		  chapter.setMarinesCount(Integer.parseInt(input[2]));
	        		  continue;
	        		  }
	        	  if (!chapter.setWorld(input[3])) continue;
	        	  return chapter; 
        		  }
        	  
		} */
        System.out.println("Insert chapter name: ");
        while (scanner.hasNext()) {
            String[] input = scanner.nextLine().trim().split(" ");
            if (input.length != 1) {
                System.out.println("Please insert one chapter name");
                } else {
                    if (chapter.setName(input[0])) break;
                System.out.println("Name can be null or empty, please enter a name :<");
                }
        }
        System.out.println("Insert chapter parent legion: ");
        if (scanner.hasNext()) {
        String[] input = scanner.nextLine().trim().split(" "); 
        chapter.setParentLegion(input[0]); }
        System.out.println("Insert chapter marines count: ");
        while (scanner.hasNext())
        {
            String[] inputt = scanner.nextLine().trim().split(" ");
            if (inputt.length != 1) {
                System.out.println("Please insert one chapter marines count");
            } else {
                if (inputChecker.integerValidCheck(inputt[0],0,1000))
                {
                    chapter.setMarinesCount(Integer.parseInt(inputt[0]));
                    break;
                }
            }
        }
        System.out.println("Insert chapter world ");
        while (scanner.hasNext()) {
            String[] inputt = scanner.nextLine().trim().split(" ");
            if (inputt.length != 1) {
                System.out.println("Please insert one chapter world");
            } else {
                if (chapter.setWorld(inputt[0])) break;
            }
        }
        return chapter;
        }




}
