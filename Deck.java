package blackjackreckoning3;

import java.util.ArrayList;

//Handles drawing a random card and creates 52 cards with card suits

public class Deck {

	//Arraylists are my favorite thing about java so far, really nice to work with
	static ArrayList<Card> cards = new ArrayList<Card>();


	//Handles drawing a random card
	//Achieves this by picking a random value between 0-1 and multiplying by 52 (Ex. .5*52=26th card would be drawn)
	public static Card pickCard() {
		double randCard = Math.ceil(Math.random() * 52);
		int cardNumber = (int)randCard;
		return cards.get(cardNumber-1);
	}
	public static void setCards() {
		//make 4 aces of each suit with value 1, Blackjack.java handles when to use 11
		String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Ace" , 1));
		}
		//make 4 jacks with value 10
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Jack" , 10));
		}
		//make 4 queens with value 10
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Queen" , 10));
		}
		//make 4 kings with value 10
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "King" , 10));
		}

		//make 4 twos
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Two" , 2));
		}
		//make 4 threes
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Three" , 3));
		}
		//make 4 fours
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Four" , 4));
		}
		//make 4 fives
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Five" , 5));
		}
		//make 4 sixes
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Six" , 6));
		}
		//make 4 sevens
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Seven" , 7));
		}
		//make 4 eights
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Eight" , 8));
		}
		//make 4 nines
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Nine" , 9));
		}
		//make 4 tens
		for(int i = 1; i < 5; i++) {
			cards.add(new Card(suits[i-1], "Ten" , 10));
		}
	}


}
