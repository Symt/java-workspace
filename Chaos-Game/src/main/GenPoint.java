package main;

import java.util.concurrent.ThreadLocalRandom;

public class GenPoint implements Runnable {
	public int nofpoints = 0;
	private int givenMax = 100000;
	private int radius = 375;
	private int r = 0;
	private double[] nexus = new double[2];
	public double[][] shape;
	public double[][] points;
	public boolean end = false;

	public GenPoint(int sides, int framewidth, int frameheight) {
		shape = new double[sides][2];
		double theta = ((2 * Math.PI) / sides);
		for (int i = 0; i < sides; i++) {
			shape[i][0] = ((double) radius) * Math.cos(theta * i
					+ ((sides % 2 == 1) ? 3 * Math.PI / 2 : (sides % 4 == 2) ? 2 * Math.PI / sides : Math.PI / sides));
			shape[i][1] = ((double) radius) * Math.sin(theta * i
					+ ((sides % 2 == 1) ? 3 * Math.PI / 2 : (sides % 4 == 2) ? 2 * Math.PI / sides : Math.PI / sides));
		}
		points = new double[100001][2];
		points[0][0] = framewidth/2;
		points[0][1] = frameheight/2;
	}

	public void run() {
		double[] endpoint = new double[2];
		while (!end) {
			try {
				produce(nofpoints);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			r = random();
			nexus[0] =  shape[r][0];
			nexus[1] =  shape[r][1];
			endpoint[0] = (nexus[0] + points[nofpoints][0])/2 + points[0][0]/2;
			endpoint[1] = (nexus[1] + points[nofpoints][1])/2 + points[0][1]/2;
			points[nofpoints+1][0] = endpoint[0];
			points[nofpoints+1][1] = endpoint[1];
			nofpoints++;
			
		}
	}

	private void produce(int points) throws InterruptedException {
		synchronized (this) {
			while (nofpoints >= givenMax) {
				System.out.println("-- Points are finished --");
				wait();
			}
		}
	}
	
	private int random() {
		return ThreadLocalRandom.current().nextInt(0, shape.length);
	}
}
