package braces.server.fields;

import java.io.Serializable;

/**
 * Class coordinates
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer x; //Can't be null
    private long y; //Max:316
    public Coordinates(int x ,long y)
    {
    	this.x = x;
    	this.y = y;
    }
    public Coordinates() {
		// TODO Auto-generated constructor stub
	}
	public Coordinates(long long1, double double1) {
		// TODO Auto-generated constructor stub
	}
	public boolean setXCoordinate(Integer x)
    {
        if (x == null) return false;
        this.x = x;
        return true;
    }
    public Integer getXCoordinate()
    {
        return x;
    }
    public boolean setYCoordinate(long y)
    {
        if (y > 316) return false;
        this.y = y;
        return true;
    }
    public long getYCoordinate()
    {
        return y;
    }
}
