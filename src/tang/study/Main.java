package tang.study;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


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

		
		while(!Display.isCloseRequested()) {
			
			
			
			
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}
}
