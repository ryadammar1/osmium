package osmium;

import java.io.IOException;

import osmium.exception.InvalidDirectoryException;
import osmium.locater.StrongholdLocater;
import osmium.parser.SeedParser;

public class Osmium {

	public static void main(String[] args) {

		StrongholdLocater strongholdLocater = null;
		SeedParser seepParser = null;
		
		try {
			if (args.length > 0)
				seepParser = new SeedParser(args[0]);
			else
				seepParser = new SeedParser();
		} catch (InvalidDirectoryException ide) {
			System.out.println(ide.getMessage());
			return;
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			return;
		}

		strongholdLocater = new StrongholdLocater();

		System.out.println(strongholdLocater.locateStronghold(seepParser.getRefreshedSeed()));
	}
}
