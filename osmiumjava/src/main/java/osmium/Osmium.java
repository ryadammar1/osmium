package osmium;

import java.io.IOException;

import osmium.exception.InvalidDirectoryException;
import osmium.locater.StrongholdLocator;
import osmium.locater.StructureLocator;
import osmium.parser.SeedParser;
import osmium.structure.StructureEnum;

public class Osmium {

	static StrongholdLocator strongholdLocater;
	static StructureLocator structureLocater;
	static SeedParser seepParser;

	public static void main(String[] args) {
	}

	public static void instantiate() {
		try {
			seepParser = new SeedParser();
		} catch (InvalidDirectoryException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		strongholdLocater = new StrongholdLocator();
		structureLocater = new StructureLocator();
	}

	public static void getLocation(String structure, int playerX, int playerZ) {
		switch (structure) {
		case "stronghold":
			if (strongholdLocater.locateStronghold(seepParser.getRefreshedSeed()))
				System.out.println(strongholdLocater.getCoordinates());
			else
				System.out.println("Unable to find a stronghold nearby.");
			break;
		case "bastion":
			if (structureLocater.locateStructure(seepParser.getRefreshedSeed(), StructureEnum.BASTION, playerX,
					playerZ))
				System.out.println(structureLocater.getCoordinates());
			else
				System.out.println("Unable to find a bastion nearby.");
			break;
		case "fortress":
			if (structureLocater.locateStructure(seepParser.getRefreshedSeed(), StructureEnum.FORTRESS, playerX,
					playerZ))
				System.out.println(structureLocater.getCoordinates());
			else
				System.out.println("Unable to find a fortress nearby.");
			break;
		default:
			System.out.println("Error: Invalid structure type.");
			break;
		}
	}

	public static void setSaveDir(String saveDir) {
		seepParser.setSavePath(saveDir);
	}

	public static StrongholdLocator getStrongholdLocater() {
		return strongholdLocater;
	}

	public static StructureLocator getStructureLocater() {
		return structureLocater;
	}

	public static SeedParser getSeepParser() {
		return seepParser;
	}
}