package app.cs.controller.contentplanning.mam;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.PIMOrMAMNode;
import app.cs.model.response.StringResponse;
import app.cs.presentor.JsonFormatter;

@Controller
public class MAMSearchController {

	private Interactor mamSearch;
	private JsonFormatter formatter;
	private StringRequest request;

	@Autowired
	public MAMSearchController(Interactor mamSearch, StringRequest request,
			JsonFormatter formatter) {
		this.mamSearch = mamSearch;
		this.request = request;
		this.formatter = formatter;
	}

	@RequestMapping(value = { "/mam/search/{key}" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMOrMAMNode> searchMAM(@PathVariable String key)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		request.setStringRequest(key);
		return new ObjectMapper().readValue(formatter
				.format(((StringResponse) mamSearch.execute(request))
						.getResponseString()),
				new TypeReference<List<PIMOrMAMNode>>() {
				});
	}
}
