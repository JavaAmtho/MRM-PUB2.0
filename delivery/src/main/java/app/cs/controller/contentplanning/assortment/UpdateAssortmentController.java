package app.cs.controller.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.model.request.UpdateAssortmentRequest;

@Controller
public class UpdateAssortmentController {

	private Interactor updateAssortment;
	private UpdateAssortmentRequest request;

	@Autowired
	public UpdateAssortmentController(Interactor updateAssortment,
			UpdateAssortmentRequest request) {

		this.updateAssortment = updateAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/update/{name}/{path}")
	public @ResponseBody String execute(@RequestBody Assortment assortment,
			@PathVariable String name, @PathVariable String path) {

		request.setAssortment(assortment);
		request.setPath(path);
		request.setName(name);

		updateAssortment.execute(request);
		return name;
	}

}
