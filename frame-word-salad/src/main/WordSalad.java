package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.nio.charset.Charset;
import java.nio.file.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WordSalad {
	
	File file;
	String path;
	String contents = "";
	int k;
	
	public WordSalad(int width, int height) {
		
		JFrame frame = new JFrame("Word Salad");
		JPanel panel = new JPanel(new BorderLayout());
		JButton fileAdd = new JButton("Select File");
		JButton run = new JButton("Submit");
		JLabel kLabel = new JLabel("k: ");
		JTextArea textArea = new JTextArea();
		Integer[] options = {1,2,3,4,5,6,7,8,9,10};
		JSpinner spinner = new JSpinner(new SpinnerListModel(options));
		JPanel n1 = new JPanel();
		JPanel n2 = new JPanel();
		JPanel n3 = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width, height));
		
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setColumns(2);
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		GroupLayout n1l = new GroupLayout(n1);
		n1l.setHorizontalGroup(
				n1l.createSequentialGroup()
				.addComponent(kLabel)
				.addComponent(spinner));
		
		GroupLayout n2l = new GroupLayout(n2);
		n2l.setVerticalGroup(
				n2l.createSequentialGroup()
				.addComponent(fileAdd)
				.addComponent(run));
		
		JScrollPane sp = new JScrollPane(textArea);
		sp.setPreferredSize(new Dimension(width-20, height*2/3));
		sp.setBorder(null);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		n3.add(sp);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(n1, BorderLayout.NORTH);
		panel.add(n2, BorderLayout.CENTER);
		panel.add(n3, BorderLayout.SOUTH);
		
		frame.add(panel);
		
		frame.setResizable(false);	
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		fileAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt", "text"));
            	int val = fileChooser.showOpenDialog(fileChooser);
            	if (val == JFileChooser.APPROVE_OPTION) {
            	     file = fileChooser.getSelectedFile();
            	     path = file.getPath();
            	     try {
						contents = readFile(path, Charset.defaultCharset());
					} catch (Exception err) {
						err.printStackTrace();
					}
            	     fileAdd.setText(file.getName());
            	}
            }
        });
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (file == null) {            		
            		return;
            	}
            	k = (Integer) spinner.getValue();
            	contents = mixUp();
            	textArea.setText(contents);
            }
        });
	}
	
	public String mixUp() {
		
		// Mix some stuff up
		
		return contents;
	}
	
	public String readFile(String path, Charset encoding) throws IOException {
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = (int) screenSize.getWidth()/2;
		int height = (int) screenSize.getHeight()/2;
		new WordSalad(width, height);
	}
}
