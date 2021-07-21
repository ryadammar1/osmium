package osmium.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.querz.nbt.io.NamedTag;

public class JsonParser {

	private static final String[] OBJECTS = { "value", "Data", "value", "WorldGenSettings", "value", "seed", "value" };

	public static Long parseSeed(NamedTag ntag) throws ParseException {
		if (ntag == null)
			return null;

		Object obj, data = ntag.getTag();
		JSONObject jobj;

		for (int i = 0; i <= OBJECTS.length -1 ; i++) {
			obj = new JSONParser().parse(data.toString());
			jobj = (JSONObject) obj;
			data = jobj.get(OBJECTS[i]);
		}
		
		return Long.parseLong(data.toString());
	}
}
