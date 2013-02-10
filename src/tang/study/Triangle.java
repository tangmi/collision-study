package tang.study;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

/**
 * Arbitrary triangle object
 * @author michael
 *
 */
public class Triangle {
	public Vector3f[] vertices;
	
	public Triangle(Vector3f vert1, Vector3f vert2, Vector3f vert3) {
		vertices = new Vector3f[3];
		vertices[0] = vert1;
		vertices[1] = vert2;
		vertices[2] = vert3;
	}
	
	public AABB getAABB() {
		return new AABB(this);
	}
	
	public void draw() {
		glBegin(GL_TRIANGLES);
			glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
			glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);
			glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);
		glEnd();
	}
}
