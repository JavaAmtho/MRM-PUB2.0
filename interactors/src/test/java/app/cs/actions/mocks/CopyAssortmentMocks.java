package app.cs.actions.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CopyAssortmentMocks implements Interactor {

	private IAssortmentRepository assortmentRepository;

	private String type = "Assortment";

	@Autowired
	public CopyAssortmentMocks(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(RequestModel request) {

		return new EmptyResponse();

	}
}
