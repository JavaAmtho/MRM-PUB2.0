package app.cs.controller.publicationplanning.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.MoveDimensionRequest;
import app.cs.model.response.MultiDimensionalObjectResponse;

@Controller
public class MoveDimensionController {

	private MoveDimensionRequest request;

	private Interactor moveDimension;

	@Autowired
	public MoveDimensionController(Interactor moveDimension,
			MoveDimensionRequest request) {
		this.moveDimension = moveDimension;
		this.request = request;
	}

	@RequestMapping(value = "/dimension/move/{oldPath}/{newPath}")
	public @ResponseBody
	MultiDimensionalObject move(@PathVariable String oldPath,
			@PathVariable String newPath,
			@RequestBody MultiDimensionalObject objectInMove) {
		request.setNewPath(newPath);
		request.setOldPath(oldPath);
		request.setObjectInMove(objectInMove);

		System.out.println("newPath==>" + newPath);
		System.out.println("groupIds==>" + objectInMove.getGroupId());
		return ((MultiDimensionalObjectResponse) moveDimension.execute(request))
				.getResponse();

	}

}
