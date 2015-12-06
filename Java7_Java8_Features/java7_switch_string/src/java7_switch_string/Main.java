package java7_switch_string;

public class Main {
	
	public static void main(String[] args) {
		for (int i=0; i<args.length; i++) {			
			switchString(args[i]);
		}
	}

	private static void switchString(String string) {
		/*
		 * Strings in switch Statements - 
		 * You can use the String class in the expression of a switch statement.
		 */
		switch (string) {
		case "1":
			System.out.println("case 1");
			break;
		default:
			System.out.println("default case");
			break;
		}
	}
}
