package osmium.coordinate;

public class Coordinate {

	float x, y, z;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public String toString() {
		return "x: " + this.x + ", z: " + this.z;
	}
	
}
