package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.GetAllAssortmentsRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class GetAllAssortments implements Interactor {

	private IAssortmentRepository assortmentRepository;

	@Autowired
	public GetAllAssortments(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(RequestModel request) {
		GetAllAssortmentsRequest getAllAssortmentsRequest = (GetAllAssortmentsRequest) request;
		return new StringResponse(assortmentRepository.getAllAssortmentNames(
				getAllAssortmentsRequest.getPagePath(),
				getAllAssortmentsRequest.getLogicalPageID()));
	}
}
