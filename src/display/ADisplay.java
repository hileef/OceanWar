package display;

import core.Display;

public abstract class ADisplay implements Display {

	private static final int IMAGE_SIZE = 50;
	private int worldSize;
	
	public ADisplay(int size) {
		this.worldSize = size;
	}
	
	public static void pause(double secondes) {
		try {
			Thread.sleep((int) (1000 * secondes));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected int worldSize() { return worldSize; }
	protected int imageSize() { return IMAGE_SIZE; } 
	
}
