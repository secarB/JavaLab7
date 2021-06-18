package braces.client.utils;

public class ClientInputChecker {
	private  static final double eps = 1E-6;

    /**
     * Check the integer value
     * @param s value to check
     * @param min min
     * @param max max
     * @return true/false if value is valid
     */
    public boolean integerValidCheck(String s, int min, int max){
        try{
            int x = Integer.parseInt(s);
            if(x >= min && x <= max) return true;
            System.out.println("Input is invalid. Please enter the integer in correct range");
            return false;
        } catch(NumberFormatException e){
            System.out.println("Input is invalid. Please enter a integer number");
            return false;
        }
    }
    /**
     * Check the long value
     * @param s value to check
     * @param min min
     * @param max max
     * @return true/false if value is valid
     */
    public boolean longValidCheck(String s, Long min, Long max){
        try{
            long x = Long.parseLong(s);
            if(x >= min && x <= max) return true;
            System.out.println("Input is invalid. Please enter the long number in correct range");
            return false;
        } catch(NumberFormatException e){
            System.out.println("Input is invalid. Please enter a long number");
            return false;
        }
    }
}
