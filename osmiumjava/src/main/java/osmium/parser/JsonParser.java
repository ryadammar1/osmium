package osmium.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.querz.nbt.io.NamedTag;

public class JsonParser {

	public static final String[] SEED_PATH = { "value", "Data", "value", "WorldGenSettings", "value", "seed", "value" };

	public static long parseSeed(NamedTag ntag) throws ParseException {

		Object obj1 = new JSONParser().parse(ntag.getTag().toString());
		JSONObject jo1 = (JSONObject) obj1;

		Object data1 = jo1.get(SEED_PATH[0]);

		Object obj2 = new JSONParser().parse(data1.toString());
		JSONObject jo2 = (JSONObject) obj2;

		Object data2 = jo2.get(SEED_PATH[1]);

		Object obj3 = new JSONParser().parse(data2.toString());
		JSONObject jo3 = (JSONObject) obj3;

		Object data3 = jo3.get(SEED_PATH[2]);

		Object obj4 = new JSONParser().parse(data3.toString());
		JSONObject jo4 = (JSONObject) obj4;

		Object data4 = jo4.get(SEED_PATH[3]);

		Object obj5 = new JSONParser().parse(data4.toString());
		JSONObject jo5 = (JSONObject) obj5;

		Object data5 = jo5.get(SEED_PATH[4]);

		Object obj6 = new JSONParser().parse(data5.toString());
		JSONObject jo6 = (JSONObject) obj6;

		Object data6 = jo6.get(SEED_PATH[5]);

		Object obj7 = new JSONParser().parse(data6.toString());
		JSONObject jo7 = (JSONObject) obj7;

		Object data7 = jo7.get(SEED_PATH[6]);

		return Long.parseLong(data7.toString());
	}
}
