import java.util.Scanner; //Code verwendet von http://www.coderslexicon.com/a-beginner-tic-tac-toe-class-for-java/
import java.util.Random;

public class Testgame{
	
//INITIALISIERUNG

	Scanner peter = new Scanner(System.in);
	Random random = new Random();


//INSTANZVARIABLEN

	String playerName, lastAction, resultRockPaperScissor, resultTicTacToe;

	int playerHp, annoyedValue, annoyedDamage, damage, szenario, streichholzzahl, gameResult;

	boolean riverRiddle, stäbchenSpielResult, resultMathGame, ticTacToeWin;

	char[][] board;  

	char currentPlayerMark;

//KONSTRUKTOR

	Testgame (String playerName){

		this.playerName = playerName;
		playerHp = 100;
		lastAction = "Aufgewacht...";
		annoyedValue = 0;
		szenario = 0; //für die Räume
		streichholzzahl = 17;//für das streichholzsspiel
		board = new char[3][3];//für ticTacToe
		currentPlayerMark = 'x';
		setBoard();

	}

//METHODEN

	//
	public void loadNothing(){

	}

	//Status anzeigen lassen
	public void loadStatus(){	
		System.out.println("\nSTATUS:");
		System.out.println("Name: " + playerName);
		System.out.println("Leben: " + playerHp);
		System.out.println("Letzte Aktion: " + lastAction + "\n");
	}
	//Anfangswerte werden gesetzt.
	public void setIdle(){
		playerHp = 100;
		lastAction = "Aufgewacht...";
		annoyedValue = 0;
		szenario = 0;
		streichholzzahl = 17;
		setBoard();
		gameResult = 0;
	}
	//wird warscheinlich nur einmal verwendet
	public void introduction(){
		System.out.println("***********************************************");
		System.out.println("Du wachst auf.\nDer Raum ist gut beleuchtet und sieht vornehm aus.\nDer Raum ist leer.\nDu erinnerst dich an nichts.\nDu hast nichts...");
		System.out.println("***********************************************");
	}

	//annoying value wird um 1 hoch gesetzt und der Schaden dementsprechend angepasst
	public void annoyed(){
		annoyedValue++;
		annoyedDamage = 10 * annoyedValue;
	}

	//Schaden wird übertragen
	public void damageCalculation(int damage){
		this.damage = damage;
		playerHp = playerHp - damage;
		lethalityCheck();
	}

	//Es wird geguckt, ob der Spieler noch am Leben ist
	public boolean hpCheck(){
		if(playerHp <= 0){
			return true;
		}else{
			return false;
		} 
	}

	//falls der Spieler nicht mehr lebt, wird der GameOver Screen geladen
	public void lethalityCheck(){
		if(hpCheck()){
			loadGameOver();
		}
	}

	//Nur zur Übersichtlichkeit am Ende jedes Raumes angezeigt
	public void endRoom(){
		System.out.println("***********************************************");
	}
//___________________________________________________________________________________________________

	//Setzt am Ende alles nochmal in den Startzustand, damit nicht zufällig noch was anderes geladen wird
	public void spielVorbei(){
	setIdle();
	}



//Spiel zu Ende
	public void loadGameOver(){
		loadStatus();
		boolean nichtGeantwortet = true;
		System.out.println("Du hast leider das Spiel verloren. Möchtest du es neu starten?");
		String playerAnswer = peter.next();
		while(nichtGeantwortet){
			if(playerAnswer.equalsIgnoreCase("ja")){
				endRoom();
				setIdle();
				nichtGeantwortet = false;
				loadRoom1();
			}else if(playerAnswer.equalsIgnoreCase("nein")){
				System.out.println("Dann wird das Spiel jetzt beendet. Danke fürs Spielen.");
				nichtGeantwortet = false;
				spielVorbei();
				System.exit(0);
				break;
			}else{
			System.out.println("Bitte neu antworten.");
			}
		}
		
	}

//____________________________________________________________________________________________________________

	//Array mit "-" füllen
	public void setBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    //Spielbrett anzeigen lassen
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

 	//checkn, ob das Spielfeld voll ist, wegen Unentschieden
 	public boolean isBoardFull() {
        boolean isFull = true;
        	for (int i = 0; i < 3; i++) {
            	for (int j = 0; j < 3; j++) {
                	if (board[i][j] == '-') {
                     	isFull = false;
                 	}
            	}
        	}
 		return isFull;
 	}

    //guckt, ob drei werte die selben sind (für das folgende)
    public boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    //spalten nach einem win checken
    private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    //durch die reihen gehn und gucken ob wer gewonnen hat
    public boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    //diagonale nach win überprüfen
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }
	
	//gucken, ob irgendwer gewonnen hat
 	public boolean checkForWin() {
 		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
 
    }

    //Spieler wechseln
    public void changePlayer() {
        if (currentPlayerMark == 'x') {
        	currentPlayerMark = 'o';
        }
        else {
            currentPlayerMark = 'x';
        }
    }

    //eingabe
    public boolean placeMark(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }
        return false;
    }

    //TicTacToe
    public void loadTicTacToe(){
    	boolean läuft = true;
    	lastAction = "Den linken Weg genommen.";
    	loadStatus();
    	System.out.println("\t\"Heyho! Du hast es ziemlich weit geschafft. Hier wird dich ein letztes Spiel erwarten. Pass auf, denn wir spielen jetzt TicTacToe. Gleich wird ein Spielbrett erscheinen und wir werden abwechselnd unsere Markierungen setzten. Du musst dann Koordinaten wie zum Beispiel \"0\" + \"Enter\" + \"1\" + \"Enter\" eingeben. Dabei bestimmt die erste Zahl die Reihe und die zweite Zahl die Spalte. Es ist tatsächlich etwas trickey, weil die Matrix von 0 bis 2 geht. Also gibt es eine 0te Reihe, welche bis zur zweiten geht. Aber eigentlich ja ganz easy. Wenn du jedoch versuchst ein schon besetztes Feld zu nennen, dann mach ich einfach weiter und überspringe deinen Zug. Du darfst anfangen. Viel Glück.");
    	while(läuft){
    		printBoard();
    		System.out.println("Welche Stelle möchtest du markieren?");
    		placeMark(peter.nextInt(), peter.nextInt());
    		if(checkForWin() == true){
    			gameResult = 1;
    			printBoard();
    			System.out.println("Du hast gewonnen");
    			läuft = false;
    			break;
    		}else if(isBoardFull() == true){
    			System.out.println("Unentschieden.");
    			printBoard();
    			gameResult = 2;
    			läuft = false;
    			break;
    		}

    		printBoard();
    		changePlayer();
    		boolean falscheAntwort = true;
    		while(falscheAntwort){
    			
    			int ghostAnswer = random.nextInt(3);
    			int ghostAnswer2 = random.nextInt(3);
    			if(board[ghostAnswer][ghostAnswer2] == '-'){
    				System.out.println("\t\"Ich bin am Zug!\"");
    				placeMark(ghostAnswer, ghostAnswer2);
    				if(checkForWin() == true){
    					gameResult = 3;
    					printBoard();
    					System.out.println("Du hast verloren.");
    					läuft = false;
    					break;
    				}else if(isBoardFull() == true){
    					gameResult = 2;
    					System.out.println("Unentschieden.");
    					läuft = false;
    					break;
    				}
    				falscheAntwort = false;
    				break;
    			}
    		}
    		
    		changePlayer();
		}
		if(gameResult == 1){
			System.out.println("\t\"Du hast wohl gewonnen...Na gut. Ich werde dich weiter lassen.\"");
			resultTicTacToe = " gewonnen.";
			szenario = 14;
			endRoom();
			loadRoom11();
		}else if(gameResult == 2){
			System.out.println("\t\"Hmmm...Unendschieden...Gefällt mir nicht. Na gut. Ich werde dich weiter lassen.\"");
			resultTicTacToe = " unendschieden gespielt.";
			endRoom();
			loadRoom11();
		}else if(gameResult == 3){
			System.out.println("\t\"Hahaha, ich habe gewonnen. Bin halt der Beste. Naja, ich werde dich wohl trotzdem weiter lassen.\"");
			damageCalculation(36);
			resultTicTacToe = " verloren.";
			endRoom();
			loadRoom11();
		}
	}

//____________________________________________________________________________________________________________

	//elfter Raum
	public void loadRoom11(){
		if(szenario == 13){
			lastAction = "Schere Stein Papier Spiel gegen den Geist" + resultRockPaperScissor;
			loadStatus();
			spielVorbei(); 
		}else if(szenario == 14){
			lastAction = "Tic Tac Toe gegen den Geist" + resultTicTacToe;
			loadStatus();
			spielVorbei();
		}

		System.out.println("Der Geist erscheint vor dir. Es gibt keine Tür dieses Mal.\n\t\"Wie es scheint, hast du es bis zum Ende geschafft. Gratulation! Ich habe dir zwar nichts versprochen, aber ich denke ich werde dich befreien. Danke für die schöne Zeit, es ist eine Schande.\"\nDu wirst ohnmächtig...\n\nENDE");
		System.exit(0);
	}

//____________________________________________________________________________________________________________
//neunter Raum
	public void loadRoom9(){
		if (szenario == 8){
			lastAction = "Die rechte Tür gewählt.";
			loadStatus();
			szenario = 10;
		}else if(szenario == 9){
			lastAction = "Die rechte Tür gewählt.";
			loadStatus();
			szenario = 11;
		}

		System.out.println("Dieser Raum ist giftig, du spürst Schmerzen in deine Brust. Es gibt keine weiteren Türen, es ist eine Sackgasse. Du verlässt sofort den Raum.");

		damageCalculation(50);

		if (szenario == 10){
			endRoom();
			loadRoom8();
		}else if(szenario == 11){
			endRoom();
			loadRoom6();
		}
	}

//________________________________________________________________________________________________________________

//zehnter Raum
	public void loadRoom10(){
		lastAction = "Den linken Raum gewählt.";
		loadStatus();
		System.out.println("Dieser Raum ist giftig, du spürst Schmerzen in deine Brust. Es gibt keine weiteren Türen, es ist eine Sackgasse. Du verlässt sofort den Raum.");
		damageCalculation(50);
		szenario = 12;
		loadRoom7();
	}

//Das Flussrätsel
	public void loadRiverRiddle(){
		lastAction = "Durch die einzige Tür gegangen.";
		loadStatus();

		System.out.println("\t\"Hier kommt nun das Rätsel:\" Ein Bauer muss einen Fluss überqueren. Er hat einen Kohlkopf, ein Schaf und einen Wolf dabei und möchte alle drei sicher auf die andere Seite des Flusses transportieren. Um dies zu ermöglichen hat er ein Boot. Dieses Boot ist leider nicht so groß, das heißt, dass er immer nur einen mitnehmen kann (Kohlkopf, Schaf oder Wolf). Ein weiteres Problem ist, dass wenn der Bauer den Kohkopf und das Schaf alleine lässt, der Kohlkopf aufgegessen wird und genauso wird das Schaf aufgegessen, wenn es sich alleine mit dem Wolf befindet. Wie muss der Bauer nun vorgehen, wenn er alle sicher rüberbringen möchte\"\nIch werde dich gleich eine Zahlenfolge abfragen. Dabei steht die \"1\" für den Kohlkopf, die \"2\" für das Schaf und dementsprechend steht die \"3\" für den Wolf. Der Akt des Transportes wird als eine Zahl aufgeschrieben, je nachdem was transportiert wird. Als Beispiel für eine Eingabe wäre \"1321\". Dies würde dann bedeuten, dass der Bauer zuerst den Kohlkopf rüberbringt, dann zurückrudert, den Wolf mitnimmt, wieder zurückrudert, das Schaf mitbringt, und dann zu Schluss noch den Kohlkopf auf die andere Seite zurückbringt. Natürlich macht das keinen Sinn, denn nach dem ersten Herüberbringen wäre das Schaf gefressen und am Ende würde man den Kohlkopf wieder zurück zum Anfang bringen. Aber als Beispiel sollte es reichen. Als Antwort verlange ich die kleinstmögliche Lösung, mit so wenig Transporten wie möglich. Also falls man sowas wie 11 scheibt, was bedeutet, dass man den Kohlkopf hin und zurückbringt wäre die Lösung möglicherweise trotzdem richtig, sie wird aber nicht akzeptiert. Bitte gib jetzt deine Antwort ein:");
		int playerAnswer = peter.nextInt();
		if (playerAnswer == 23212){
			szenario = 3;
			System.out.println("\"DAS KANN NICHT SEIN.\"");
			endRoom();
			loadRoom7();
		}else{
			szenario = 4;
			damageCalculation(25);
			System.out.println("\"Hahahahahahahaha, Bestraaaaaafung, hehe\"");
			endRoom();
			loadRoom6();
		}
	}

//achter Raum
	public void loadRoom8(){
		if(szenario != 10){
			if(stäbchenSpielResult){
				lastAction = "Du hast das Stäbchenspiel gegen den Geist gewonnen.";
				loadStatus();
				System.out.println("\t\"Hmmpf, du hast nur Glück gehabt.");
			}else{
				lastAction = "Du hast das Stäbchenspiel gegen den Geist verloren.";
				loadStatus();
				System.out.println("\t\"Hahahaha, das war doch einfach.");
			}	

			System.out.println("Naja, egal. Du hast es tatsächlich ziemlich weit geschafft, das muss ich zugeben. Hier gebe ich dir eine letzte Entscheidung. Du siehst zwei Wege. Wählst du die linke Tür oder die rechte Tür?\"");

			boolean nichtGeantwortet = true;

			while(nichtGeantwortet){
				String playerAnswer = peter.next();

				if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
					nichtGeantwortet = false;
					System.out.println("Nächster Raum wird geladen.");
					endRoom();
					loadTicTacToe();
				}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
					nichtGeantwortet = false;
					szenario = 8;
					System.out.println("Nächster Raum wird geladen.");
					endRoom();
					loadRoom9();
				}else{
					System.out.println("\".........HAT DIR DEINE MUTTER KEIN RESPEKT BEIGEBRACHT? Hmmpf, hmmpf, was wolltest du noch gleich sagen?\"");
					annoyed();
					damageCalculation(annoyedDamage);
				}
			}
		}else if(szenario == 10){
			lastAction = "Du hast den giftigen Raum überlebt und zum vorherigen Raum zurückgekehrt.";
			loadStatus();
			System.out.println("Es gibt nur noch die linke Tür, durch welche du gehen kannst. Du gehst durch diese Tür.");
		}
			
	}

//Schere Stein Papier
	public void loadRockPaperScissors(){
		boolean spielLäuft = true;
		lastAction = "Die mittlere Tür gewählt.";
		loadStatus();
		System.out.println("\t\"Mit dieser Tür hat du tatsächlich Glück gehabt. Nun werden wir Schere Stein Papier gegeneinander spielen. Hier nochmal alles was du wissen musst: \"\nGleich wirst du dich entscheiden müssen, ob du Schere, Stein oder Papier wählen möchtest. Dabei steht die \"1\" für Schere, die \"2\" für Stein und die \"3\" für Papier. Dein Gegner wird sich auch für eines dieser drei Optionen entscheiden. Dann kommt die Auflösung. Stein schlägt Schere, Schere schlägt Papier und Papier schlägt Stein. Nun, wähle bitte jetzt die 1, 2 oder die 3, je nachdem was du wählst.");
		
		while(spielLäuft){
			String playerAnswerString;
			String ghostAnswerString;

			int playerAnswer = peter.nextInt();
			int ghostAnswer = random.nextInt(3)+1;	//1 = Schere  //2 = Stein //3 = Papier

			if(playerAnswer == 1){
				playerAnswerString = "Schere";
			}else if(playerAnswer == 2){
				playerAnswerString = "Stein";
			}else if(playerAnswer == 3){
				playerAnswerString = "Papier";
			}else{
				playerAnswerString = "nichts";
			}

			if(ghostAnswer == 1){
				ghostAnswerString = "Schere";
			}else if(ghostAnswer == 2){
				ghostAnswerString = "Stein";
			}else if(ghostAnswer == 3){
				ghostAnswerString = "Papier";
			}else{
				ghostAnswerString = "nichts";
			}

			System.out.println("Du hast also " + playerAnswerString + " gewählt. Der Geist hat " + ghostAnswerString + " gewählt.");
			//szenario Spieler gewinnt
			if ((playerAnswer == 1 && ghostAnswer == 3) || (playerAnswer == 2 && ghostAnswer == 1) || (playerAnswer == 3 && ghostAnswer == 2)){
				spielLäuft = false;
				resultRockPaperScissor = " gewonnen.";
				System.out.println("Du gewinnst dieses Spiel und darfst mit Stolz in den nächsten Raum weiter. \n\t\"Hmpf...\"");
				szenario = 13;
				endRoom();
				loadRoom11();
			}else if(playerAnswer == ghostAnswer){//szenario unentschieden
				System.out.println("Es ist ein Unendschieden. Bitte gebe deine Eingabe erneut ein.");
			}else{//szenario verloren
				spielLäuft = false;
				resultRockPaperScissor = " verloren.";
				szenario = 13;
				System.out.println("Du verlierst dieses Spiel");
				damageCalculation(35);
				System.out.println("\"\tDu hast verloren, wer hätte es nur ahnen können. Ich bin natürlich unschlagbar. Nun werde ich dich bestarfen, jedoch kann ich dir den weiteren Pfad nicht verbieten...Das ist nur zu schade. Naja, wir sehen uns auf der anderen Seite.\"");
				endRoom();
				loadRoom11();
			}
		}
		
	}


//________________________________________________________________________________________________________________

//Stäbchenspiel
	public void loadStäbchenspiel(){

		stäbchenSpielResult = true;
		lastAction = "linke Tür gewählt";
		loadStatus();
		if(szenario == 5){
			System.out.println("\t\"Überraschung! Hier kommt ein Spiel. Ich hoffe du hast Spaß mitgebracht. Hier kommen die Regeln:\"\n\nDu wirst gleich eine Anzahl von Streichhölzern sehen. Es sind 17 Stück. Jeder von uns darf sich aussuchen, wie viele Streichhölzer er ziehen möchte. Man darf sich eins, zwei oder drei Streichhölzer aussuchen. Wer am Ende gezwungen ist, das letzte Streichholz zu ziehen, hat verloren. Viel Glück.\n\t\"Hähä, ich bin hier der Gastgeber, das heißt ich darf anfangen!!!\"");
		}else{
			System.out.println("\t\"Überraschung! Hier kommt mal wieder ein kleines Spielchen, hehe. Ich hoffe du hast Spaß mitgebracht. Hier kommen die Regeln:\"\n\nDu wirst gleich eine Anzahl von Streichhölzern sehen. Es sind 17 Stück. Jeder von uns darf sich aussuchen, wie viele Streichhölzer er ziehen möchte. Man darf sich eins, zwei oder drei Streichhölzer aussuchen. Wer am Ende gezwungen ist, das letzte Streichholz zu ziehen, hat verloren. Viel Glück.\n\t\"Hähä, ich bin hier der Gastgeber, das heißt ich darf anfangen!!!\"");
		}

		boolean läuft = true;

		while(läuft){
			System.out.println("Die Streichholzmenge beträgt " + streichholzzahl +". ");
			boolean invalidAnswer = true;
			boolean invalidAnswer2 = true;

				while(invalidAnswer2){
					int ghostsTurn = random.nextInt(3) + 1;
					if(ghostsTurn > 3 || ghostsTurn > streichholzzahl){
						//nichts
					}else{
						System.out.println("\t\"Ich nehme mir jetzt " + ghostsTurn + " Stück.");
						streichholzzahl = streichholzzahl - ghostsTurn;
						if(streichholzzahl == 0){
							invalidAnswer = false;
							stäbchenSpielResult = true;
							break;
						}else{
							stäbchenSpielResult = true;
							break;
						}
					}
				}

				if(streichholzzahl <= 0){
				stäbchenSpielResult = true;
				break;
			}

			System.out.println("Die Streichholzmenge beträgt " + streichholzzahl +". ");

				while(invalidAnswer){
						System.out.println("Wie viele Streichhölzer möchtest du jetzt entnehmen?");
						int playerAnswer = peter.nextInt();
					if(playerAnswer > 3 || playerAnswer > streichholzzahl || playerAnswer <= 0){
						System.out.println("Das geht nicht. So viele kannst du nicht nehmen. Du kriegst kurze Kopfschmerzen. Versuche es erneut.");
						annoyed();
						damageCalculation(16);
					}else{
						streichholzzahl = streichholzzahl - playerAnswer;
						invalidAnswer = false;
					}
				}
			
			if(streichholzzahl <= 0){
				stäbchenSpielResult = false;
				break;
			}
		}


		if(stäbchenSpielResult == true){
			System.out.println("\n\t\"Hmmpf, weiter gehts...\"");
			endRoom();
			loadRoom8();
		}else if(stäbchenSpielResult == false){
			System.out.println("\n\t\"Tja, bin ich wohl besser. Einmal Bestrafung also.\"");
			damageCalculation(25);
			endRoom();
			loadRoom8();
		}
	}

//siebter Raum
	public void loadRoom7(){

		if(szenario == 12){
			System.out.println("\t\"Wie es scheint, hast du den giftigen Raum gewählt gehabt, haha. Ich schätze, du möchtest nun nicht mehr durch die linke Tür. Dann hast du jetzt wohl die Wahl zwischen der mittleren und der rechten Tür. Welche ist es?\"");
			String playerAnswer = peter.next();
			if(playerAnswer.equalsIgnoreCase("mitte") || playerAnswer.equalsIgnoreCase("mittlere")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRockPaperScissors();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				System.out.println("Nächster Raum wird geladen.");
				szenario = 6;
				endRoom();
				loadRoom6();
			}else{
				System.out.println("\t\"Wenn du nicht aussuchen willst, mach' ich das für dich. Ich habe die mittlere Tür versiegelt. Jetzt kannst du nur nch durch die rechte. Viel Spaß.\"\nDu gehst durch die rechte Tür.");
				System.out.println("Nächster Raum wird geladen.");
				szenario = 6;
				annoyed();
				damageCalculation(annoyedDamage);
				endRoom();
				loadRoom6();
			}
		}else if(szenario == 1){
			System.out.println("\t\"Hier gebe ich dir die Wahl zwischen drei Türen, wie du vielleicht sehen kannst. Du kannst dich entscheiden zwischen der linken, der mittleren und der rechten Tür. Was wählst du?\"");
			String playerAnswer = peter.next();
			if(playerAnswer.equalsIgnoreCase("mitte") || playerAnswer.equalsIgnoreCase("mittlere")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRockPaperScissors();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				System.out.println("Nächster Raum wird geladen.");
				szenario = 6;
				endRoom();
				loadRoom6();
			}else if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom10();
			}else{
				System.out.println("\t\"Dann werde ich dich in den linken Raum werfen, wenn du mir so kommst...Viel Spaß da drüben, wir sehn uns gleich wieder, hehe.\"\nDu kannst dich nicht bewegen und schwebst durch die linke Tür hindurch...");
				szenario = 12;
				annoyed();
				endRoom();
				loadRoom10();
			}
		}else if(szenario == 7){
			System.out.println("\t\"Hier gebe ich dir die Wahl zwischen drei Türen, wie du vielleicht sehen kannst. Du kannst dich entscheiden zwischen der linken, der mittleren und der rechten Tür. Was wählst du?\"");
			String playerAnswer = peter.next();
			if(playerAnswer.equalsIgnoreCase("mitte") || playerAnswer.equalsIgnoreCase("mittlere")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRockPaperScissors();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				System.out.println("Nächster Raum wird geladen.");
				szenario = 6;
				endRoom();
				loadRoom6();
			}else if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom10();
			}else{
				System.out.println("\t\"Dann werde ich dich in den linken Raum werfen, wenn du mir so kommst...Viel Spaß da drüben, wir sehn uns gleich wieder, hehe.\"\nDu kannst dich nicht bewegen und schwebst durch die linke Tür hindurch...");
				szenario = 12;
				annoyed();
				endRoom();
				loadRoom10();
			}
		}else if(szenario == 2){
			System.out.println("\t\"Bis hierhin hast du ziemlich viel Glück gehabt, mein Freund. Aber das wird sich bald ändern...Naja, egal. Hier gebe ich dir die Wahl zwischen drei Türen, wie du vielleicht sehen kannst. Du kannst dich entscheiden zwischen der linken, der mittleren und der rechten Tür. Was wählst du?\"");
			String playerAnswer = peter.next();
			if(playerAnswer.equalsIgnoreCase("mitte") || playerAnswer.equalsIgnoreCase("mittlere")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRockPaperScissors();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				System.out.println("Nächster Raum wird geladen.");
				szenario = 6;
				endRoom();
				loadRoom6();
			}else if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom10();
			}else{
				System.out.println("\t\"Dann werde ich dich in den linken Raum werfen, wenn du mir so kommst...Viel Spaß da drüben, wir sehn uns gleich wieder, hehe.\"\nDu kannst dich nicht bewegen und schwebst durch die linke Tür hindurch...");
				szenario = 12;
				annoyed();
				endRoom();
				loadRoom10();
			}
		}else if(szenario == 3){
			System.out.println("\t\"Scheint als hättest du nur Glück gehabt...Naja, egal. Hier gebe ich dir die Wahl zwischen drei Türen, wie du vielleicht sehen kannst. Du kannst dich entscheiden zwischen der linken, der mittleren und der rechten Tür. Was wählst du?\"");
			String playerAnswer = peter.next();
			if(playerAnswer.equalsIgnoreCase("mitte") || playerAnswer.equalsIgnoreCase("mittlere")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRockPaperScissors();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				System.out.println("Nächster Raum wird geladen.");
				szenario = 6;
				endRoom();
				loadRoom6();
			}else if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom10();
			}else{
				System.out.println("\t\"Dann werde ich dich in den linken Raum werfen, wenn du mir so kommst...Viel Spaß da drüben, wir sehn uns gleich wieder, hehe.\"\nDu kannst dich nicht bewegen und schwebst durch die linke Tür hindurch...");
				szenario = 12;
				annoyed();
				endRoom();
				loadRoom10();
			}
		}else{
			System.out.println("Wooow, es wurde ein Fehler gefunden! Herzlichen Glückwunsch dafür. Dann ist das Spiel vorserst hier vorbei. SRY.");
		}
	}

//vierter Raum
	public void loadRoom4(){
		if (resultMathGame){
			lastAction = "Das Mathe Rästel gespielt und gewonnen.";
		}else{
			lastAction = "Das Mathe Rästel gespielt und verloren.";
		}

		loadStatus();

		if (resultMathGame == true){
			System.out.println("\n\t\"Nun, es scheint du bist ziemlich talentiert...Doch wie du sehen kannst, gibt es nur eine einzige Tür und du denkst doch wohl nicht, ich lasse dich da einfach so unbeschadet durch? Haha, ich kenne da so ein Rästel, nicht einmal ICH habe es lösen können. Falls du es schaffst, werd' ich fair sein und dich hier rauslassen, falls du es nicht schaffst (ich würde jetzt gerne sagen, du wüsstest schon was passiert...), wirst du bestraft.\"");
		}else{
			System.out.println("\n\t\"Tja, scheint als wärst du meinen Aufgaben kaum gewachsen zu sein, hehe. wie du sehen kannst, gibt es nur eine einzige Tür und du denkst doch wohl nicht, ich lasse dich da einfach so unbeschadet durch? Haha, ich kenne da so ein Rästel, nicht einmal ICH habe es lösen können. Falls du es schaffst, werd ich fair sein und dich hier rauslassen, falls du es nicht schaffst (ich würde jetzt gerne sagen, du wüsstest schon was passiert...), wirst du bestraft.\"");
		}

		System.out.println("\t\"Bist du bereit, fortzufahren?\"");
		
		String playerAnswer = peter.next();
		
		if(playerAnswer.equalsIgnoreCase("ja")){
			
			System.out.println("\t\"So sei es...\"");
			endRoom();
			loadRiverRiddle();
		}else if(playerAnswer.equalsIgnoreCase("nein")){
			System.out.println("Pech gehabt, du kommst jetzt trotzdem in den neuen Raum.");
			endRoom();
			loadRiverRiddle();
		}else{
			System.out.println("\".........HAT DIR DEINE MUTTER KEIN RESPEKT BEIGEBRACHT? Hmmpf, hmmpf, was wolltest du noch gleich sagen?\"");
			annoyed();
			damageCalculation(annoyedDamage);
			loadRiverRiddle();
		}
	}

//_____________________________________________________________________________________________________________

	public void loadRiddle(){

		lastAction = "Die linke Tür gewählt und vom Geist überfallen.";
		loadStatus();
		System.out.println("Plötzlich erscheint der Geist vor dir. Er scheint außer Atem zu sein.\n\t\"Haaaaab' ich dich erwischt...Scheint so, als wärst du die andere Route gegangen...Naja, auch egal. Ich habe trotzdem ein nettes Spielchen für dich. Es ist ein Rätsel (welches ich aus einem sehr guten und bekannten Film habe...). Das Rätsel geht wie folgt: \n\"Zweiunddreißg Schimmel auf einem roten Hang - erst malmen sie, dann stampfen sie und warten wieder lang.\" \n\t\"Das war es jetzt. Na? Weißt du die Antwort? Es sind -?\"");
		String playerAnswer = peter.next();
		if(playerAnswer.equalsIgnoreCase("zähne")){
			szenario = 1;
			System.out.println("\t\"Hmmpf, es scheint so, als hättest du den selben Film gesehen, wie ich. Naja, da habe ich wohl Pech gehabt. Du darfst passieren...\"");
			endRoom();
			loadRoom7();
		}else{
			szenario = 7;
			System.out.println("\t\"Scheint als wäre es zu schwer für dich. Jetzt werde ich dich ein bisschen bestrafen und dann kann es auch schon weitergehen.\"\nDu spürst kurzzeitig Schmerzen in deinem Kopf, die so plötzlich verschwinden wie sie kamen.");
			damageCalculation(25);
			endRoom();
			loadRoom7();
		}

	}

//_____________________________________________________________________________________________________________

//Das Mathe Rätsel
	public void loadMathGame(){
		lastAction = "Die rechte Tür gewählt.";
		loadStatus();
		System.out.println("\t\"Hahaha, wie es scheint hast du leider die falsche Tür gewählt. Hier werde ich dir ein Rätsel stellen. Falls du es schaffst, kommst du unbeschadet weiter. Falls du es nicht schaffst, werde ich dich bestrafen, hehe.\nHier kommt die Beschreibung:\" \n Man wird dir eine mathematische Zahlenfolge nennen. Es sind 5 Zahlen. Ich fordere jedoch die sechste Zahl, welche logisch folgen müsste. Ein Beispiel wäre die Zahlenfolge |1|2|4|8|16| und in diesem Beispiel wäre die sechste und gesuchte Zahl 32. Nun Kommt deine Zahlenfolge:\n\n\t\t|1|2|6|30|210|X|\n\nDu hast nur einen einzigen Versuch. Bitte schreibe nun deine Lösung:\"");
		int playerAnswer = peter.nextInt();
		if (playerAnswer == 2310){
			System.out.println("Die Antwort ist richtig.\n\t\"Herzlichen Glückwunsch...natürlich war das nur Glück. Ich lasse dich dann wohl unbeschadet in den nächsten Raum (Zum Glück wird der nicht viel besser, hehe\"");
			resultMathGame = true;
			endRoom();
			loadRoom4();
		}else{
			System.out.println("Die Antwort ist falsch.\n\t\"Hahahahaha, ich wusste, dass du es nicht schaffst. Nun lass mich dich bestrafen und dich in den wundervollen nächsten Raum begleiten.\"");
			damageCalculation(35);
			resultMathGame = false;
			endRoom();
			loadRoom4();
		}
	}

//fünfter Raum
	public void loadRoom5(){
		boolean nichtGeantwortet = true;

		lastAction = "Die linke Tür gewählt...";

		loadStatus();

		System.out.println("Der Raum ist leer. Du hast wohl die richtige Entscheidung getroffen mit der linken Tür. Es ist nichts passiert, du siehst jedoch zwei weitere Türen. Welche möchtest du nehmen?");

		while(nichtGeantwortet){
			String playerAnswer = peter.next();

			if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				nichtGeantwortet = false;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRiddle();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				nichtGeantwortet = false;
				szenario = 2;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom7();
			}else{
				System.out.println("Bitte noch einmal sagen...");
			}
		}
	}

//sechster Raum
	public void loadRoom6(){

		boolean nichtGeantwortet = true;

		if(szenario == 4){
			lastAction = "Du hast das Rästel des Geistes nicht geschafft.";
			loadStatus();
			System.out.println("\t\"So sieht man sich wieder. Wie fandest du mein Rästel? Ziemlich schwer, nicht? Ich hoffe natürlich auch, dass ich dich nicht zu sehr bestraft habe (hehehe). Wie auch immer, hier kommt deine nächste Prüfung. Wie du sehen kannst sind zwei Türen vor dir und in einer der Türen werde ich sein, mit einem kleinen Spielchen. Tatsächlich hoffe ich für dich, dass du mich findest, glaub mir, es wird für uns beide witziger...Also, für welche Tür entscheidest du dich? Die linke oder die rechte Tür?\"");
		}else if(szenario == 5){
			lastAction = "Durch den giftigen Raum gegangen...";
			loadStatus();
			System.out.println("Es erscheint plötzlich eine Gestalt. Es ist ein schwebender Mann im Anzug. Er ist etwas durchsichtig, es sieht aus, als wäre es ein Geist. Er fängt an zu sprechen.\n\t\"Muahahahahaha, so sieht man sich wieder. Ich hoffe, dir war es der leichte Weg wert. Hier gebe ich dir wieder die Entscheidnung. Wie du sehen kannst sind zwei Türen vor dir und in einer der Türen werde ich sein, mit einem kleinen Spielchen. Tatsächlich hoffe ich für dich, dass du mich findest, glaub mir, es wird für uns beide witziger...Also, für welche Tür entscheidest du dich? Links oder rechts?\"");
		}else if(szenario == 6){
			lastAction = "Du hast dich für die rechte Tür entschieden.";
			loadStatus();
			System.out.println("\t\"Joa, hast dich wohl für einen Raum mehr entschieden.Hier gebe ich dir wieder die Entscheidnung. Wie du sehen kannst sind zwei Türen vor dir und in einer der Türen werde ich sein, mit einem kleinen Spielchen. Tatsächlich hoffe ich für dich, dass du mich findest, glaub mir, es wird für uns beide witziger...Also, für welche Tür entscheidest du dich? Links oder rechts?\"");
		}else if(szenario == 11){
			lastAction = "Du hast den giftigen Raum sofort verlassen.";
			loadStatus();
			System.out.println("\t\"Jetzt gibt es wohl nur noch eine einzige Wahl für dich. Und das ist die linke Tür. Viel Spaß!\"\nDu betrittst die linke Tür.");
			nichtGeantwortet = false;
			endRoom();
			loadStäbchenspiel();
		}

		while(nichtGeantwortet){
			String playerAnswer = peter.next();

			if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				nichtGeantwortet = false;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadStäbchenspiel();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				nichtGeantwortet = false;
				szenario = 9;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom9();
			}else{
				System.out.println("\".........HAT DIR DEINE MUTTER KEIN RESPEKT BEIGEBRACHT? Hmmpf, hmmpf, was wolltest du noch gleich sagen?\"");
				annoyed();
				damageCalculation(annoyedDamage);
			}
		}
	}

//_________________________________________________________________________________________________
//zweiter Raum
	public void loadRoom2(){
		szenario = 5;
		lastAction = "Die rechte Tür gewählt.";
		damageCalculation(50);
		loadStatus();
		System.out.println("Du kommst in den Raum rein, doch du spürst etwas...etwas unangenehmes...\nPlötzlich fängt es an in deiner Brust wehzutun...Der Raum ist anscheinend vergiftet!\nEs gibt nur eine Tür. Du gehst so schnell du kannst durch sie.\n...Kurz bevor du den Raum verlässt, kann man eine Stimme ganz leise lachen hören...");
		endRoom();
		loadRoom6();
	}

//dritter Raum
	public void loadRoom3(){
		boolean nichtGeantwortet = true;

		lastAction = "Die linke Tür gewählt.";
		loadStatus();

		System.out.println("Du schaust dich um. Nach wenigen Sekunden erscheint ein Geist vor dir. Er ist ein Mensch, gekleidet im Anzug.\n\t\"Wie es scheint, hast du wohl auf den leichten Weg verzichtet... Na schön, dann wird es wohl spannend... Ab hier entscheidest nur du, wie gut du es durchschaffst. Ich selbst nehme eine Tür und warte mit einer Aufgabe auf dich auf der anderen Seite, hehehe...\"\nDer Geist bleibt kurz still, dann dreht er sich um und geht durch die rechte Tür.\n(Hat er vergessen sich unsichtbar zu machen?)\nPlötzlich ertönt schon wieder die Stimme:\n\tNun, " + playerName + ", in welche Tür wünscht du zu gehen?");

		while(nichtGeantwortet){
			String playerAnswer = peter.next();

			if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				nichtGeantwortet = false;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom5();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				nichtGeantwortet = false;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadMathGame();
			}else{
				annoyed();
				System.out.println("\t\"WAS? Antworte mir mal bitte anständig, so eine Antwort akzeptiere ich nicht. Antworte jetzt lieber richtig!\"\nEtwas sticht in deiner Brust...");
				damageCalculation(annoyedDamage);
			}
		}

	}
//__________________________________________________________________________________________________		
//erster Raum
	public void loadRoom1(){
		boolean nichtGeantwortet = true;

		loadStatus();

		introduction();

		System.out.println("Eine Stimme fängt an zu sprechen: \n\t\"Schönen guten Tag.\"\nDu siehst nichts, es scheint unsichtbar zu sein...ein Geist?\n\t\"Wie du sehen kannst, " + playerName + ", bist du hier gefangen. Du hast jedoch die Chance zu entkommen. Hier beginnt der erste Schritt. Ich biete dir zwei Optionen...\"\nEs erscheinen plötzlich zwei Türen.\n\t\"Du musst dich für eine der Türen entscheiden. Es wird dein folgendes Schicksal mitbestimmen, wähle weise. Die linke Tür ist ein harter Weg, er wird dich testen und birgt viele Gefahren. Der rechte Pfad jedoch ist ein simpler. Was wählst du? Die linke, oder die rechte Tür?\"");

		//abfrage, in welchen Raum es weitergehen wird
		while(nichtGeantwortet){
			String playerAnswer = peter.next();

			if(playerAnswer.equalsIgnoreCase("links") || playerAnswer.equalsIgnoreCase("linke")){
				nichtGeantwortet = false;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom3();
			}else if(playerAnswer.equalsIgnoreCase("rechts") || playerAnswer.equalsIgnoreCase("rechte")){
				nichtGeantwortet = false;
				System.out.println("Nächster Raum wird geladen.");
				endRoom();
				loadRoom2();
			}else{
				annoyed();
				System.out.println("\t\"WAS? Antworte mir mal bitte anständig, so eine Antwort akzeptiere ich nicht. Antworte jetzt lieber richtig!\"\nEtwas sticht in deiner Brust...");
				damageCalculation(annoyedDamage);
			}
		}
		
	}




























}