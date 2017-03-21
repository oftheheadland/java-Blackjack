package blackjackreckoning3;
import java.util.Scanner;

//Handles all user input using scanner. still not 100% sure of how java input works but this seems to work well.

public class Input {

	public static String getInput() {

		System.out.print(">> ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String response = in.nextLine();
		System.out.println("------------------------------");
		return response;
	}
}