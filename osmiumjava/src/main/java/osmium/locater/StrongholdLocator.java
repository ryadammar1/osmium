package osmium.locater;

import java.util.Random;

import osmium.coordinate.Coordinates;

public class StrongholdLocator {

	Coordinates coordinates = new Coordinates();
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public boolean locateStronghold(long seed) {
		double dist, angle;

		Random rnd = new Random();

		rnd.setSeed(seed);

		angle = 2.0 * Math.PI * rnd.nextDouble();
		dist = (4.0 * 32.0) + (rnd.nextDouble() - 0.5) * 32 * 2.5;

		coordinates.setX(((int) Math.round(Math.cos(angle) * dist) << 4) + 8);
		coordinates.setZ(((int) Math.round(Math.sin(angle) * dist) << 4) + 8);

		return true;
	}

}
