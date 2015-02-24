package display;

import core.Display;

public abstract class ADisplay implements Display {

	public static final int IMAGE_SIZE = 50;
	
	public static void pause(double secondes) {
		try {
			Thread.sleep((int) (1000 * secondes));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
