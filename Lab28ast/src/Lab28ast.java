import java.awt.Desktop;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Lab28ast {
	public static void main(String args[]) throws IOException {
		String line1 = "How much wood would a wood chuck chuck";
		String line2 = "if a wood chuck would chuck wood?";
		String line3 = "He would chuck what a wood chuck COULD";
		String line4 = "if a wood chuck would chuck wood.";

		Encode topSecret = new Encode(line1, line2, line3, line4);
		topSecret.displayOriginalText();
		topSecret.enterKey();
		topSecret.encodeText();
		topSecret.displayEncodedText();
		topSecret.writeToFile();

		Decode notSecret = new Decode(topSecret.codeLine1, topSecret.codeLine2, topSecret.codeLine3,
				topSecret.codeLine4);

		notSecret.displayEncodedText();
		notSecret.enterKey();
		notSecret.decodeText();
		notSecret.displayOriginalText();
	}
}

class Encode {
	private String line1, line2, line3, line4;
	String codeLine1, codeLine2, codeLine3, codeLine4;
	private String key;

	public Encode(String ln1, String ln2, String ln3, String ln4) {
		codeLine1 = line1 = ln1;
		codeLine2 = line2 = ln2;
		codeLine3 = line3 = ln3;
		codeLine4 = line4 = ln4;
	}

	public void displayOriginalText() {
		System.out.println("The original uncoded message is:\n");
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);
		System.out.println(line4);
		System.out.println();
	}

	public void enterKey() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter key --> ");
		// For the 90-point version the "key" is a single letter.
		// For the 100-point version, the "key" is a word.
		key = input.nextLine();
	}

	private int find(char[] x, char y) {
		int result = -1;

		for (int i = 0; i < x.length; i++) {
			if (x[i] == y) {
				result = i;
				break;
			}
		}

		return result;
	}

	public String encodeLine(String line) {
		int ultimateKey;
		String result = "";
		char temp = ' ';
		ultimateKey = key.charAt(0);

		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == ' ') {
				result += " ";
				continue;
			}
			if (Character.isUpperCase(line.charAt(i))) {
				temp = Character.toLowerCase(line.charAt(i));
			} else {
				temp = line.charAt(i);
			}
			temp += ultimateKey;

			if (temp > 126) {
				temp = (char) (32 + temp % 129);
			}
			result += temp;
		}
		return result;
	}

	public void encodeText() {
		System.out.println("\nEncoding the Text\n");
		codeLine1 = encodeLine(line1);
		codeLine2 = encodeLine(line2);
		codeLine3 = encodeLine(line3);
		codeLine4 = encodeLine(line4);

	}

	public void displayEncodedText() {
		System.out.println("The ENCODED message is:\n");
		System.out.println(codeLine1);
		System.out.println(codeLine2);
		System.out.println(codeLine3);
		System.out.println(codeLine4);

	}

	public void writeToFile() throws IOException {
		System.out.println("Saving coded message\n");
		try {
			BufferedWriter outStream = new BufferedWriter(new FileWriter("code.dat"));
			String[] split = (codeLine1 + "\n" + codeLine2 + "\n" + codeLine3 + "\n" + codeLine4).split("\n");
			for (String i : split) {
				outStream.write(i);
				outStream.newLine();
			}
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Decode {
	private String line1, line2, line3, line4;
	private String codeLine1, codeLine2, codeLine3, codeLine4;
	private String key;

	public Decode(String a, String b, String c, String d) {
		line1 = a;
		line2 = b;
		line3 = c;
		line4 = d;
	}

	public void displayEncodedText() {
		System.out.println("The encoded message is:\n");
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);
		System.out.println(line4);
		System.out.println();
	}

	public void displayOriginalText() {
		System.out.println("The original text is:\n");
		System.out.println(codeLine1);
		System.out.println(codeLine2);
		System.out.println(codeLine3);
		System.out.println(codeLine4);
	}

	public void decodeText() {
		codeLine1 = decodeLine(line1);
		codeLine2 = decodeLine(line2);
		codeLine3 = decodeLine(line3);
		codeLine4 = decodeLine(line4);
	}

	public String decodeLine(String line) {
		int ultimateKey;
		String result = "";
		char temp = ' ';
		ultimateKey = key.charAt(0);

		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == ' ') {
				result += " ";
				continue;
			}
			if (Character.isUpperCase(line.charAt(i))) {
				temp = Character.toLowerCase(line.charAt(i));
			} else {
				temp = line.charAt(i);
			}
			temp = (char) ((int) temp - (int) ultimateKey);
			if ((int) temp < 32) {
				temp = (char) (temp - 32 + 129);
			}
			result += temp;
		}
		return result;
	}

	public void readFromFile() throws IOException {
		File inFile = new File("code.dat");
		if (inFile.exists()) {
			try {
				BufferedReader inStream = new BufferedReader(new FileReader(inFile));

				String inString;
				while ((inString = inStream.readLine()) != null) {
					if (codeLine1 == null) {
						codeLine1 = inString;
					} else if (codeLine2 == null) {
						codeLine2 = inString;
					} else if (codeLine3 == null) {
						codeLine3 = inString;
					} else if (codeLine4 == null) {
						codeLine4 = inString;
					} else {
						break;
					}
				}
				inStream.close();
			} catch (FileNotFoundException e) {
				System.exit(66);
			}
		} else {
			System.out.println("Specified file does not exist....\nExiting...");
			System.exit(66);
		}
	}

	public void enterKey() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter key --> ");
		key = input.nextLine();
	}

}