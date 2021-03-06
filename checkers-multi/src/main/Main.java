package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	public Main() {
		setSize(420, 70);
		JPanel p = new JPanel();
		p.setSize(400, 70);
		setResizable(false);
		setLocationRelativeTo(null);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton b1 = new JButton("Host");
		b1.setSize(100, 16);
		b1.setPreferredSize(new Dimension(100, 32));
		JButton b2 = new JButton("Client");
		b2.setSize(100, 16);
		b2.setPreferredSize(new Dimension(100, 32));
		SpinnerNumberModel model = new SpinnerNumberModel(8081, 1024, 44405, 1);
		JSpinner spin = new JSpinner(model);
		((DefaultEditor) spin.getEditor()).getTextField().setEditable(false);
		spin.setSize(100, 32);
		spin.setPreferredSize(new Dimension(100,32));
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int port = (int)spin.getValue();
				new Host(port);
			}
		
		});
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Client((int)spin.getValue());
			}
		
		});
		p.add(b1, BorderLayout.EAST);
		p.add(b2, BorderLayout.WEST);
		p.add(spin, BorderLayout.CENTER);
		add(p);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new Main();
	}
}
