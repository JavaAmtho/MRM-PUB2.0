package app.cs.controller.publicationplanning.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.EditDimensionRequest;

@Controller
public class EditDimensionController {
	private Interactor editDimension;
	private EditDimensionRequest request;

	@Autowired
	public EditDimensionController(Interactor editDimension,
			EditDimensionRequest request) {
		this.editDimension = editDimension;
		this.request = request;
	}

	@RequestMapping(value = "/dimension/update/{dimensionId}")
	public @ResponseBody
           	MultiDimensionalObject execute(@RequestBody MultiDimensionalObject dimensionalObject) {
		request.setDimensionalObject(dimensionalObject);
		editDimension.execute(request);
		return dimensionalObject;

	}

}
