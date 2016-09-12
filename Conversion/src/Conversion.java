import java.io.*;
import java.util.*;
public class Conversion {

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		String input = s.next();
		if (input.contains("f"))
		{
			Scanner toNum = new Scanner(input);
			float degrees = toNum.nextFloat();
			float newDeg = 5*(degrees);
		}
	}

}
