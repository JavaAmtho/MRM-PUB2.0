package app.cs.controller.contentplanning.pim;

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
public class PIMSearchController {

	private Interactor pimSearch;

	private JsonFormatter formatter;

	private StringRequest request;

	@Autowired
	public PIMSearchController(Interactor pimSearch, StringRequest request,
			JsonFormatter formatter) {
		this.pimSearch = pimSearch;
		this.request = request;
		this.formatter = formatter;
	}

	@RequestMapping(value = { "/pim/search/{key}" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMOrMAMNode> searchPIM(@PathVariable String key)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		request.setStringRequest(key);
		return new ObjectMapper().readValue(formatter
				.format(((StringResponse) pimSearch.execute(request))
						.getResponseString()),
				new TypeReference<List<PIMOrMAMNode>>() {
				});
	}

}
