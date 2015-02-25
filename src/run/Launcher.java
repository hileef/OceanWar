package run;

import core.CoreFactory;
import core.RunnableWorld;

public class Launcher {

	private static final boolean accountForConsoleArguments = true;
	private static final int DEFAULT_NUMBER_OF_TURNS = 200;
	
	public static void main(String[] args) {
		
		RunnableWorld world = null;
		int turns = DEFAULT_NUMBER_OF_TURNS;
		
		if(accountForConsoleArguments && args.length > 0) {
			try {
				turns = Integer.parseInt(args[0]);
			} catch(Exception e) { ; }
		} 
		
		if(accountForConsoleArguments && args.length > 1) {
			if(args[1].toLowerCase().equals("graphical"))
				world = CoreFactory.buildGraphicalWorld();
			else if(args[1].toLowerCase().equals("animated"))
				world = CoreFactory.buildAnimatedWorld();
		}
		
		if(world == null)
			world = CoreFactory.buildConsoleWorld();
		
		world.run(turns);
		System.exit(0);

	}

}
