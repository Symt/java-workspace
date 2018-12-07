package cards;

public class Dealer {
	Main main = new Main();
	String cardSet = "";
	Deck deck = main.getDeckState();
	boolean standStatus = false;
	String firstCard = "";
	Player player = main.getPlayer();
	int totalVal = 0;
	int aceCount = 0;
	
	public String getCards() {
		return cardSet;
	}
	public void addCard(int times) {
		while (times != 0) {
			if (cardSet.equals("")) {
				firstCard = deck.returnFirst();
			}
			totalVal += deck.firstValue();
			if (deck.firstValue() == 0) {
				aceCount += 1;
			}
			cardSet += deck.returnFirst() + ", ";
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
	public String getFirstCard() {
		return firstCard;
	}
	
	public void ai() {
		int stopVal = 10;
		if (player.standStatus || player.totalVal >= 21) {
			while (totalVal < 17) {
				while (totalVal < stopVal) {
					hit();
				}
				if (stopVal < 18) {
					stopVal++;
				}
				while (aceCount > 0) {
					if (totalVal + 11 > 21) {
						totalVal++;
					} else {
						totalVal += 11;
					}
					aceCount--;
				}
			}
			main.finalScore();
		}
	}
}
