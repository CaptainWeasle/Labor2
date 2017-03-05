import java.util.Scanner;

public class TestgameMain{

	public static void main(String args[]){


		Scanner peter = new Scanner (System.in);

		System.out.println("Bitte geben Sie Ihren Namen ein.");
		Testgame spiel = new Testgame(peter.next());

		spiel.loadRoom1();




	}
}