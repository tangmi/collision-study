package tang.study;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;


public class Main {
	public static final int DISPLAY_WIDTH = 800;
	public static final int DISPLAY_HEIGHT = 600;
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
			Display.setTitle("tangmi/collision-study");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); // Resets any previous projection matrices
		glOrtho(0, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		
		
		AABB box2 = new AABB(new Vector3f(400, 300, 0), new Vector3f(50, 50, 0));
		Triangle tri = new Triangle(
				new Vector3f(200, 100, 0),
				new Vector3f(250, 300, 0),
				new Vector3f(100, 50, 0)
				);
		
		while(!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			box2.setPos(new Vector3f(Mouse.getX(), DISPLAY_HEIGHT - Mouse.getY(), 0.0f));

			
			
			
			if(tri.getAABB().isColliding(box2)) {
				glColor3f(0.5f, 0.5f, 0.2f);
			} else {
				glColor3f(0.2f, 0.2f, 0.2f);
			}
			tri.getAABB().draw();
			
			glColor3f(1.0f, 1.0f, 0.5f);
			tri.draw();
			
			if(box2.isColliding(tri)) {
				glColor3f(1.0f, 0, 0);
			} else {
				glColor3f(1.0f, 0.5f, 1.0f);
			}
			box2.draw();
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}
}
