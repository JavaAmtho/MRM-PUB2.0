package app.cs.controller.ruleplanning.pim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.StringResponse;

@Controller
public class MasterTemplateController {
	private Interactor getMasterTemplate;
	private StringRequest request;

	@Autowired
	public MasterTemplateController(Interactor getMasterTemplate,
			StringRequest request) {
		this.getMasterTemplate = getMasterTemplate;
		this.request = request;
	}

	@RequestMapping(value = { "/page/masterTemplateList" }, method = RequestMethod.GET)
	public @ResponseBody
	String masterTemplateList(){
		String resp = ((StringResponse) getMasterTemplate.execute(request)).getResponseString();
		return resp;

	}

}
