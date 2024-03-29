package osmium.locater;

import osmium.coordinate.Coordinates;
import osmium.exception.InvalidStructureTypeException;
import osmium.random.Random;
import osmium.structure.Structure;
import osmium.structure.StructureEnum;

public class StructureLocator {

	private Coordinates playerCoordinates;
	private Coordinates structCoordinates;

	private Structure structure;

	private Random rnd;

	public Coordinates getCoordinates() {
		return structCoordinates;
	}

	public StructureLocator() {
		rnd = new Random();
		playerCoordinates = new Coordinates();
	}

	public void setSeed(long value) {
		rnd.setSeed(value, false);
	}

	public boolean locateStructure(long seed, StructureEnum type, int x, int z) {
		if(seed == -1) // Save directory is empty
			return false;
		
		rnd.setSeed(seed, true);
		playerCoordinates.setX(x);
		playerCoordinates.setZ(z);
		try {
			structure = Structure.getStructure(type);
		} catch (InvalidStructureTypeException e) {
			System.out.println("Error while trying to get structure type.");
			return false;
		}
		
		boolean status = false;

		for (int i = -3; i <= 3; i++) {
			for (int j = -3; j <= 3; j++) {
				updateSeed(i * 341873128712L + j * 132897987541L + rnd.getSeed() + structure.cfg1);

				int candidateStructX = (int) (((long) i * structure.cfg2 + rnd.nextInt(structure.cfg3, true)) << 4);
				int candidateStructZ = (int) (((long) j * structure.cfg2 + rnd.nextInt(structure.cfg3, true)) << 4);

				if (isStructureValid() && isClosestStructure(candidateStructX, candidateStructZ)) {
					structCoordinates.setX(candidateStructX);
					structCoordinates.setZ(candidateStructZ);

					if (dist == Double.POSITIVE_INFINITY)
						computeDistance();

					status = true;
				}

				rnd.reset();
			}
		}

		resetDistance();
		return status;
	}

	private void updateSeed(long value) {
		rnd.setSeed((value ^ Long.parseLong("5deece66d", 16)) & ((1L << 48) - 1), false);
	}

	private double dist = Double.POSITIVE_INFINITY;

	private void resetDistance() {
		dist = Double.POSITIVE_INFINITY;
	}

	private void computeDistance() {
		dist = (playerCoordinates.getX() - structCoordinates.getX())
				* (playerCoordinates.getX() - structCoordinates.getX())
				+ (playerCoordinates.getZ() - structCoordinates.getZ())
						* (playerCoordinates.getZ() - structCoordinates.getZ());
	}

	private boolean isClosestStructure(int x, int z) {

		if (structCoordinates == null) {
			structCoordinates = new Coordinates();
			return true;
		}

		double candidateDist = (playerCoordinates.getX() - x) * (playerCoordinates.getX() - x)
				+ (playerCoordinates.getZ() - z) * (playerCoordinates.getZ() - z);

		if (candidateDist < dist) {
			dist = candidateDist;
			return true;
		}

		return false;
	}

	private boolean isStructureValid() {
		if (structure.type == StructureEnum.FORTRESS)
			return rnd.nextInt(5, false) < 2;
		else if (structure.type == StructureEnum.BASTION)
			return rnd.nextInt(5, false) >= 2;

		return false;
	}
}
