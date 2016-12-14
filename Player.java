public class Player{
	
	//Instanzvariablen

	protected String playerName;

	protected int points;

	
	//Konstruktor

	Player(String playerName){
		playerName = this.playerName;

		points = 0;
	}

	
	//Methoden

	public int getPoints(){
		return points;
	}

	public String getName(){
		return playerName;
	}

}