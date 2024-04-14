package finalJavaAssignment;

public class App {

	public static void main(String[] args) {
//!		instantiate a Deck and two player objects
//!		call the deck shuffle method
//!		use a for loop to iterate 52 times for Draw, alternating between players
//!		use a for loop to iterate 26 times the flip method for both players
//!			compare the values of the flipped card, increment points to winner. no points in a tie.
//!		after the for loops, compare the score for both players
//!			print the final score and either Player 1 or player 2 wins or 'its a draw'
//		
//		NOTES:
//!		use card Describe for every flipped card to illustrate how the game went.
//!			also tell who one the exchange
//!		print out the updated scores every turn
		
		//create a new deck and shuffle and add 2 players
		Deck GameDeck = new Deck();
		GameDeck.shuffle();
		Player Player1 = new Player("Tony Stark");
		Player Player2 =  new Player("Justin Hammer");
		
		
		for(int i = 0; i < 52; i++) {
			if (i % 2 == 0) {
				Player1.addCardToHand(GameDeck.draw());
			}
			else {
				Player2.addCardToHand(GameDeck.draw());
			}
		}
		
		
		//the commented out methods are for testing, they still work.
		
		//System.out.println("Done");
		Player1.displayPlayerName();
		//Player1.showCardsInHand();
		Player2.displayPlayerName();
		//Player2.showCardsInHand();
		
		
		//Playing the game
		for(int i = 0; i < 26; i++) {
			// compares the card values of each players' card and assigns points on who won the exchange
			Card P1card = Player1.showPlayerCard();
			Card P2card = Player2.showPlayerCard();
			if (P1card.getValue() > P2card.getValue()) {
				Player1.incrementScore();
				System.out.println(Player1.displayPlayerName() + "'s *" + P1card.toString() + "* beats " + Player2.displayPlayerName() + "'s *" + P2card.toString() + "*");	
			} else if (P1card.getValue() < P2card.getValue()) {
				Player2.incrementScore();
				System.out.println(Player2.displayPlayerName() + "'s *" +P2card.toString() + "* beats " + Player1.displayPlayerName() + "'s *" + P1card.toString() + "*");
			} else {
				System.out.println(Player1.displayPlayerName() + " TIES " + Player2.displayPlayerName() + "'s " + P2card.toString() + " with a " + P1card.toString());
			}
			System.out.println("current score is " + Player1.displayPlayerName() + ": " + Player1.getScore() + ", " + Player2.displayPlayerName() + ": " + Player2.getScore());
			
		}
		//Post game summary.
		if (Player1.getScore() > Player2.getScore()) {
			System.out.println(Player1.displayPlayerName() + " is the winner with " + Player1.getScore() + " to " + Player2.getScore());
		} else if (Player1.getScore() < Player2.getScore()) {
			System.out.println(Player2.displayPlayerName() + " is the winner with " + Player2.getScore() + " to " + Player1.getScore());
		} else {
			System.out.println("And the game ends in a tie: " + Player1.getScore() + " and " + Player2.getScore());
		}
		
		//final notes: this program could have better game analytics, such as "win/loss streak"
		// its hard coded to only really have two players. You can play with five players since there are only four of the same card, so ties can't happen and leaving out two cards could be interesting.
		// I probably could of used lambda functions for the print statements to make them more comprehensive.
		
		
		
	}

}
