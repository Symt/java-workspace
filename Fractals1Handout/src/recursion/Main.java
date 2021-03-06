/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 * @author Caldwell (fac_peterson)
 */
public class Main extends javax.swing.JFrame {
	public static double zoom = 1;
	public static double panx = 0;
	public static double pany = 0;
	public ArrayList<Polygon> world = new ArrayList<Polygon>();
	public ArrayList<Point2> a = new ArrayList<Point2>();

	public Main() {
		initComponents();
		ArrayList<Point2> a = new ArrayList<Point2>();
		a.add(new Point2(1, 0));
		a.add(new Point2(0.5, -1));
		a.add(new Point2(0, 0));
		Shape s = new Shape(a);
		for (int i = 0; i < 400; i = i + 100) {
			world.add(new Polygon(s, new Point2(i, 100), new Point2(i + 50, 100), Color.RED));
		}
		world.add(new Polygon(s, new Point2(50, 120), new Point2(100, 200), Color.BLUE));
		world.add(new Polygon(s, new Point2(50, 400), new Point2(150, 440), Color.BLUE));
		Shape s1 = Shape.regularShape(5);
		world.add(new Polygon(s1, new Point2(300, 300), new Point2(310, 320), Color.GREEN));
		Polygon p = new Polygon(s1, new Point2(400, 150), new Point2(425, 150), Color.BLACK);
		world.add(p);
		world.addAll(p.decorate(s));
	}

	public class MyPanel extends JPanel {

		@Override
		public void paint(Graphics g) {
			super.paint(g);

			for (Polygon i : world) {
				i.draw(g);
			}

		}
	}

	// **************** DO NOT CHANGE ANYTHING IN THE INITCOMPONENTS METHOD******************
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		graphicsPanel = new MyPanel();
		upButton = new javax.swing.JButton();
		leftButton = new javax.swing.JButton();
		downButton = new javax.swing.JButton();
		rightButton = new javax.swing.JButton();
		zoomInButton = new javax.swing.JButton();
		zoomOutButton = new javax.swing.JButton();
		slider1 = new javax.swing.JSlider();
		slider2 = new javax.swing.JSlider();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout graphicsPanelLayout = new javax.swing.GroupLayout(graphicsPanel);
		graphicsPanel.setLayout(graphicsPanelLayout);
		graphicsPanelLayout.setHorizontalGroup(graphicsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 555, Short.MAX_VALUE));
		graphicsPanelLayout.setVerticalGroup(graphicsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		upButton.setText("^");
		upButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				upButtonActionPerformed(evt);
			}
		});

		leftButton.setText("<-");
		leftButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				leftButtonActionPerformed(evt);
			}
		});

		downButton.setText("v");
		downButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				downButtonActionPerformed(evt);
			}
		});

		rightButton.setText("->");
		rightButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rightButtonActionPerformed(evt);
			}
		});

		zoomInButton.setText("Zoom In");
		zoomInButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				zoomInActionPerformed(evt);
			}
		});

		zoomOutButton.setText("Zoom Out");
		zoomOutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				zoomOutActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(graphicsPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(81, 81, 81)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(downButton).addComponent(upButton)))
						.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addComponent(zoomInButton)
												.addGap(32, 32, 32).addComponent(zoomOutButton))
										.addGroup(layout.createSequentialGroup().addComponent(leftButton)
												.addGap(69, 69, 69).addComponent(rightButton))))
						.addGroup(layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(slider2, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(slider1, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(graphicsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(upButton).addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(leftButton).addComponent(rightButton))
						.addGap(18, 18, 18).addComponent(downButton).addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(zoomInButton).addComponent(zoomOutButton))
						.addGap(18, 18, 18)
						.addComponent(slider1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(slider2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(259, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Add code for up Button Panning Here
	private void upButtonActionPerformed(java.awt.event.ActionEvent e) {
		for (Polygon p : world) {
			p.pany += p.zoomfac * .2 * graphicsPanel.getHeight();
		}
	}

	// Add Code for Left Button Panning Here

	private void leftButtonActionPerformed(java.awt.event.ActionEvent e) {
		for (Polygon p : world) {
			p.pany += p.zoomfac * .2 * graphicsPanel.getWidth();
		}
	}

	// Add code for right button panning here

	private void rightButtonActionPerformed(java.awt.event.ActionEvent e) {
		for (Polygon p : world) {
			p.pany -= p.zoomfac * .2 * graphicsPanel.getWidth();
		}
	}

	// Add code for Down Button Panning Here
	private void downButtonActionPerformed(java.awt.event.ActionEvent e) {
		for (Polygon p : world) {
			p.pany -= p.zoomfac * .2 * graphicsPanel.getHeight();
		}
	}

	// Add code for Zoom in here
	private void zoomInActionPerformed(java.awt.event.ActionEvent e) {
		for (Polygon p : world) {
			p.zoomfac *= 1.2d;
		}
	}

	// Add code for Zoom Out here
	private void zoomOutActionPerformed(java.awt.event.ActionEvent e) {
		for (Polygon p : world) {
			p.zoomfac /= 1.2d;
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
			}
		});

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton downButton;
	private javax.swing.JPanel graphicsPanel;
	private javax.swing.JButton leftButton;
	private javax.swing.JButton rightButton;
	private javax.swing.JSlider slider1;
	private javax.swing.JSlider slider2;
	private javax.swing.JButton upButton;
	private javax.swing.JButton zoomInButton;
	private javax.swing.JButton zoomOutButton;
	// End of variables declaration//GEN-END:variables
}
