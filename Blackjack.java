package blackjackreckoning3;

//Handles player turn + deal turn and the rules of the game

import java.util.*;

public class Blackjack
{
	//Initial setup
	ArrayList<Card> cards = new ArrayList<Card>();
	int wins; int losses;


	//creates a new deck each session, keeps the game going after a win/loss and prompts user what to do next. 
	//restarts program after game but still tracks wins/losses
	public void playGame() {
		Deck.setCards();
		while(true)
			startRound();
	}
	public void startRound() {
		//Reset hand values each round - small but important step that was giving me a lot of trouble
		int dealerMinValue = 0;
		int dealerMaxValue = 0;
		int playerMinValue = 0;
		int playerMaxValue = 0;
		System.out.println("Welcome to Blackjack by Andrew VanNess");

		//greet user and give 4 options
		System.out.println("Would you like to play? Type a number to begin.");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.println("3. Rules");
		System.out.println("4. Stats");
		System.out.println("");
		String response = Input.getInput(); //redirect to input class and wait for input

		//switches work just like other languages, very clean and simple.
		//using the response string from scanner determine what will happen
		switch(response) {
		case "1" :
			break;

		case "2" :
			System.out.println("");
			System.out.println("Ending program."); 
			endProgram();

		case "3" :
			System.out.println("");
			System.out.println("1 player only. Dealer wins on ties. Player wins on blackjack without a chance for dealer to get blackjack."); 
			System.out.println("Dealer stands on 17 or higher. No bets, but wins and losses are tracked.\n");
			startRound();

		case "4" :
			System.out.println("");
			System.out.println("Stats this session.");
			System.out.println("Wins: " + wins);
			System.out.println("Losses: " + losses + "\n");

			startRound();


			//basically an else statement, if 1,2,3,4 are not entered at menu screen this will automatically happen - restarts round essentially
		default :
			System.out.println("");
			System.out.println("Invalid input.\n");

			startRound();
		}

		//the bulk of game code, decides what will happen after game begins

		ArrayList<Card> dealerCards = new ArrayList<Card>();   // make array to hold dealers cards
		ArrayList<Card> playerCards = new ArrayList<Card>();   // players cards
		dealerCards.add(Deck.pickCard());                                                                  //Starts dealers first pick
		System.out.println("The dealer draws the " + dealerCards.get(0).toString());
		int[] dealerHandVal = Hand.getValues(dealerCards);
		dealerMinValue = dealerHandVal[0];
		dealerMaxValue = dealerHandVal[1];
		if(dealerMinValue == dealerMaxValue)
			System.out.println("The dealer has " + dealerMinValue);
		else
			System.out.println("The dealer has " + dealerMinValue + " or " + dealerMaxValue + " due to Ace.");         //End dealers first picks

		boolean roundOver = false;
		boolean playerTurnOver = false;
		//draws 2 cards
		playerCards.add(Deck.pickCard());
		playerCards.add(Deck.pickCard());
		//sets up an array with 2 values, [minhandvalue] [maxhandvalue]
		int[] playerHandVal = Hand.getValues(playerCards);
		playerMinValue = playerHandVal[0];
		playerMaxValue = playerHandVal[1];
		System.out.println("\nYou draw the " + playerCards.get(0).toString());
		System.out.println("You draw the " + playerCards.get(1).toString());
		if(playerMinValue == playerMaxValue)
			System.out.println("You have " + playerMinValue);
		else
			System.out.println("You have " + playerMinValue + " or " + playerMaxValue + " due to Ace.");
		if(playerMaxValue == 21) {
			System.out.println("You got blackjack!");
			wins++;
			System.out.println("Wins: " + wins);
			System.out.println("Losses: " + losses + "\n");

			roundOver = true;
			playerTurnOver = true;
		}
		String position = "";
		if(!playerTurnOver && !roundOver) {
			System.out.println("What will you do?");
			System.out.println("1. Hit");
			System.out.println("2. Stand");
			System.out.println("");
			position = Input.getInput();
		}	



		//handles players turn rules depending on input + game state
		while(!playerTurnOver && !roundOver)
		{
			//player chooses to hit, draws card from deck
			if(position.equals("1")) {  
				playerCards.add(Deck.pickCard());
				playerHandVal = Hand.getValues(playerCards);
				playerMinValue = playerHandVal[0];
				playerMaxValue = playerHandVal[1];
				System.out.println("You draw the " + playerCards.get(playerCards.size()-1).toString());
				//if an ace is in hand there will be 2 values, otherwise only 1
				if(playerMinValue == playerMaxValue)
					System.out.println("You have " + playerMinValue);
				else
					System.out.println("You have " + playerMinValue + " or " + playerMaxValue + " due to Ace.");
				//if hitting causes player to bust, game ends
				if(playerMaxValue > 21) {
					roundOver = true;
					playerTurnOver = true;
					System.out.println("You bust.");
					losses++;
					System.out.println("Wins: " + wins);
					System.out.println("Losses: " + losses + "\n");


				}

				if(playerMaxValue == 21) {
					roundOver = true;
					playerTurnOver = true;
					System.out.println("You got blackjack!");
					wins++;
					System.out.println("Wins: " + wins);
					System.out.println("Losses: " + losses + "\n");

				}
				//playerTurnOver = true;	
			}
			if(position.equalsIgnoreCase("2")) { //Stand
				System.out.println("You stand.");
				playerTurnOver = true;
			}

			if(!playerTurnOver) {
				System.out.println("What will you do?");
				System.out.println("1. Hit");
				System.out.println("2. Stand");
				System.out.println("");
				position = Input.getInput();
			}


		}

		//end of user turn

		//dealer's turn begins


		boolean dealerTurnOver = false;
		while(!dealerTurnOver && !roundOver) {
			//will loop back to here if dealerhandvalue is under 17
			//handvalue MUST be >=17 to trigger any of the events below. will continue to draw until he is over 16
			dealerCards.add(Deck.pickCard());
			System.out.println("\nThe dealer draws the " + dealerCards.get(dealerCards.size()-1).toString());
			//sets up an array with 2 values, [minhandvalue] [maxhandvalue]
			dealerHandVal = Hand.getValues(dealerCards);
			dealerMinValue = dealerHandVal[0];
			dealerMaxValue = dealerHandVal[1];

			//if an ace is in hand there will be 2 values, otherwise only 1
			if(dealerMinValue == dealerMaxValue)
				System.out.println("The dealer has " + dealerMinValue);
			else
				System.out.println("The dealer has " + dealerMinValue + " or " + dealerMaxValue + " due to Ace.");

			//above 16 dealer will stand unless of course he busts OR gets blackjack
			if(dealerMaxValue >= 17 && dealerMaxValue < 22) {
				//
				if(dealerMaxValue == 21) {
					System.out.println("Dealer got blackjack.");
					losses++;
					System.out.println("Wins: " + wins);
					System.out.println("Losses: " + losses + "\n");
					dealerTurnOver = true;
					roundOver = true;
				}

				//dealer stands but handvalue is less than player's handvalue
				else if(dealerMaxValue < playerMaxValue) {
					System.out.println("You win.");
					wins++;
					System.out.println("Wins: " + wins);
					System.out.println("Losses: " + losses + "\n");

					dealerTurnOver = true;
					roundOver = true;
				}
				//dealer and player handvalues are equal. casino rules state dealer wins
				else if(dealerMaxValue == playerMaxValue) {
					System.out.println("You tied with the dealer. By house rules dealer wins.");
					losses++;
					System.out.println("Wins: " + wins);
					System.out.println("Losses: " + losses + "\n");
					dealerTurnOver = true;
					roundOver = true;
				}
				//in case of odd cases, this catches all basic losing conditions including rare circumstances like both players = 21
				else {
					System.out.println("The dealer beat you.");
					losses++;
					System.out.println("Wins: " + wins);
					System.out.println("Losses: " + losses + "\n");
					dealerTurnOver = true;
					roundOver = true;
				}

			}
			//if dealer hits at anything under 17 and goes over 21 he instantly loses
			if(dealerMaxValue > 21) {
				System.out.println("The dealer busts. You win!");
				wins++;
				System.out.println("Wins: " + wins);
				System.out.println("Losses: " + losses + "\n");
				dealerTurnOver = true;
				roundOver = true;
			}
		}
	}
	//not sure where else to put this, simply exits the program if user enters 2 at main menu
	public void endProgram() {
		System.exit(0);
	}
}
