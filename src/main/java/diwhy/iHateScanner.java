package diwhy;
import java.util.Scanner;

/** https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html **/
/** https://elearning.algonquincollege.com/coursemat/woollar/Courses/Common/OOP-Java/Input/InputCode-Singleton.pdf **/
public class iHateScanner {
	private static Scanner input;
	public iHateScanner() {
		if ( input == null) {
			input = new Scanner(System.in);
		}
	}
	public Scanner getScanner() {
		return input;
	}
	public String getLine() {
		return input.nextLine();
	}
	public Double getDouble() {
		return input.nextDouble();
	}
}
