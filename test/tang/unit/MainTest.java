package tang.unit;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runner.notification.*;
import org.lwjgl.util.vector.Vector3f;

import tang.study.Triangle;

public class MainTest {

	@Test
	public void testTest() {
		Triangle tri = new Triangle(
				new Vector3f(),
				new Vector3f(),
				new Vector3f()
				);
		assertTrue(false);
	}
}
