package synograb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static final double VERSION = 1.0;
	public Scanner sc = new Scanner(System.in);
	public boolean[] find;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		System.out.println("Welcome to Synograb version " + VERSION);

		System.out.println("Please enter a single paragraph below\n--------------------------------------");
		String paragraph = sc.nextLine();

		String[] split = paragraph.split("(\\s|\\.|\\!|\\?)+");
		find = new boolean[split.length];

		System.out.print("Would you like to try all words (Y/N): ");
		if (sc.nextLine().equals("Y")) {
			Arrays.fill(find, true);
		} else {
			for (int i = 0; i < split.length; i++) {
				String str = split[i];
				System.out.print("Do you want to find a synonym for " + str + " (Y/N): ");
				find[i] = sc.nextLine().equalsIgnoreCase("Y");
			}
		}
		for (int i = 0; i < find.length; i++) {
			if (find[i]) {
				split[i] = getSynonym(split[i]);
			}
		}
		System.out.println(format(split));
	}

	public String getSynonym(String word) {
		String synonym = "";
		String synString = "";
		String elem = "*";
		ArrayList<String> synList = new ArrayList<String>();
		String url = "https://www.thesaurus.com/browse/" + word;
		try {

			Document document = Jsoup.connect(url).get();
			Elements synonyms = document.select(".css-3kshty");
			if (synonyms.size() == 0) {
				return word;
			}
			for (Element elm : synonyms) {
				synString += elm.text() + ", ";
				synList.add(elm.text());
			}
			synString = synString.replaceAll(", $", "");
			System.out.println("Possible Synonyms: " + synString);
			while (true) {
				System.out.print("Pick a synonym to put in place, leave blank to skip word (" + word + "): ");
				elem = sc.nextLine();
				if (synList.contains(elem)) {
					synonym = elem;
					break;
				} else {
					if (elem.equals("")) {
						return word;
					}
					System.out.print("Please enter a valid synonym from the list.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return synonym;
	}

	public String format(String[] list) {
		String formatted = "";
		for (int i = 0; i < list.length; i++) {
			formatted += list[i] + " ";
		}
		return formatted.trim();
	}
}
