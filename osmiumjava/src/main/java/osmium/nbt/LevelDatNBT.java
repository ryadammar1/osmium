package osmium.nbt;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import osmium.parser.JsonParser;

public class LevelDatNBT {

	NamedTag levelDatNtag;

	public Long getLevelDatSeed(File levelDat) throws ParseException, IOException {
		levelDatNtag = levelDat != null ? NBTUtil.read(levelDat) : null;
		return JsonParser.parseSeed(levelDatNtag);
	}
}
