/**
 * 
 * @author Zach Misic
 * @author Charlie Fulks 
 */


package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("unchecked")
public class Arrays3 extends javax.swing.JFrame {

    // Size of the array being animated

    public static int asize = 50;
    // The array being displayed
    public int arr[][] = new int[asize][asize];
    // An array this holds the previous values
    public boolean running = true;
    // The colors being displayed.  These colors are pretty terrible - please use better ones!
    public static Color[] colors = {
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.PINK,
        Color.MAGENTA,
        Color.LIGHT_GRAY,
        Color.ORANGE,
        Color.BLACK
    };
    // Number of different colors
    public static int ncolors = colors.length;
    public Random randoms = new Random();
    // Animate at 4 frames / second
    public Timer clock = new Timer(250, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            tick();
            arrayView.repaint();
        }
    });

    public Arrays3() {
        initComponents();

        // The following code is needed to make all three
        // comboboxes have the same options on pull down.
        ComboBoxModel m = patternModify1.getModel();
        DefaultComboBoxModel m1 = new DefaultComboBoxModel();
        DefaultComboBoxModel m2 = new DefaultComboBoxModel();
        for (int i = 0; i < m.getSize(); i++) {
            m1.addElement(m.getElementAt(i));
            m2.addElement(m.getElementAt(i));
        }
        patternModify2.setModel(m1);
        patternModify3.setModel(m2);

        clock.start();

    }

    public class MyPanel extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            // Draw 5x5 squares of color in the graphics panel
            for (int i = 0; i < asize; i++) {
                for (int j = 0; j < asize; j++) {
                    g.setColor(colors[arr[i][j]]);
                    g.fillRect(j * 5, i * 5, 5, 5);
                }
            }
        }
    }

    public void tick() {
        if (running) {
            // At each tick. modify the array three times:
            modifyArray(patternModify1.getSelectedIndex());
            modifyArray(patternModify2.getSelectedIndex());
            modifyArray(patternModify3.getSelectedIndex());
            arrayView.repaint();
        }
    }

    // These functions "wrap" the colors / positions.  That is, when you go past
    // the last color or position you get to the first one.  Using these prevents
    // out of bounds errors
    public int cr(int i) {
        return (i + 20 * ncolors) % ncolors;
    }

    public int ir(int i) {
        return (i + 20 * asize) % asize;
    }

    // Fill in the code to modifyArray.  The "whatToDo" is an integer
    // corresponding to the options on the pulldowns.  You need to make sure
    // that these correspond correctly with the order of items in the
    // drop down

    @SuppressWarnings("ManualArrayToCollectionCopy")
    public void modifyArray(int whatToDo) {
        int oldarr[][] = new int[asize][asize];
        // Controlled by stop/startarr = oldarr;
        for (int i = 0; i < asize; i++) {
            for (int j = 0; j < asize; j++) {
                oldarr[i][j] = arr[i][j];
            }
        }
        // Your code puts values into arr, with the previous array values in oldarr.
        switch (whatToDo) {
            case 0: //No Change
                // No need to change the array!
                break;
            case 1: //Transpose
                for (int i = 0; i < asize; i++) {
                    for (int k = 0; k < asize; k++) {
                        arr[i][k] = oldarr[k][i];
                        arr[k][i] = oldarr[i][k];
                    }
                }

                break;
            case 2: //Rotate
                for (int i = 0; i < asize; i++) {
                    for (int k = 0; k < asize; k++) {
                        arr[i][k] = oldarr[k][asize-i-1];
                        arr[k][i] = oldarr[i][asize-k-1];
                    }
                }
                  
                break;
            case 3: //Increment
                for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                        arr[i][j] = cr(oldarr[i][j] + 1);
                    }
                }

                break;
            case 4: //Mirror
                for (int i = 0; i < asize; i++) {
                    for (int k = 0; k < asize/2; k++) {
                        arr[i][k+asize/2] = oldarr[i][asize/2-k];
                    }
                    
                }
                break;
            case 5: //Shift Right
                for (int i = 0; i < asize; i++) {
                   for (int k = 0; k < asize; k++) {
                        arr[i][k] = oldarr[i][ir(k-1)];
                   }
                }
                break;
            case 6: //Shift Down
                for (int i = 0; i < asize; i++) {
                   for (int k = 0; k < asize; k++) {
                        arr[i][k] = oldarr[ir(i-1)][k];
                   }
                }
                break;
            case 7: //Blur
                for (int i = 0; i < asize; i++) {
                   for (int k = 0; k < asize; k++) {
                       arr[i][k] = average(i, k, oldarr);               
                   }
                }
                break;
            case 8: //Invert
                for (int i = 0; i < asize; i++) {
                   for (int k = 0; k < asize; k++) {
                       arr[i][k] = cr(Math.abs(oldarr[i][k]-8));              
                   }
                }
                break;
            case 9: // Random Square
               
                int e = ir(ThreadLocalRandom.current().nextInt(0, 49)); // x
                int f = ir(ThreadLocalRandom.current().nextInt(0, 49)); // y
                int c = ThreadLocalRandom.current().nextInt(3, 14); // width or height
                int d = cr(ThreadLocalRandom.current().nextInt(0, 7)); // color
                
                for (int i = e-c; i < e+c; i++) {
                  for (int k = f-c; k < f+c; k++) {
                     arr[Math.abs(ir(i))][Math.abs(ir(k))] = cr(d);
                  }
                } 
                break;
            case 10: // Random Shift
                int l = ir(ThreadLocalRandom.current().nextInt(0, 10));
                int m = ir(ThreadLocalRandom.current().nextInt(0, 10));
                for (int i = 0; i < asize; i++) {
                   for (int k = 0; k < asize; k++) {
                        arr[i][k] = oldarr[ir(i-l)][ir(k-m)];
                   }
                }
                break;
            case 11: //Student Choice
                int a, b;
                for (int i = 0; i < asize; i++) {
                   for (int k = 0; k < asize; k++) {
                       a = ir(ThreadLocalRandom.current().nextInt(0, 49));
                       b = ir(ThreadLocalRandom.current().nextInt(0, 49));
                       arr[i][k] = oldarr[a][b]; 
                   }
                }
                break;
        }
    }
    
    public int average(int a, int b, int[][] oldarr) {
         int lol = oldarr[ir(a)][ir(b)]+oldarr[ir(a-1)][ir(b-1)]+oldarr[ir(a-1)][ir(b)]+oldarr[ir(a-1)][ir(b+1)]+oldarr[ir(a)][ir(b-1)]+oldarr[ir(a)][ir(b+1)]+oldarr[ir(a+1)][ir(b-1)]+oldarr[ir(a+1)][ir(b+1)]+oldarr[ir(a+1)][ir(b)];
         int average = cr(lol/9);   
         return average;   
    }

    // This sets the array to some specific value
    // As above, the integer indicates which option was
    // selected on the pulldown.
    public void setArray(int whatToDo) {
        switch (whatToDo) {
            case 0: //Blank
                for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                        arr[i][j] = 0;
                    }
                }
                break;
            case 1: // Stripes
                for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                        arr[i][j] = cr(j);
                    }
                }
                break;
            case 2: //Checkerboard
                for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                        arr[i][j] = cr((j + i) % 2);
                    }
                }
                break;
            case 3: //Circle
                
                int dist;
                for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                        dist = (int) (Math.sqrt(Math.pow(i-(asize/2-.5), 2) + Math.pow(j-(asize/2-.5), 2)) * .3);
                        arr[i][j] = cr(dist % 8);
                    }
                }
                break;
            case 4: //Diagonals
                for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                        arr[i][j] = cr(j+i);
                    }
                }
                break;
            case 5: //Wider and wider
                int sp = 1;
                int lp = 0;
                
                for (int i = 0; i < asize; i++) {
                		if (sp == lp) {
                			lp = 0;
                			sp++;
                		}
                        Arrays.fill(arr[i],  cr(sp));
                        lp++;
                }
                
                break;
            case 6: //Random Noise
                int a;
                for (int i = 0; i < asize; i++) {
                   for (int k = 0; k < asize; k++) {
                       a = cr(ThreadLocalRandom.current().nextInt(0, 7));
                       arr[i][k] = a; 
                   }
                }
                break;
            case 7: //Cosmic
            	double r1 = cosmicR();
        		double r2 = cosmicR();
        		double r3 = cosmicR();
            	for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                    	int x = i-arr.length/2;
                    	int y = j-arr.length/2;
                        arr[i][j] = cr(Math.abs((int) (r1*x + r2*y + r3*x*y))); 
                    }
                 }
            	
                break;
            case 8: //Free Choice
                for (int i = 0; i < asize; i++) {
                    for (int j = 0; j < asize; j++) {
                        arr[i][j] = cr(j + i % 2);
                    }
                }
                break;

        }
    }


    private double cosmicR() {
    	return ThreadLocalRandom.current().nextDouble(-0.5, 0.5);
    }

    //Shouldn't need to edit any code below this point. All has to do with GUI output.

    @SuppressWarnings("unchecked")
    private void initComponents() {

        arrayView = new MyPanel();
        setPattern = new javax.swing.JComboBox();
        patternModify1 = new javax.swing.JComboBox();
        patternModify2 = new javax.swing.JComboBox();
        patternModify3 = new javax.swing.JComboBox();
        startstop = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        arrayView.setPreferredSize(new java.awt.Dimension(250, 250));
        setResizable(false);
        setPattern.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
            "Blank",
            "Stripes",
            "Checkerboard",
            "Circle",
            "Diagonals",
            "Wider and wider",
            "Random Noise",
            "Cosmic",
            "Free Choice"
        }));
        setPattern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPatternActionPerformed(evt);
            }
        });

        patternModify1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
            "No Change",
            "Transpose",
            "Rotate",
            "Increment",
            "Mirror",
            "Shift Right",
            "Shift Down",
            "Blur",
            "Invert",
            "Random Square",
            "Random Shift",
            "Student Choice"
        }));
        patternModify1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternModify1ActionPerformed(evt);
            }
        });

        patternModify2.setModel(patternModify1.getModel());
        patternModify2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternModify2ActionPerformed(evt);
            }
        });

        patternModify3.setModel(patternModify1.getModel());
        patternModify3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternModify3ActionPerformed(evt);
            }
        });

        startstop.setText("Start / Stop");
        startstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startstopActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addComponent(arrayView)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(patternModify1)
                .addComponent(patternModify2)
                .addComponent(patternModify3)
                .addComponent(setPattern)
                .addComponent(startstop))

        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(arrayView)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(patternModify1)
                    .addComponent(patternModify2)
                    .addComponent(patternModify3)
                    .addComponent(setPattern)
                    .addComponent(startstop)))
        );



        pack();
    }

    private void setPatternActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_setPatternActionPerformed
        setArray(setPattern.getSelectedIndex());
        arrayView.repaint();
    }
    private void patternModify1ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_patternModify1ActionPerformed
        // Nothing to do when the modify settings change
    } //GEN-LAST:event_patternModify1ActionPerformed

    private void patternModify2ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_patternModify2ActionPerformed
    } //GEN-LAST:event_patternModify2ActionPerformed

    private void patternModify3ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_patternModify3ActionPerformed
    } //GEN-LAST:event_patternModify3ActionPerformed

    private void startstopActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_startstopActionPerformed
        running = !running;
    } //GEN-LAST:event_startstopActionPerformed
    //GEN-LAST:event_startstopActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Arrays3().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel arrayView;
    private javax.swing.JComboBox patternModify1;
    private javax.swing.JComboBox patternModify2;
    private javax.swing.JComboBox patternModify3;
    private javax.swing.JComboBox setPattern;
    private javax.swing.JButton startstop;
    // End of variables declaration//GEN-END:variables
}