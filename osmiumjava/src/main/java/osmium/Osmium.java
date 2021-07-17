package osmium;

import java.io.IOException;
import osmium.exception.InvalidDirectoryException;
import osmium.locater.StrongholdLocator;
import osmium.locater.StructureLocator;
import osmium.parser.SeedParser;
import osmium.structure.StructureEnum;

public class Osmium {

	public static void main(String[] args) {
		StrongholdLocator strongholdLocater;
		StructureLocator structureLocater;

		SeedParser seepParser;

		if (args.length < 3) {
			System.out.println(
					"Error: Wrong usage. Must indicate structure type (stronghold, bastion, etc.) and player position (x, z).");
			return;
		}

		try {
			if (args.length > 3)
				seepParser = new SeedParser(args[3]);
			else
				seepParser = new SeedParser();
		} catch (InvalidDirectoryException e) {
			System.out.println(e.getMessage());
			return;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}

		switch (args[0]) {
		case "stronghold":
			strongholdLocater = new StrongholdLocator();
			System.out.println(strongholdLocater.locateStronghold(seepParser.getRefreshedSeed()));
			break;
		case "bastion":
			structureLocater = new StructureLocator(seepParser.getRefreshedSeed(), StructureEnum.BASTION,
					Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			structureLocater.locateStructure();
			System.out.println(structureLocater.getCoordinates());
			break;
		case "fortress":
			structureLocater = new StructureLocator(seepParser.getRefreshedSeed(), StructureEnum.FORTRESS,
					Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			structureLocater.locateStructure();
			System.out.println(structureLocater.getCoordinates());
			break;
		default:
			System.out.println("Error: Invalid structure type.");
			return;
		}
	}
}