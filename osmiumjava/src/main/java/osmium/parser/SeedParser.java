package osmium.parser;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import osmium.exception.InvalidDirectoryException;
import osmium.io.WorldSelector;
import osmium.nbt.LevelDatNBT;

public class SeedParser {

	WorldSelector worldSelector;
	LevelDatNBT levelDataNBT;

	public SeedParser(String savePath) throws InvalidDirectoryException, IOException {
		this.worldSelector = new WorldSelector(savePath);
		this.levelDataNBT = new LevelDatNBT(this.worldSelector.getRefreshedLevelDat());
	}

	public SeedParser() throws InvalidDirectoryException, IOException {
		this.worldSelector = new WorldSelector();
		this.levelDataNBT = new LevelDatNBT(this.worldSelector.getRefreshedLevelDat());
	}

	private void refreshLevelDataNBT() throws InvalidDirectoryException, IOException {
		this.levelDataNBT.refreshLevelDatNtag(this.worldSelector.getRefreshedLevelDat());
	}

	public long getRefreshedSeed() {
		try {
			refreshLevelDataNBT();
		} catch (InvalidDirectoryException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return levelDataNBT.getLevelDatSeed();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;

	}

}
