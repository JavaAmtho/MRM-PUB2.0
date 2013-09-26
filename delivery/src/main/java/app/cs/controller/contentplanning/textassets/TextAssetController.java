package app.cs.controller.contentplanning.textassets;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.utils.FileUtils;

@Controller
public class TextAssetController {

	private FileUtils fileUtils;

	@Autowired
	public TextAssetController(FileUtils fileUtils) {
		// TODO Auto-generated constructor stub
		this.fileUtils = fileUtils;
	}


	@RequestMapping(value = { "/text/list/62" }, method = RequestMethod.GET)
	public @ResponseBody
	String getTextAssetsForFirstLevel() throws IOException,
			URISyntaxException {
		return fileUtils.getFileContents("62.json");

	}
	
	@RequestMapping(value = { "/text/list" }, method = RequestMethod.GET)
	public @ResponseBody
	String getTextAssets() throws IOException,
			URISyntaxException {
		return fileUtils.getFileContents("textassets.json");

	}
	
	@RequestMapping(value = { "/text/list/91" }, method = RequestMethod.GET)
	public @ResponseBody
	String getTextAssetsSecondLevel() throws IOException,
			URISyntaxException {
		return fileUtils.getFileContents("91.json");

	}

}