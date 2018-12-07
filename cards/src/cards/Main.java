package cards;

import java.util.Scanner;

public class Main {
	static Deck deck = new Deck();
	static Player player = new Player();
	static Dealer dealer = new Dealer();
	static boolean game = true;
	/*
	 * Couldn't think of any other way besides making them static, and with some of
	 * the objects being used in multiple classes, this would just be the easiest
	 * way.
	 */

	public static void main(String[] args) {
		int aceValue = 0;
		deck.shuffle();
		player.addCard(2);
		dealer.addCard(2);
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		String preVali = "";
		System.out.println("Dealer: " + dealer.getFirstCard() + "\nPlayer: "
				+ player.getCards().substring(0, player.getCards().length() - 2));
		while (game == true) {
			preVali = "";
			while (!isValid(preVali)) {
				System.out.print("Hit (0) or Stand (1): ");
				preVali = sc.nextLine();
			}
			choice = Integer.parseInt(preVali);
			if (choice == 0) {
				player.hit();
				System.out.println("Dealer: " + dealer.getFirstCard() + "\nPlayer: "
						+ player.getCards().substring(0, player.getCards().length() - 2));
			} else if (choice == 1) {
				player.stand();
				while (player.aceCount > 0) {
					preVali = "";
					while (!isValid(preVali)) {
						System.out.print("Ace - Value (1 or 11): ");
						preVali = sc.nextLine();
					}
					aceValue = Integer.parseInt(preVali);
					if (aceValue != 1 || aceValue != 11) {
						player.totalVal += aceValue;
						player.aceCount--;
					} else {
						System.out.println("Invalid Number");
					}
				}
				System.out.println("Dealer: " + dealer.getFirstCard() + "\nPlayer: "
						+ player.getCards().substring(0, player.getCards().length() - 2));
				dealer.ai();
			} else {
				continue;
			}
		}

		sc.close();
	}

	public static boolean isValid(String test) {
		return (test.equals("1") || test.equals("0"));
	}

	public Deck getDeckState() {
		return deck;
	}

	public Player getPlayer() {
		return player;
	}

	public void finalScore() {
		System.out.print("\n");
		if (player.totalVal == dealer.totalVal || (player.totalVal > 21 && dealer.totalVal > 21)) {
			System.out.println("No one wins");
		} else if ((player.totalVal > dealer.totalVal && player.totalVal <= 21)
				|| (player.totalVal <= 21 && dealer.totalVal > 21)) {
			System.out.println("Player wins");
		} else if ((dealer.totalVal > player.totalVal && dealer.totalVal <= 21)
				|| (player.totalVal > 21 && dealer.totalVal <= 21)) {
			System.out.println("Dealer wins");
		}
		System.out.println("Final Cards\nDealer (" + dealer.totalVal + "): "
				+ dealer.getCards().substring(0, dealer.getCards().length() - 2) + "\nPlayer (" + player.totalVal
				+ "): " + player.getCards().substring(0, player.getCards().length() - 2));
		game = false;
	}
}
