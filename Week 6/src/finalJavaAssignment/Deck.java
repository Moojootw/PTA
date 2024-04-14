package finalJavaAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	//A deck is a list of cards that has every possible card in a normal deck of playing cards
	List<Card> cards = new ArrayList<Card>();
	
	Deck() { // This contains the attributes of all cards in a deck (suits and values)
		String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
		String[] numbers = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
		
		for (String suit: suits) {
			int count = 2; //generates cards; value by suit
			for (String number : numbers) {
				Card card = new Card(number, suit, count);
				this.cards.add(card);
				count++;
			}
		}
	}

	//some of these are unused, perhaps some clean up is needed?
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public void describe() {
		for (Card card: this.cards) {
			card.describe();
		}
		
	}
	
	public void shuffle() { //takes a fresh deck and simulates a person shuffling it
		Collections.shuffle(this.cards);
	}
	
	public Card draw() {
		Card card = this.cards.remove(0); //removes the "top" card of the deck
		return card;
	}
}
