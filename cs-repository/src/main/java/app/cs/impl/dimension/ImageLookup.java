package app.cs.impl.dimension;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import app.cs.utils.FileUtils;

@Component
public class ImageLookup {

	public ImageLookup() {
	}

	public String get(String name) {
		JSONParser parser = new JSONParser();
		Object images = null;
		try {
			images = parser.parse(new FileUtils()
					.getFileContents("images.json"));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject jsonImages = (JSONObject) images;

		System.out.println(jsonImages.get(name));
		return (String) jsonImages.get(name);
	}
}
