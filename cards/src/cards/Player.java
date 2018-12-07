package cards;
public class Player {
	Main main = new Main();
	String cardSet = "";
	Deck deck = main.getDeckState();
	boolean standStatus = false;
	int totalVal = 0;
	int aceCount = 0;

	public String getCards() {
		return cardSet;
	}
	public void addCard(int times) {
		while (times != 0) {
			cardSet += deck.returnFirst() + ", ";
			if (deck.firstValue() == 0) {
				aceCount++;
			}
			totalVal += deck.firstValue();
			deck.rotateOne();
			times--;
		}
	}
	public void hit() {
		addCard(1);
	}
	public void stand() {
		standStatus = true;
		// does nothing, just for show.
	}
}
