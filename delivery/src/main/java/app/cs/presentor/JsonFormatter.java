package app.cs.presentor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class JsonFormatter {

	@SuppressWarnings("unchecked")
	public String format(String products) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(products);
		JSONArray array = new JSONArray();
		for (Object object : jsonObject.keySet()) {
			array.add(jsonObject.get(object));

		}
		return array.toString();
	}
}
