package run;

import core.Display;
import core.World;
import ships.Shipyard;
import display.Animated;
import display.Console;
import display.Graphical;

public class Launcher {

	private static final boolean accountForConsoleArguments = true;
	
	public static void main(String[] args) {
		Display d = new Console();
		
		if(accountForConsoleArguments && args.length > 0) {
			if(args[0].toLowerCase().equals("graphical"))
				d = new Graphical();
			else if(args[0].toLowerCase().equals("animated"))
				d = new Animated();
		}
		
		World world = new World(new Shipyard(), d);
		world.run(10);
		System.exit(0);

	}

}
