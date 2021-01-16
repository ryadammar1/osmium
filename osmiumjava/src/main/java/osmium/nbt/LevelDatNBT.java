package osmium.nbt;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import osmium.parser.JsonParser;

public class LevelDatNBT {

	NamedTag levelDatNtag;
	
	long levelDatSeed;

	public LevelDatNBT(File levelDat) throws IOException {
		this.levelDatNtag = NBTUtil.read(levelDat);
	}
	
	public long getLevelDatSeed() throws ParseException {
		return JsonParser.parseSeed(this.levelDatNtag);
	}
	
	public void refreshLevelDatNtag(File levelDat) throws IOException {
		this.levelDatNtag = NBTUtil.read(levelDat);
	}

	public NamedTag getLevelDatNtag() {
		return levelDatNtag;
	}

}
