package multithreading;

public class Multithreading implements Runnable {
	public Multithreading() {
		Thread A = new Thread(this);
		A.setName("A");
		A.start();
		Thread B = new Thread(this);
		B.setName("B");
		B.start();
		Thread C = new Thread(this);
		C.setName("C");
		C.start();
		// As you know, threads are ran parallelly so the order in which they are
		// printed are pretty much random...
	}

	public static void main(String[] args) {
		new Multithreading();
	}

	public void run() {
		int sum = 0;
		for (int x = 0; x < 10; x++) {
			System.out.println("Thread: " + Thread.currentThread().getName() + " - value: " + sum);
			sum += x;
		}
		System.out.println("Thread: " + Thread.currentThread().getName() + " - Sum: " + sum);
	}
}