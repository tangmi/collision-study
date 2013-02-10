package tang.study;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

/**
 * Axis aligned bounding box object
 * @author michael
 *
 */
public class AABB {
	public float minX = Float.POSITIVE_INFINITY;
	public float maxX = Float.NEGATIVE_INFINITY;
	public float minY = Float.POSITIVE_INFINITY;
	public float maxY = Float.NEGATIVE_INFINITY;
	public float minZ = Float.POSITIVE_INFINITY;
	public float maxZ = Float.NEGATIVE_INFINITY;
	private Vector3f pos;
	private Vector3f size;
	
	public AABB(Vector3f pos, Vector3f size) {
		this.pos = pos;
		this.size = size;
		this.updateCoordinates();
	}
	
	public AABB(Triangle triangle) {
		for(Vector3f vertex : triangle.vertices) {
			minX = Math.min(minX, vertex.x);
			maxX = Math.max(maxX, vertex.x);
			minY = Math.min(minY, vertex.y);
			maxY = Math.max(maxY, vertex.y);
			minZ = Math.min(minZ, vertex.z);
			maxZ = Math.max(maxZ, vertex.z);
		}
	}
	
	private void updateCoordinates() {
		this.minX = pos.getX() - size.getX()/2;
		this.maxX = pos.getX() + size.getX()/2;
		this.minY = pos.getY() - size.getY()/2;
		this.maxY = pos.getY() + size.getY()/2;
		this.minZ = pos.getZ() - size.getZ()/2;
		this.maxZ = pos.getZ() + size.getZ()/2;
	}
	
	public void setPos(Vector3f pos) {
		this.pos = pos;
		this.updateCoordinates();
	}
	
	public void setSize(Vector3f size) {
		this.size = size;
		this.updateCoordinates();
	}
	
	public Vector3f getPos() {
		return this.pos;
	}
	
	public Vector3f getSize() {
		return this.size;
	}
	
	public boolean isColliding(Triangle other) {
		/*
		 * projection of a point onto a line
		 fun dot(a, b) {
		 	return a.x * b.x + a.y * b.y;
		 }
		 fun len(v) {
		 	return v.x * v.x + v.y + v.y;
		 	or
		 	return dot(v, v);
		 }
		 e = p2 - p1;
		 return p1 + e * dot(e, p3 - p1) / len(e);
		 */
		
		if(this.isColliding(other.getAABB())) {
			//since one of the objects is axis aligned, we don't need to check the axis of the AABB box
			
			boolean collision = false;

			for(int i = 0; i < other.vertices.length; i++) {
				Vector3f vert1 = other.vertices[i];
				Vector3f vert2 = other.vertices[(i + 1) % (other.vertices.length)];
				
				/*
					we're finding the determinant of:
					| x2-x1  x3-x1 |
					| y2-y1  y3-y1 |
				 */
								
				int vertCount = 0;
				for(Vector3f point : this.getVertices()) {			
					float determinant = (vert2.x - vert1.x) * (point.y - vert1.y) - (point.x - vert1.x) * (vert2.y - vert1.y);
							
					if(determinant < 0) {
						vertCount++;

						glBegin(GL_LINES);
							int size = 5;
							glColor3f(0.5f, 1.0f, 0.5f);
							glVertex2f(point.x - size, point.y - size);
							glVertex2f(point.x + size, point.y + size);
							
							glVertex2f(point.x + size, point.y - size);
							glVertex2f(point.x - size, point.y + size);
						glEnd();
					} else {
						
					}
				}
				
				if(vertCount == 4) {
					return false;
				}
				
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	public Vector3f[] getVertices() {
		return new Vector3f[]{
				new Vector3f(minX, maxY, 0.0f),
				new Vector3f(minX, minY, 0.0f),
				new Vector3f(maxX, minY, 0.0f),
				new Vector3f(maxX, maxY, 0.0f),
		};
	}
	
	public boolean isColliding(AABB other) {
		return !(
				other.maxX <= this.minX ||
				other.minX >= this.maxX ||
				other.maxY <= this.minY ||
				other.minY >= this.maxY /*||
				other.maxZ <= this.minZ ||
				other.minZ >= this.maxZ*/
				);
	}
	
	public void draw() {
		glBegin(GL_QUADS);
			glVertex3f(minX, maxY, 0.0f);
			glVertex3f(minX, minY, 0.0f);
			glVertex3f(maxX, minY, 0.0f);
			glVertex3f(maxX, maxY, 0.0f);
		glEnd();
	}
}
