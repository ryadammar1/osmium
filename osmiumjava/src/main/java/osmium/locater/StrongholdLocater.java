package osmium.locater;

import java.util.Random;

import osmium.coordinate.Coordinate;

public class StrongholdLocater {
	
	public Coordinate locateStronghold(long seed) {
		double dist, angle;

		Random rnd = new Random();

		rnd.setSeed(seed);

		Coordinate coordinate = new Coordinate();

		angle = 2.0 * Math.PI * rnd.nextDouble();
		dist = (4.0 * 32.0) + (rnd.nextDouble() - 0.5) * 32 * 2.5;

		coordinate.setX(((int) Math.round(Math.cos(angle) * dist) << 4) + 8);
		coordinate.setZ(((int) Math.round(Math.sin(angle) * dist) << 4) + 8);

		return coordinate;
	}

}
