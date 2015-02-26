package tests.core;

import static org.junit.Assert.*;

import org.junit.Test;

import core.CoreFactory;
import core.RunnableWorld;

public class WorldTest {

	@Test
	public void test() {
		RunnableWorld w = CoreFactory.buildConsoleWorld();
		w.runTests(w);
	}

}
