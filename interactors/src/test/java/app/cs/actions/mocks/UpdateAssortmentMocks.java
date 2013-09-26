package app.cs.actions.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class UpdateAssortmentMocks implements Interactor {

	private IAssortmentRepository assortmentRepository;
	
	@Autowired
	public UpdateAssortmentMocks(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	@Override
	public ResponseModel execute(RequestModel requestModel) {
		
		return new EmptyResponse();
	}

}
