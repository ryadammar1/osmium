package osmium;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import osmium.exception.ExceptionHandler;
import osmium.exception.exceptionTypes.EmptyDirectoryException;
import osmium.exception.exceptionTypes.InvalidDirectoryException;
import osmium.locater.StrongholdLocator;
import osmium.locater.StructureLocator;
import osmium.parser.SeedParser;
import osmium.structure.StructureEnum;

public class Osmium {

	static StrongholdLocator strongholdLocater;
	static StructureLocator structureLocater;
	static SeedParser seepParser;

	public static void main(String[] args) throws InvalidDirectoryException, ParseException, IOException {
	}

	public static void instantiateOsmium() {
		seepParser = new SeedParser();
		strongholdLocater = new StrongholdLocator();
		structureLocater = new StructureLocator();
	}

	public static void setGameDirectory(String gameDirectory) {
		if(gameDirectory.equalsIgnoreCase("default")) {
			seepParser.resetGameDirectory();
			return;
		}
		
		seepParser.setGameDirectory(gameDirectory);
	}

	public static int[] getStructureLocation(String structure, int playerX, int playerZ) {
		long seed = 0;

		try {
			seed = seepParser.getSeed();
		} catch (ParseException | EmptyDirectoryException | InvalidDirectoryException | IOException e) {
			return ExceptionHandler.returnException(e);
		}

		if (structure.contentEquals("STRONGHOLD")) {
			if (strongholdLocater.locateStronghold(seed))
				return ExceptionHandler.returnSuccess(strongholdLocater.getCoordinates().toIntegerArray());
			else
				return ExceptionHandler.returnFailure();
		} else {
			if (structureLocater.locateStructure(seed, StructureEnum.valueOf(structure), playerX, playerZ))
				return ExceptionHandler.returnSuccess(structureLocater.getCoordinates().toIntegerArray());
			else
				return ExceptionHandler.returnFailure();
		}
	}

}