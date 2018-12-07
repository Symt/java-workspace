package cards;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
	Card[] deck = {};
	String firstCard;

	public Deck() {
		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 0 };
		String[] suit = { "Clubs", "Spades", "Diamonds", "Hearts" };
		String[] cardTypes = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		for (String s : suit) {
			for (int i = 0; i < cardTypes.length; i++) {
				String c = cardTypes[i];
				int v = values[i];
				deck = Arrays.copyOf(deck, deck.length + 1);
				deck[deck.length - 1] = new Card(s, c, v);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(Arrays.asList(deck));
	}

	public String returnFirst() {
		firstCard = deck[0].cType + " (" + deck[0].suit + ")";
		return firstCard;
	}
	public int firstValue() {
		return deck[0].values;
	}
	public void rotateOne() {
		Collections.rotate(Arrays.asList(deck), 1);
	}

	public Card[] returnDeck() {
		return deck;
	}
}