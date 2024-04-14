package finalJavaAssignment;

import java.util.ArrayList;
import java.util.List;

public class Player { 
	// this class makes a player to play the card game War, which needs two different instances of player to work.
	
	private List<Card> hand;
	private int score;
	private String name;
	
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<>();
		this.score = 0;
	}
	
	public int incrementScore() {
		return this.score = this.score + 1;
	}
	
	public String displayPlayerName() {
		return this.name;
	}
	
	public void showCardsInHand() {
		//a testing method
		for (Card cardInHand : hand) {
			System.out.println(cardInHand);
		}
	}
	
	public Card showPlayerCard() {
		//set this to a variable. Removes the first card from hand after being called
		return this.hand.remove(0);
	}
	
	
	public void addCardToHand(Card card) {
		//to be used in a for loop in junction with Deck.draw()
		this.hand.add(card);
	}
	
	
	//some of these may not be in use either
	
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//methods
	
	//describe information about the player and calls the describe method for each card in the Hand list
	//flip, removes and returns the top Card of the Hand
	//draw, Deck instance as an argument and calls the 'draw' method from deck
	//incrementScore, add +1 to score every time the player wins an exchange.

}
