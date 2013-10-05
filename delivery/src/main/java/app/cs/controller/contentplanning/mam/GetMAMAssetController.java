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
public class GetMAMAssetController {

	private Interactor getMAMAssets;

	private StringRequest request;

	private JsonFormatter formatter;

	@Autowired
	public GetMAMAssetController(Interactor getMAMAssets,
			StringRequest request, JsonFormatter formatter) {
		this.getMAMAssets = getMAMAssets;
		this.request = request;
		this.formatter = formatter;
	}

	@RequestMapping(value = { "/mam/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMOrMAMNode> listForGivenId(@PathVariable String id)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		request.setStringRequest(id);
		return new ObjectMapper().readValue(formatter
				.format(((StringResponse) getMAMAssets.execute(request))
						.getResponseString()),
				new TypeReference<List<PIMOrMAMNode>>() {
				});

	}

	@RequestMapping(value = { "/mam/list" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMOrMAMNode> list() throws JsonParseException, JsonMappingException,
			IOException, ParseException {
		request.setStringRequest("");
		return new ObjectMapper().readValue(formatter
				.format(((StringResponse) getMAMAssets.execute(request))
						.getResponseString()),
				new TypeReference<List<PIMOrMAMNode>>() {
				});
	}
}
