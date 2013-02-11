package tang.unit;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runner.notification.*;
import org.lwjgl.util.vector.Vector3f;

import tang.study.AABB;
import tang.study.Triangle;

public class MainTest {

	@Test
	public void testSimpleTriangleAABBCollision() {
		Triangle tri = new Triangle(
				new Vector3f(0, 1, 0),
				new Vector3f(1, 0, 0),
				new Vector3f(1, 1, 0)
				);
		AABB box = new AABB(new Vector3f(), new Vector3f(1, 1, 0));
		
		assertTrue(box.isColliding(tri));
	}
	
	@Test
	public void testSimpleTriangleAABBCollision2() {
		Triangle tri = new Triangle(
				new Vector3f(0, 1, 0),
				new Vector3f(1, 0, 0),
				new Vector3f(1, 1, 0)
				);
		AABB box = new AABB(new Vector3f(-1, -1, 0), new Vector3f(1, 1, 0));
		
		assertFalse(box.isColliding(tri));
	}
}
