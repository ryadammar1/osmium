package osmium.coordinate;

public class Coordinates {

	float x, y, z;

	public Coordinates() {
	}

	public Coordinates(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public int[] toIntegerArray() {
		return new int[] {(int)x, (int)z};
	}
	
	@Override
	public String toString() {
		return "x: " + x + ", z: " + z;
	}
	
}
