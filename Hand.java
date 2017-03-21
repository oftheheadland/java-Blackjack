package blackjackreckoning3;

import java.util.ArrayList;

//Handles the arraylist composing current players hand

public class Hand {

	public static int[] getValues(ArrayList<Card> list) {
		//find minimum value of hand
		int count1 = 0;
		for(Card x: list) {
			count1 = count1 + x.getValue();  }

		//find maximum value of hand, checking if Ace can be used
		int count2 = 0;
		boolean usedEleven = false;
		for(Card y: list) {
			if(y.getValue() == 1 && !usedEleven) {  // Checks for Ace and if the 11 is needed
				count2 = count2 + 11;
				usedEleven = true;
			}
			else
				count2 = count2 + y.getValue();	

		}
		//checks if count2 is even applicable. if ace=11 results in a bust then it defaults back to ace=1
		if(count2 > 21)
			count2 = count1;
		//puts the values into an array and sends them back to blackjack
		int[] handValue = {count1, count2};	
		return handValue;
	}

}
