package finalJavaAssignment;

public class Card { 
	// this is the card class. It defines what a card is and has some extra utilities for gathering information on the card
	String name;
	String suit;
	int value;
	
	public Card(String name, String suit, int value) {
		this.name = name;
		this.suit = suit;
		this.value= value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void describe() { //unused
		System.out.println(this.name + " of "+ this.suit + " -- "+ this.value);
	}
	
	// @overrride
	public String toString() { //this is primarily used for display since the value part is a bit extra
		return name + " of " + suit;
	}
}
