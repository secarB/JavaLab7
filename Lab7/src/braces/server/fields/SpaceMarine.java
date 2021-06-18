package braces.server.fields;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import javax.security.auth.x500.X500Principal;

/**
 * Class space marine
 */
public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {
    private static final long serialVersionUID = 1L;
    private long id; //The field value must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically
    private String name; //Can't be null, String can't be empty
    private Coordinates coordinates; //Can't be null
    private java.time.LocalDate creationDate; //Can't be null, the value of this field must be generated automatically
    private long health; //Can be null, Field value must be greater than 0
    private long height;
    private AstartesCategory category; //Can be null
    private MeleeWeapon meleeWeapon; //Can't be null
    private Chapter chapter; //Can't be null
    private String key;
    private String owner;

	public SpaceMarine() {
		// TODO Auto-generated constructor stub
	}
	   public SpaceMarine( long id,String owner, String name, Coordinates coordinates, LocalDate creationDate, long health, long height, AstartesCategory category, MeleeWeapon meleeWeapon, Chapter chapter, String key) {
	        this.id = id;
	        this.name = name;
	        this.coordinates = coordinates;
	        this.creationDate = creationDate;
	        this.health = health;
	        this.height = height;
	        this.category = category;
	        this.meleeWeapon = meleeWeapon;
	        this.chapter = chapter;
	        this.key = key;
	        this.owner = owner;
	    }
	public String getKey() {
		return this.key;
		
	}
	public void setKey(String key) {
		this.key = key;
		
	}
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	public LocalDate getDate()
	{
		return this.creationDate;
	}
	public String getOwner() {
		return this.owner;
	}
	
	public long getId() {
        return id;
    }
    public boolean setId(long id) {
        if (id < 0) return false;
        this.id = id;
        return true;
    }
    public String getName() {
        return name;
    }
    public boolean setName(String name) {
        if (name == null || name.equals("")) return false;
        this.name = name;
        return true;
    }
    public Coordinates  getCoordinates() {
        return coordinates;
    }
    public boolean setCoordinates(Coordinates coordinates) {
        if (coordinates == null) return false;
        this.coordinates = coordinates;
        return true;
    }
    public Long getHealth() {
        return health;
    }
    public boolean setHealth(Long health) {
        if (health == null || health <= 0) return false;
        this.health = health;
        return true;
    }
    public long getHeight() {
        return height;
    }
    public void setHeight(long height) {
        this.height = height;
    }
    public String getCreationDate() {
        return this.creationDate.toString();
    }
    public LocalDate getCreationDateTime()
    {
    	return this.creationDate;
    }
    public void setCreationDate(java.time.LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public String getCategory() {
        return category.toString();
    }
    public boolean setCategory(AstartesCategory category) {
        if (category == null) return false;
        this.category = category;
        return true;
    }
    public String getMeleeWeapon() {
        return meleeWeapon.toString();
    }
    public boolean setMeleeWeapon(MeleeWeapon meleeWeapon) {
        if (meleeWeapon == null) return false;
        this.meleeWeapon = meleeWeapon;
        return true;
    }
    public Chapter getChapter() {
        return chapter;
    }
    public boolean setChapter(Chapter chapter) {
        if (chapter == null) return false;
        this.chapter = chapter;
        return true;
    }
    public String info() {
        return("Id : " + id + "\n" +
                "Name : " + name + "\n" +
                "Coordinates x ¸ y : " + coordinates.getXCoordinate() + ", " + coordinates.getYCoordinate() + "\n" +
                "Creation date : " + getCreationDate() + "\n" +
                "Health : " + health + "\n" +
                "Height : " + height+ "\n" +
                "Astartes Category : " + category + "\n" +
                "Melee Weapon : " + meleeWeapon + "\n" +
                "Chapter name : " + chapter.getName() + "\n" +
                "Chapter Parent Legion : " + chapter.getParentLegion() + "\n" +
                "Marines Count : " + chapter.getMarinesCount() + "\n" +
                "Chapter World : " + chapter.getWorld() + "\n" +
                "----------------- \n" );
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return (int) (health - o.getHealth());
    }

}
