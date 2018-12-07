package game.main;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Levels {

	private Handler handler;
	private int level;
	private long currentTime;
	private long totalTimeLevel;
	private HUD hud;
	public static boolean presentHeart = false;
	private long runningTotal;
	private long startTime = System.currentTimeMillis();
	private boolean finished = false;

	public Levels(int level, Handler handler, HUD hud) {

		this.handler = handler;
		this.level = level;
		this.hud = hud;

		spawnObjects(calculateTotalObjects(level));
	}

	private int rand(int low, int high) {
		return ThreadLocalRandom.current().nextInt(low, high);
	}

	private int calculateTotalObjects(int level) {
		int objects = (int) Math.pow(level, 2);

		return objects;
	}

	private void spawnObjects(int total) {
		for (int i = 0; i < total+5; i++) {
			handler.addObject(new BasicEnemy(rand(0, Main.WIDTH), rand(0, Main.HEIGHT), ID.BasicEnemy, handler));
		}

		totalTimeLevel = System.currentTimeMillis() + rand(5000, 10000);
		
		for (GameObject obj : handler.object) {
			if (obj.getID() == ID.Heart) {
				presentHeart = true;
			}
		}
		if (presentHeart) {
			presentHeart = false;
		} else {
			handler.addObject(new Heart(rand(0, Main.WIDTH)-32, rand(0, Main.HEIGHT)-32, ID.Heart, handler));
			presentHeart = true;
		}
	}

	private void removeAllEnemies() {
		GameObject obj;
		for (int i = 0; i < handler.object.size(); i++) {
			obj = handler.object.get(i);
			if (obj.getID() == ID.BasicEnemy) {
				handler.removeObject(obj);
			}
		}
	}
	private void removeAllEntities() {
		
		handler.object = new LinkedList<GameObject>();
		finished = true;
	}
	public void nextLevel() {
		level++;
		removeAllEnemies();
		spawnObjects(calculateTotalObjects(level));

	}

	public void tick() {
		currentTime = System.currentTimeMillis();
		if (startTime != 0) {
			runningTotal = currentTime - startTime;
			hud.updateScore(runningTotal);
		}
		if (currentTime >= (totalTimeLevel + 1000) && finished) {
			Main.running = false;
		}
		if (currentTime >= totalTimeLevel && HUD.HEALTH > 0 && startTime != 0) {
			nextLevel();
			hud.updateLevel(level);
			currentTime = System.currentTimeMillis();
		} else if (HUD.HEALTH == 0 && startTime != 0) {
			startTime = 0;
			removeAllEntities();
			totalTimeLevel = System.currentTimeMillis();
			
		}
	}
}
