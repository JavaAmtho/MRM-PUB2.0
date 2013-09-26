package app.cs.controller.client;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.model.response.ClientResponse;
import app.cs.utils.FileUtils;

/**
 * The Class ClientController.
 */
@Controller
public class ClientController {

	private final String MRMINDEX = "redirect:/pages/mrm/engine/core/html/start.html";
	private final String PUBINDEX = "redirect:/pages/pub/engine/core/html/start.html";

	@Autowired
	private ServletContext context;
	private FileUtils fileUtils;

	@Autowired
	public ClientController(FileUtils fileUtils) {
		this.fileUtils = fileUtils;
	}

	@RequestMapping(value = { "/mrm" }, method = RequestMethod.GET)
	public String getMrmIndexPage() throws ParseException, IOException,
			URISyntaxException {
		return MRMINDEX;
	}

	@RequestMapping(value = { "/pub" }, method = RequestMethod.GET)
	public String getPubIndexPage() throws ParseException, IOException,
			URISyntaxException {
		return PUBINDEX;
	}

	@RequestMapping(value = { "/mrm/home" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ClientResponse getMrmHome() throws IOException, URISyntaxException {
		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("mrm"
				+ "/home/home.html"));
		customResponse.setEvents(fileUtils.getFileContents("mrm"
				+ "/home/events.json"));
		customResponse.setElements(fileUtils.getFileContents("mrm"
				+ "/home/elements.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/mrm/login" })
	public @ResponseBody
	ClientResponse getLogin() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("mrm"
				+ "/login/login.html"));
		customResponse.setEvents(fileUtils.getFileContents("mrm"
				+ "/login/events.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/mrm/header" })
	public @ResponseBody
	ClientResponse getHeader() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("mrm"
				+ "/header/header.html"));
		customResponse.setEvents(fileUtils.getFileContents("mrm"
				+ "/header/events.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/mrm/footer" })
	public @ResponseBody
	ClientResponse getFooter() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("mrm"
				+ "/footer/footer.html"));
		return customResponse;

	}

	// TODO: Please remove duplication
	@RequestMapping(value = { "/pub/home" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ClientResponse getMrmHomeForPub() throws IOException, URISyntaxException {
		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("pub"
				+ "/home/home.html"));
		customResponse.setEvents(fileUtils.getFileContents("pub"
				+ "/home/events.json"));
		customResponse.setElements(fileUtils.getFileContents("pub"
				+ "/home/elements.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/pub/login" })
	public @ResponseBody
	ClientResponse getLoginForPub() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("pub"
				+ "/login/login.html"));
		customResponse.setEvents(fileUtils.getFileContents("pub"
				+ "/login/events.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/pub/header" })
	public @ResponseBody
	ClientResponse getHeaderForPub() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("pub"
				+ "/header/header.html"));
		customResponse.setEvents(fileUtils.getFileContents("pub"
				+ "/header/events.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/pub/footer" })
	public @ResponseBody
	ClientResponse getFooterForPub() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents("pub"
				+ "/footer/footer.html"));
		return customResponse;

	}

}
