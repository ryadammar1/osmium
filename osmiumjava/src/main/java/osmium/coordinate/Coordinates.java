package osmium.coordinate;

public class Coordinates {

	float x, y, z;

	public Coordinates(int x, int z) {
		this.x = x;
		this.z = z;
	}

	public Coordinates() {
	}
	
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
