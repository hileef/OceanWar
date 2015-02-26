package tests.core;

import org.junit.Test;

import ships.Shipyard;
import core.CoreFactory;
import display.Console;

public class WorldTest {

	@Test
	public void test() {
		CoreFactory.buildConsoleWorld().runTests(new Shipyard(), new Console(CoreFactory.SIZE));
	}

}
