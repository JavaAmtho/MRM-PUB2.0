package app.cs.actions.publicationplanning.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.dimension.DimensionRepository;
import app.cs.model.request.GetDimensionByIdRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.TreeResponse;

@Component
public class GetPublicationsForGivenChannel implements Interactor {

	private DimensionRepository dimensionRepository;

	@Autowired
	public GetPublicationsForGivenChannel(
			DimensionRepository dimensionRepository) {
		this.dimensionRepository = dimensionRepository;
	}

	@Override
	public ResponseModel execute(RequestModel model) {
		GetDimensionByIdRequest dimensionByIdRequest = (GetDimensionByIdRequest) model;
		return new TreeResponse(dimensionRepository.getDimensionsBy(
				"Publication", dimensionByIdRequest.groupIds));
	}

}
