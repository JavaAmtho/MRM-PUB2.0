package app.cs.actions.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.interfaces.mam.AssetsRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class PIMSearchMock implements Interactor{

	private AssetsRepository pimRepository;

	@Autowired
	public PIMSearchMock(AssetsRepository pimRepository) {
		this.pimRepository = pimRepository;
	}

	public ResponseModel execute(RequestModel model) {
	
		return new StringResponse("Product 123, Product 34, Product 2314, Product 42357, Product 3467");

	}

}