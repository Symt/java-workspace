package filereader;

import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		String result = readFile();
		System.out.println("-- Reading -- \n" + result);
		System.out.println("-- Writing -- \nSuccess: " + writeFile(result));
	}

	private static boolean writeFile(String text) {
		try {
			BufferedWriter outStream = new BufferedWriter(new FileWriter("result.txt"));
			String[] split = text.split("\n");
			for (String i : split) {
				outStream.write(i);
				outStream.newLine();
			}
			outStream.close();

			Desktop desktop = Desktop.getDesktop();
			desktop.open(new File("result.txt"));
			return true;
		} catch (IOException e) {
			return false;
		}

	}

	private static String readFile() throws IOException {
		String result = "";
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter File Path: ");
		String path = sc.nextLine();
		String os = System.getProperty("os.name").toLowerCase();
		if (path.contains("~") && (os.indexOf("mac") >= 0 || os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") >= 0)) {
			path = path.replaceAll("~", "/Users/" + System.getProperty("user.name"));
		} else if (path.contains("~") && System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
			path = path.replaceAll("~", "C://Users//" + System.getProperty("user.name")); // Untested
			path = path.replaceAll("/", "//"); // Untested
		}
		File inFile = new File(path);
		if (inFile.exists()) {
			try {
				BufferedReader inStream = new BufferedReader(new FileReader(inFile));

				String inString;
				while ((inString = inStream.readLine()) != null) {
					result += inString + "\n";
				}
				inStream.close();
			} catch (FileNotFoundException e) {
				System.out.println("Invalid path - Probably goes to a directory....\nPath: " + path + "\nExiting...");
				System.exit(66);
			}
		} else {
			System.out.println("Specified file does not exist....\nPath: " + path + "\nExiting...");
			System.exit(66);
		}
		sc.close();
		return cleanTextContent(result);
	}

	private static String cleanTextContent(String text) {
		return text.replaceAll("[^\\x00-\\x7F]", "");
	}
}