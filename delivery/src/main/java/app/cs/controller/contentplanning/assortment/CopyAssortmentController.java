package app.cs.controller.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.model.request.CopyAssortmentRequest;

@Controller
public class CopyAssortmentController {

	private Interactor copyAssortment;
	private CopyAssortmentRequest request;

	@Autowired
	public CopyAssortmentController(Interactor copyAssortment,
			CopyAssortmentRequest request) {

		this.copyAssortment = copyAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/copy/{newName}/{newPath}")
	public @ResponseBody String copy(@RequestBody Assortment assortment,
			@PathVariable String newName, @PathVariable String newPath) {
		request.setAssortment(assortment);
		request.setNewPath(newPath);
		request.setNewName(newName);
		System.out.println("In Controller");
		copyAssortment.execute(request);
		return newName;

	}
}
