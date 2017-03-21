package blackjackreckoning3;

//Handles cards as objects with all of their attributes - suits,names,values

public class Card
{
	//initial variables
	private String suit;
	private String name;
	private int value;
	//card object attributes, each card has these 3
	public Card(String suit, String name, int value) {
		this.suit = suit;
		this.name = name;
		this.value = value;
	}
	//conver to printable format (ex. Jack of Spades)
	public String toString() {
		String fullName = name + " of " + suit;
		return fullName;
	}
	public int getValue() {
		return value; 
	}
}