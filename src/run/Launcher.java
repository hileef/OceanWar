package run;

import core.World;
import ships.Shipyard;
import display.Animated;
import display.Console;
import display.Graphical;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		World world = new World(new Shipyard(), new Animated());
		world.run(500);

	}

}
