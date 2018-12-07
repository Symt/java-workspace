package indent;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Indent {
	int col = 0;
	int maxCol = 0;
	String code;

	public Indent() {
		code = "";
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFileChooser fc = new JFileChooser("Choose File");
		fc.addChoosableFileFilter(new FileNameExtensionFilter("All Files", "*"));
		int val = fc.showOpenDialog(frame);
		if (val == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String path = file.getPath();
			try {
				code = readFile(path, Charset.defaultCharset());
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
		ArrayList<String> splitCode = new ArrayList<String>(Arrays.asList(code.split("\n")));
		String temp = "";
		String temp2 = "";
		for (int i = 0; i < splitCode.size(); i++) {
			temp = splitCode.get(i);
			if (temp.equals("") || onlyContains(temp, '\t')) {
				splitCode.remove(i);
				continue;
			} else if (temp.startsWith("}") && !temp.equals("}")) {
				
			} else if (temp.equals("}") || temp.equals("{")) {
				temp2 = splitCode.get(i-1);
				temp2 += temp;
				splitCode.remove(i);
				i--;
				splitCode.set(i, temp2);
				continue;
			}
			splitCode.set(i, temp);
		}
		maxCol = maxLength(splitCode);
		
		int diff = 0;
		String spaces = "";
		
		for (int i = 0; i < splitCode.size(); i++) {
			temp2 = splitCode.get(i);
			if (temp2.length() < maxCol) {
				diff = maxCol - temp2.length();
			} else {
				diff = 0;
			}
			spaces = new String(new char[diff]).replace("\0", " ");
			
			if (temp2.endsWith(";")) {
				temp2 = temp2.substring(0, temp2.length()-1) + spaces + ";";
			} else if (temp2.endsWith("}")) {
				temp2 = temp2.substring(0, temp2.length()-1) + spaces + "}";
			} else if (temp2.endsWith("{")) {
				temp2 = temp2.substring(0, temp2.length()-1) + spaces + "{";
			} else if (temp2.endsWith("(")) {
				temp2 = temp2.substring(0, temp2.length()-1) + spaces + "(";
			}
			System.out.println(temp2);
			
		}
		frame.dispose();

	}
	
	private int maxLength(ArrayList<String> parsed) {
		int max = 0;
		String temp;
		int tabCount;
		for (int i = 0; i < parsed.size(); i++) {
			temp = parsed.get(i);
			tabCount = countTabs(temp);
			System.out.println(temp + " " + temp.length());
			if (temp.startsWith("\t")) {
				temp = (new String(new char[tabCount]).replace("\0", " ")) + temp.substring(tabCount, temp.length()-1);
			}
			col = temp.length();
			if (col >= max) {
				max = col;
			}
		}

		return max;
	}
	
	private boolean onlyContains(String str, char c) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != c) {
				return false;
			}
		}
		return true;
	}
	
	private int countTabs(String test) {
		int count = 0;
		for (int i = 0; i < test.length(); i++) {
			if (test.charAt(i) == '\t') {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public static void main(String[] args) {
		new Indent();
	}
}