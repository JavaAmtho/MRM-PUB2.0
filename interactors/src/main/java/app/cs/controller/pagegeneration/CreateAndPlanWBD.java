package app.cs.controller.pagegeneration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.pagegeneration.IPageGenerationRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.WBDCreationAndPlanningRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class CreateAndPlanWBD implements Interactor {

	private IPageGenerationRepository pageGenerationRepository;

	@Autowired
	public CreateAndPlanWBD(IPageGenerationRepository pageGenerationRepository) {
		this.pageGenerationRepository = pageGenerationRepository;
	}

	public ResponseModel execute(RequestModel requestModel) {
		WBDCreationAndPlanningRequest wbdCreationAndPlanningRequest = (WBDCreationAndPlanningRequest) requestModel;
		return new StringResponse(pageGenerationRepository.createAndPlanWBD(
				wbdCreationAndPlanningRequest.getRuleID(),
				wbdCreationAndPlanningRequest.getLogicalPageID(),
				wbdCreationAndPlanningRequest.getPublicationID()));
	}

}
