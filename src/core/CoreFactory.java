package core;

import display.Animated;
import display.Console;
import display.Graphical;
import ships.Shipyard;

public class CoreFactory {
	
	public static final int SIZE = 15;
	
	public static RunnableWorld buildAnimatedWorld() {
		return buildSpecializedWorld(new Shipyard(), new Animated(SIZE));
	}
	
	public static RunnableWorld buildGraphicalWorld() {
		return buildSpecializedWorld(new Shipyard(), new Graphical(SIZE));
	}
	
	public static RunnableWorld buildConsoleWorld() {
		return buildSpecializedWorld(new Shipyard(), new Console(SIZE));
	}
	
	public static RunnableWorld buildSpecializedWorld(Factory f, Display d) {
		return new World(f, d);
	}
	
}
