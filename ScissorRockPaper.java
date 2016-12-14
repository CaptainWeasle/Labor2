import java.util.Random;
import java.util.Scanner


public class ScissorRockPaper{

	//initalisierung

	Scanner peter = new Scanner(System.in);
	Random rng = new Random();


	//Instanzvariablen

	protected int enemysChoice;

	protected int playersChoice;

	//Konstruktor

	ScissorRockPaper(){

	}

	//Methoden

	public void introduction(){
		System.out.println("Willkommen " + getName() + "!");
		System.out.println("Nun kommt das erste Spiel....	Schere, Stein, Papier!");
		System.out.println("Wenn du es schaffst, mich zu besiegen, werde ich dir einen Punkt geben und du wirst \n
								dein Abenteuer fortsetzen können.");
		System.out.println("Was möchtest du wählen? MUAHAHAHAHA")
	}


	public boolean startScissorRockPaper{

		int enemysChoice = rng.nextInt(3);

		String playersAnswer = rng.next();

			if(playersAnswer.equalsIgnoreCase("schere")){
				playersChoice = 0;
			}else if(playersAnswer.equalsIgnoreCase("stein")){
				playersChoice = 1;
			}else if(playersAnswer.equalsIgnoreCase("papier")){
				playersChoice = 2;
			}

		if(playersChoice == enemysChoice){
			System.out.println("WAS? Du hast tatsächlich das selbe gewählt wie ich?");
			System.out.println("Also hast du mich nicht schlagen können!");
			return false;
		}else if(playersChoice > enemysChoice )

	}



}