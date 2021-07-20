package osmium.parser;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import osmium.exception.exceptionTypes.EmptyDirectoryException;
import osmium.exception.exceptionTypes.InvalidDirectoryException;
import osmium.io.WorldSelector;
import osmium.nbt.LevelDatNBT;

public class SeedParser {

	WorldSelector worldSelector;
	LevelDatNBT levelDataNBT;

	public SeedParser() {
		worldSelector = new WorldSelector();
		levelDataNBT = new LevelDatNBT();
	}

	public void setGameDirectory(String gameDirectory) {
		worldSelector.setSavesPath(gameDirectory + "\\saves");
	}
	
	public void resetGameDirectory() {
		worldSelector.resetSavesPath();
	}

	public Long getSeed() throws ParseException, EmptyDirectoryException, InvalidDirectoryException, IOException {
		return levelDataNBT.getLevelDatSeed(worldSelector.getLevelDat());
	}

}
